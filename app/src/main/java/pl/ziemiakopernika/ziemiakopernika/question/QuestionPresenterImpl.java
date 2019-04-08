package pl.ziemiakopernika.ziemiakopernika.question;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.choose.answer.ChooseAnswerFragment;
import pl.ziemiakopernika.ziemiakopernika.choose.answer.ChooseAnswerPresenter;
import pl.ziemiakopernika.ziemiakopernika.main.MainActivity;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.Question;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.round.RoundActivity;
import pl.ziemiakopernika.ziemiakopernika.timer.Timer;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerImpl;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerReact;

public class QuestionPresenterImpl implements QuestionPresenter, TimerReact {

    private Activity activity;
    private QuestionView questionView;
    private SetOfQuestions setOfQuestions;
    private SharedPreferences sharedPreferences;
    private Timer timer, transitionTimer;

    private ChooseAnswerPresenter chooseAnswerPresenter;

    private int numberOfCoins, numberOfQuestion, secondsPerQuestion;
    private boolean fiftyFiftyBtnActivated, addSecondsBtnActivated, waitingToStartActivity;
    private boolean answersClickable = true;

    private static final String COINS = "coins";
    public static final String NUMBER_OF_QUESTION = "numberOfQuestion";
    public static final String REQUEST_CODE = "requestCode";
    public static final int SHOW_NUMBER_OF_ROUND = 0;
    public static final int SHOW_FINAL_OF_ROUNDS = 1;
    private int waitingActivityRequestCode;

    QuestionPresenterImpl(Activity activity, QuestionView questionView){
        this.activity = activity;
        this.questionView = questionView;
        this.setOfQuestions = getSetOfQuestions();
        secondsPerQuestion = setOfQuestions.getSecondsPerQuestion();
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        numberOfCoins = getNumberOfCoins();
        timer = new TimerImpl(secondsPerQuestion*1000+1000, this);
        timer.startTimer();
    }

    private SetOfQuestions getSetOfQuestions(){
        return (SetOfQuestions)activity.getIntent().getSerializableExtra(MainPresenterImpl.QUESTION_SET);
    }

    private int getNumberOfCoins(){
        return sharedPreferences.getInt(COINS, 50);
    }

    @Override
    public void onCreate() {
        questionView.setQuestion(setOfQuestions.getQuestions().get(numberOfQuestion).getQuestion());
        questionView.setCoinsNumber(numberOfCoins);
        applyFragment();
        setButtonsActivated();
        addViewsToLinearLayout();
        startNewActivity(SHOW_NUMBER_OF_ROUND);
    }

    @Override
    public void onBackPressed() {
        if(transitionTimer!=null) {
            transitionTimer.setFinishMethodIsCallable(false);
            transitionTimer.stopTimer();
        }
        timer.setFinishMethodIsCallable(false);
        timer.stopTimer();
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

    @Override
    public void onFiftyFiftyBtnClicked() {
        if(fiftyFiftyBtnActivated){
            disactivateFiftyFiftyBtn();
            numberOfCoins = numberOfCoins - 5;
            saveNumberOfCoins(numberOfCoins);
            questionView.setCoinsNumber(numberOfCoins);
        }
    }

    @Override
    public void onAddSecondsBtnClicked() {
        if(addSecondsBtnActivated){
            disactivateAddSecondsBtn();
            numberOfCoins = numberOfCoins - 3;
            saveNumberOfCoins(numberOfCoins);
            startNewTimer(timer.getSeconds()+20);
            questionView.setCoinsNumber(numberOfCoins);
        }
    }

    @Override
    public void onAnswerChoosed(boolean correct) {
        timer.stopTimer();
        disactivateButtons();
        answersClickable = false;
        questionView.animateCorrectness(numberOfQuestion, correct);
        transitionTimer = new TimerImpl(1000, newActivityTimerReact);
        transitionTimer.startTimer();
    }

    @Override
    public boolean getAnswersClickable() {
        return answersClickable;
    }

    @Override
    public void showNextQuestion() {
        answersClickable=true;
        questionView.setQuestion(setOfQuestions.getQuestions().get(numberOfQuestion).getQuestion());
        setButtonsActivated();
        applyFragment();
        startNewTimer(secondsPerQuestion+1);
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

    private void applyFragment(){
        if(setOfQuestions.getQuestions().get(numberOfQuestion).getTypeOfQuestion() == 0){
            ChooseAnswerFragment chooseAnswerFragment = new ChooseAnswerFragment();
            chooseAnswerFragment.setSetOfQuestions(setOfQuestions);
            chooseAnswerFragment.setNumberOfQuestion(numberOfQuestion);
            chooseAnswerFragment.setQuestionPresenter(this);
            questionView.applyFragment(chooseAnswerFragment);
        }
    }

    private void saveNumberOfCoins(int numberOfCoins){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COINS, numberOfCoins);
        editor.apply();
    }

    @Override
    public void onTick(long l) {
        questionView.setTimeProgress((int)l, secondsPerQuestion);
    }

    @Override
    public void onFinish() {
        answersClickable = false;
        disactivateButtons();
        questionView.animateCorrectness(numberOfQuestion, false);
        transitionTimer = new TimerImpl(1500, newActivityTimerReact);
        transitionTimer.startTimer();
        showCorrectAnswer();
    }

    private void showCorrectAnswer(){
        if(setOfQuestions.getQuestions().get(numberOfQuestion).getTypeOfQuestion() == 0 &&
                chooseAnswerPresenter!=null){
            chooseAnswerPresenter.showCorrectAnswer();
        }
    }

    private TimerReact newActivityTimerReact = new TimerReact() {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            numberOfQuestion++;
            if(numberOfQuestion-1<setOfQuestions.getQuestions().size()-1)
                startNewActivityIfNotPaused(SHOW_NUMBER_OF_ROUND);
            else {
                startNewActivityIfNotPaused(SHOW_FINAL_OF_ROUNDS);
                activity.finish();
            }
        }
    };

    private void startNewActivityIfNotPaused(int requestCode){
        if (!questionView.getActivityPaused())
            startNewActivity(requestCode);
        else {
            waitingToStartActivity = true;
            waitingActivityRequestCode = requestCode;
        }
    }

    private void startNewActivity(int requestCode){
        Intent intent = new Intent(activity, RoundActivity.class);
        intent.putExtra(MainPresenterImpl.QUESTION_SET, setOfQuestions);
        intent.putExtra(NUMBER_OF_QUESTION, numberOfQuestion);
        intent.putExtra(REQUEST_CODE, requestCode);
        activity.startActivityForResult(intent, requestCode);
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
}
