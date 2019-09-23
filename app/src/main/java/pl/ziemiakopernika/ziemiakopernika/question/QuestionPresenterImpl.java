package pl.ziemiakopernika.ziemiakopernika.question;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.arrange.answer.ArrangeAnswerFragment;
import pl.ziemiakopernika.ziemiakopernika.arrange.answer.ArrangeAnswerRowPresenter;
import pl.ziemiakopernika.ziemiakopernika.choose.answer.ChooseAnswerFragment;
import pl.ziemiakopernika.ziemiakopernika.choose.answer.ChooseAnswerPresenter;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDao;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.finish.round.FinishRoundActivity;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.Question;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfRounds;
import pl.ziemiakopernika.ziemiakopernika.round.RoundActivity;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsDao;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.summary.SummaryActivity;
import pl.ziemiakopernika.ziemiakopernika.timer.Timer;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerImpl;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerReact;

public class QuestionPresenterImpl implements QuestionPresenter, TimerReact, CompoundButton.OnCheckedChangeListener {

    private Activity activity;
    private QuestionView questionView;
    private QuestionsDao questionsDao;
    private SetOfQuestions setOfQuestions;
    private SetOfRounds setOfRounds;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor preferencesEditor;
    private Timer timer, transitionTimer;
    private StatisticsDao statisticsDao;

    private ArrangeAnswerFragment arrangeAnswerFragment;
    private ChooseAnswerPresenter chooseAnswerPresenter;
    private ArrangeAnswerRowPresenter arrangeAnswerRowPresenter;

    private int numberOfCoins, numberOfQuestion, secondsPerQuestion;
    private boolean fiftyFiftyBtnActivated, addSecondsBtnActivated, waitingToStartActivity,
            lifebuoyStatisticSaved, muteEnabled;
    private boolean answersClickable, progressTextEnabled = true;
    public static final String COINS = "coins";
    public static final String NUMBER_OF_QUESTION = "numberOfQuestion";
    public static final String REQUEST_CODE = "requestCode";
    private static final String SHOW_DIALOG_CLICK_TIME = "showDialogClickTime";
    public static final int FINISH_ROUND_REQUEST_CODE = 41;
    public static final int SHOW_NUMBER_OF_ROUND = 0;
    public static final int SHOW_FINAL_OF_ROUNDS = 1;
    public static final int SHOW_SUMMARY = 2;
    private int waitingActivityRequestCode, secondsLeft;

    QuestionPresenterImpl(Activity activity, QuestionView questionView){
        this.activity = activity;
        this.questionView = questionView;
        setOfRounds = getSetOfRounds();
        setOfQuestions = setOfRounds.getSetOfQuestions().get(setOfRounds.getNumOfRound());
        secondsPerQuestion = setOfQuestions.getSecondsPerQuestion();
        sharedPreferences = activity.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        preferencesEditor = sharedPreferences.edit();
        questionsDao = new QuestionsDaoImpl(activity);
        numberOfCoins = getNumberOfCoins();
        muteEnabled = getMuteEnabled();
        timer = new TimerImpl(secondsPerQuestion*1000+1000, this);
        statisticsDao = new StatisticsDaoImpl(activity);
        timer.startTimer();
        timer.setFinishMethodIsCallable(false);
        timer.stopTimer();
    }

    private SetOfQuestions getSetOfQuestions(){
        return (SetOfQuestions)activity.getIntent().getSerializableExtra(MainPresenterImpl.QUESTION_SET);
    }

    private SetOfRounds getSetOfRounds(){
        return (SetOfRounds)activity.getIntent().getSerializableExtra(MainPresenterImpl.ROUND_SET);
    }

    private int getNumberOfCoins(){
        return sharedPreferences.getInt(COINS, 0);
    }

    private boolean getMuteEnabled(){
        return sharedPreferences.getBoolean(MainPresenterImpl.MUTE_ENABLED, true);
    }

    @Override
    public void onCreate() {
        questionView.setCoinsNumber(numberOfCoins);
        setButtonsActivated();
        addViewsToLinearLayout();
        startNewActivity(SHOW_NUMBER_OF_ROUND);
    }

    @Override
    public void onBackPressed() {
        showCloseActivityDialog();
    }

    private void showCloseActivityDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Wyjdź z gry");
        builder.setMessage("Czy na pewno chcesz zakończyć bieżącą grę?");
        builder.setPositiveButton("Wyjdź", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                closeTimersAndActivity();
            }
        }).setNegativeButton("Anuluj", null);
        builder.show();
    }

    private void closeTimersAndActivity(){
        if(transitionTimer!=null) {
            transitionTimer.setFinishMethodIsCallable(false);
            transitionTimer.stopTimer();
        }
        timer.setFinishMethodIsCallable(false);
        timer.stopTimer();
        activity.finish();
    }

    private void setButtonsActivatedBooleans(boolean addSecondsBtnActivated, boolean fiftyFiftyBtnActivated){
        this.addSecondsBtnActivated = addSecondsBtnActivated;
        this.fiftyFiftyBtnActivated = fiftyFiftyBtnActivated;
    }

    private void addViewsToLinearLayout(){
        for(Question question: setOfQuestions.getQuestions()){
            View view = new View(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16,16, 0.0f);
            params.setMargins(10,1,10,0);
            view.setLayoutParams(params);
            view.setBackground(activity.getResources().getDrawable(R.drawable.circle_button_non_activated));
            questionView.addViewToLinearLayout(view);
        }
    }

    int positionOfZero, positionOfOne;

    @Override
    public void onFiftyFiftyBtnClicked() {
        if(fiftyFiftyBtnActivated){
            saveLifebuoyStatistic();
            disactivateFiftyFiftyBtn();
            numberOfCoins = numberOfCoins - 5;
            saveNumberOfCoins(numberOfCoins);
            questionView.setCoinsNumber(numberOfCoins);
            if(setOfQuestions.getQuestions().get(numberOfQuestion).getTypeOfQuestion() == 0){
                chooseAnswerPresenter.disappearTwoUncorrectAnswers();
            }else{
                positionOfZero = arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().indexOf(0);
                positionOfOne = arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().indexOf(1);
                arrangeAnswerFragment.setFiftyFiftyBtnClicked(true);
                setTwoFirstCorrectAnswersBlockedUp();
                arrangeAnswerRowPresenter.setTwoFirstCorrectAnswersBlockedUp();
                arrangeAnswerFragment.getAdapter().notifyItemChanged(0);
                arrangeAnswerFragment.getAdapter().notifyItemChanged(1);
            }
        }
    }

    private void setTwoFirstCorrectAnswersBlockedUp(){
        arrangeAnswerFragment.getAdapter().notifyItemMoved(positionOfZero,0);
        Integer integer = arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(positionOfZero);
        arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().remove(positionOfZero);
        arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().add(0, integer);

        positionOfOne = arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().indexOf(1);
        arrangeAnswerFragment.getAdapter().notifyItemMoved(positionOfOne, 1);
        Integer integerTwo = arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(positionOfOne);
        arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().remove(positionOfOne);
        arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers().add(1, integerTwo);
    }

    @Override
    public void onAddSecondsBtnClicked() {
        if(addSecondsBtnActivated){
            saveLifebuoyStatistic();
            disactivateAddSecondsBtn();
            numberOfCoins = numberOfCoins - 3;
            saveNumberOfCoins(numberOfCoins);
            startNewTimer(timer.getSeconds()+20);
            questionView.setCoinsNumber(numberOfCoins);
        }
    }

    private void saveLifebuoyStatistic(){
        if(!lifebuoyStatisticSaved) {
            statisticsDao.saveLifebuoyStatistics();
            lifebuoyStatisticSaved = true;
        }
    }

    @Override
    public void progressTextClicked() {
        if(progressTextEnabled) {
            timer.stopTimer();
            onFinish();
            progressTextEnabled = false;
        }
    }

    @Override
    public void onAnswerChoosed(boolean correct) {
        timer.stopTimer();
        disactivateButtons();
        answersClickable = false;
        setOfQuestions.getAnswers().get(numberOfQuestion).setTimeLeft(secondsLeft);
        questionView.animateCorrectness(numberOfQuestion, correct);
        transitionTimer = new TimerImpl(1000, newActivityTimerReact);
        transitionTimer.startTimer();
//        playSoundIfMuteDisabled(correct);
    }

    private void playSoundIfMuteDisabled(boolean correct){
        if(!muteEnabled){
            MediaPlayer mediaPlayer;
            if(correct) mediaPlayer = MediaPlayer.create(activity, R.raw.corrects);
            else mediaPlayer = MediaPlayer.create(activity, R.raw.wrongs);
            mediaPlayer.start();
        }
    }

    @Override
    public boolean getAnswersClickable() {
        return answersClickable;
    }

    @Override
    public void showNextQuestion() {
        answersClickable = true;
        progressTextEnabled = true;
        lifebuoyStatisticSaved = false;
        setOfQuestions = setOfRounds.getSetOfQuestions().get(setOfRounds.getNumOfRound());
        questionsDao.addShowedTimeToQuestion(setOfQuestions.getQuestions().get(numberOfQuestion).getId());
        questionView.setQuestion(setOfQuestions.getQuestions().get(numberOfQuestion).getQuestion());
        setButtonsActivated();
        questionView.setTimeProgress(secondsPerQuestion*1000, secondsPerQuestion);
        applyFragmentAndStartNewTimer();
    }

    @Override
    public void showNextRound() {
        numberOfQuestion = 0;
        setOfRounds.setNumOfRound(setOfRounds.getNumOfRound()+1);
        startNewActivity(SHOW_NUMBER_OF_ROUND);
    }

    @Override
    public void onResume() {
        if(waitingToStartActivity){
            waitingToStartActivity = false;
            startNewActivity(waitingActivityRequestCode);
        }
    }

    private void setButtonsActivated(){
        if(numberOfCoins>=3 && numberOfCoins<5){
            questionView.setAddSecondsBtnActivated(true);
            questionView.setFiftyFiftyBtnActivated(false);
            setButtonsActivatedBooleans(true, false);
        }else if(numberOfCoins>=5){
            questionView.setAddSecondsBtnActivated(true);
            questionView.setFiftyFiftyBtnActivated(true);
            setButtonsActivatedBooleans(true, true);
        }else{
            questionView.setAddSecondsBtnActivated(false);
            questionView.setFiftyFiftyBtnActivated(false);
            setButtonsActivatedBooleans(false, false);
        }
    }

    private void startNewTimer(int time){
        timer.setFinishMethodIsCallable(false);
        timer.stopTimer();
        timer = new TimerImpl(time*1000,this);
        timer.startTimer();
    }

    private void applyFragmentAndStartNewTimer(){
        if(setOfQuestions.getQuestions().get(numberOfQuestion).getTypeOfQuestion() == 0){
            ChooseAnswerFragment chooseAnswerFragment = new ChooseAnswerFragment();
            chooseAnswerFragment.setSetOfQuestions(setOfQuestions);
            chooseAnswerFragment.setNumberOfQuestion(numberOfQuestion);
            chooseAnswerFragment.setQuestionPresenter(this);
            questionView.applyFragment(chooseAnswerFragment);
            startNewTimer(secondsPerQuestion+1);
        }else{
            if(sharedPreferences.getBoolean(SHOW_DIALOG_CLICK_TIME, true))
                showDialogClickTime();
            else
                startNewTimer(secondsPerQuestion+1);
            ArrangeAnswerFragment arrangeAnswerFragment = new ArrangeAnswerFragment();
            arrangeAnswerFragment.setProperties(setOfQuestions, numberOfQuestion);
            arrangeAnswerFragment.setQuestionPresenter(this);
            questionView.applyFragment(arrangeAnswerFragment);
            this.arrangeAnswerFragment = arrangeAnswerFragment;
        }
    }

    private void showDialogClickTime(){
        questionView.setQuestion("Uporządkuj...");
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_click_time, null);
        CheckBox showAgainCheckBox = view.findViewById(R.id.show_again_checkbox);
        showAgainCheckBox.setOnCheckedChangeListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Gdy już ułożysz płytki w odpowiedniej kolejności kliknij na czas aby go zatrzymać");
        builder.setView(view);
        builder.setPositiveButton("Rozumiem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                startNewTimer(secondsPerQuestion+1);
                questionView.setQuestion(setOfQuestions.getQuestions().get(numberOfQuestion).getQuestion());
            }
        });
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void saveNumberOfCoins(int numberOfCoins){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COINS, numberOfCoins);
        editor.apply();
    }

    @Override
    public void onTick(long l) {
        questionView.setTimeProgress((int)l, secondsPerQuestion);
        secondsLeft = (int)l/1000;
    }

    @Override
    public void onFinish() {
        showCorrectAnswer();
    }

    private void showCorrectAnswer(){
        if(setOfQuestions.getQuestions().get(numberOfQuestion).getTypeOfQuestion() == 0 &&
                chooseAnswerPresenter!=null){
            chooseAnswerPresenter.showCorrectAnswer();
            onAnswerChoosed(false);
        }else{
            setOfQuestions.getAnswers().get(numberOfQuestion).setChoosedAnswers
                    (arrangeAnswerRowPresenter.getAnswers().get(numberOfQuestion).getSetOfAnswers());
            arrangeAnswerFragment.getAdapter().notifyDataSetChanged();
            arrangeAnswerRowPresenter.showCorrectAnswers();
        }
    }

    private TimerReact newActivityTimerReact = new TimerReact() {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            numberOfQuestion++;
            if(numberOfQuestion<setOfQuestions.getQuestions().size())
                startNewActivityIfNotPaused(SHOW_NUMBER_OF_ROUND);
            else if(setOfRounds.getNumOfRound()<setOfRounds.getSetOfQuestions().size()-1){
                startNewActivityIfNotPaused(FINISH_ROUND_REQUEST_CODE);
            } else {
                startNewActivityIfNotPaused(SHOW_FINAL_OF_ROUNDS);
            }
        }
    };

    @Override
    public void startNewActivityIfNotPaused(int requestCode){
        if (!questionView.getActivityPaused())
            if(requestCode == FINISH_ROUND_REQUEST_CODE) startFinishRoundActivity();
            else startNewActivity(requestCode);
        else {
            waitingToStartActivity = true;
            waitingActivityRequestCode = requestCode;
        }
    }

    private void startNewActivity(int requestCode){
        Intent intent;
        if(requestCode == SHOW_SUMMARY) intent = new Intent(activity, SummaryActivity.class);
        else intent = new Intent(activity, RoundActivity.class);
        intent.putExtra(MainPresenterImpl.QUESTION_SET, setOfQuestions);
        intent.putExtra(MainPresenterImpl.ROUND_SET, setOfRounds);
        intent.putExtra(NUMBER_OF_QUESTION, numberOfQuestion);
        intent.putExtra(REQUEST_CODE, requestCode);
        activity.startActivityForResult(intent, requestCode);
        if(requestCode==SHOW_SUMMARY) activity.finish();

    }

    private void startFinishRoundActivity(){
        Intent intent = new Intent(activity, FinishRoundActivity.class);
        intent.putExtra(MainPresenterImpl.ROUND_SET, setOfRounds);
        intent.putExtra(REQUEST_CODE, FINISH_ROUND_REQUEST_CODE);
        activity.startActivityForResult(intent, FINISH_ROUND_REQUEST_CODE);
    }

    private void disactivateButtons(){
        disactivateAddSecondsBtn();
        disactivateFiftyFiftyBtn();
    }

    private void disactivateAddSecondsBtn(){
        addSecondsBtnActivated = false;
        questionView.setAddSecondsBtnActivated(false);
    }

    private void disactivateFiftyFiftyBtn(){
        fiftyFiftyBtnActivated = false;
        questionView.setFiftyFiftyBtnActivated(false);
    }

    @Override
    public void setChooserAnswerPresenter(ChooseAnswerPresenter chooserAnswerPresenter) {
        this.chooseAnswerPresenter = chooserAnswerPresenter;
    }

    @Override
    public void setArrangeAnswerRowPresenter(ArrangeAnswerRowPresenter arrangeAnswerRowPresenter) {
        this.arrangeAnswerRowPresenter = arrangeAnswerRowPresenter;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        preferencesEditor.putBoolean(SHOW_DIALOG_CLICK_TIME, !b);
        preferencesEditor.apply();
    }
}
