package pl.ziemiakopernika.ziemiakopernika.summary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.LinearLayout;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.answer.checker.AnswerChecker;
import pl.ziemiakopernika.ziemiakopernika.answer.checker.AnswerCheckerImpl;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDao;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.redinfo.RedInfoActivity;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsBottomSheet;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsDao;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.summary.recycler.AnswerPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.summary.recycler.AnswerRowAdapter;
import pl.ziemiakopernika.ziemiakopernika.timer.Timer;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerImpl;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerReact;

public class SummaryPresenterImpl implements SummaryPresenter {

    private SummaryActivity activity;
    private SummaryView summaryView;
    private StatisticsDao statisticsDao;
    private QuestionsDao questionsDao;
    private SetOfQuestions setOfQuestions;
    private AnswerChecker answerChecker;
    private int correctAnswers, uncorrectAnswers, totalCoins,
             percentageCorrectness, timeLeft;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private int secondsPerQuestion = 15;
    private int numberOfQuestions = 5;


    public SummaryPresenterImpl(Activity activity, SummaryView summaryView){
        this.activity = (SummaryActivity)activity;
        this.summaryView = summaryView;
        setOfQuestions = getSetOfQuestions();
        questionsDao = new QuestionsDaoImpl(activity);
        answerChecker = new AnswerCheckerImpl(setOfQuestions);
        statisticsDao = new StatisticsDaoImpl(activity);
        timeLeft = getTimeLeft();
        sharedPreferences = activity.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private SetOfQuestions getSetOfQuestions(){
        return (SetOfQuestions)activity.getIntent().getSerializableExtra(MainPresenterImpl.QUESTION_SET);
    }

    @Override
    public void onCreate() {
        addViewsAndSetNumberOfAnswers();
        int coinsForTime = getCoinsForTime(timeLeft);
        int coinsForCorrectAnswers = getCoinsForCorrectAnswers(correctAnswers);
        percentageCorrectness = getPercentCorrectness();
        totalCoins = coinsForTime + coinsForCorrectAnswers;
        summaryView.setRecyclerViewAdapter(new AnswerRowAdapter(new AnswerPresenterImpl(setOfQuestions, activity), activity));
        summaryView.setSummaryBackgroundColor(getBackgroundColor(correctAnswers, uncorrectAnswers));
        summaryView.setCoinBtnsDrawable(getDrawableColor(correctAnswers, uncorrectAnswers));
        summaryView.setStatisticsBtnColor(getStatisticsColor(correctAnswers, uncorrectAnswers));
        summaryView.setBadgeText(getBadge(percentageCorrectness));
        summaryView.setTimeLeftText("Pozostały czas: " + timeLeft + " sekund");
        summaryView.setAnswersProportions("Poprawność odpowiedzi: " + correctAnswers + ":" + uncorrectAnswers);
        summaryView.setCoinsForTimeNumber(coinsForTime);
        summaryView.setCoinsForCorrectAnswers(coinsForCorrectAnswers);
        summaryView.setTotalCoinsBtn(totalCoins);
        saveStatistics();
        setCongratulationsText(totalCoins);
        addCoins(totalCoins);
    }

    private void addViewsAndSetNumberOfAnswers(){
        for(int i =0; i< setOfQuestions.getNumOfQuestion(); i++) {
            View view = getDefaultView();
            if(answerChecker.answerIsCorrect(i)) {
                view.setBackground(activity.getResources().getDrawable(R.drawable.circle_button_green));
                correctAnswers++;
            }else {
                view.setBackground(activity.getResources().getDrawable(R.drawable.circle_button_red));
                uncorrectAnswers++;
            }
            summaryView.addViewToRoundLayout(view);
        }
    }

    private View getDefaultView(){
        View view = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25,25, 0.0f);
        params.setMargins(10,1,10,0);
        view.setLayoutParams(params);
        view.setBackground(activity.getResources().getDrawable(R.drawable.circle_button_non_activated));
        return view;
    }

    private int getPercentCorrectness(){
        return (correctAnswers*100)/(correctAnswers+uncorrectAnswers);
    }

    private String getBadge(int percentCorrectness){
        if(percentCorrectness<=20)
            return "NIEDOSTATECZNY";
        else if(percentCorrectness <=40)
            return "DOPUSZCZAJĄCY";
        else if(percentCorrectness <=50)
            return "DOSTATECZNY";
        else if(percentCorrectness <=70)
            return "DOBRY";
        else if(percentCorrectness<=90)
            return "BARDZO DOBRY";
        else
            return "CELUJĄCY";
    }

    private int getTimeLeft(){
        int timeLeft = 0;
        for(Answer answer: setOfQuestions.getAnswers())
            timeLeft = timeLeft + answer.getTimeLeft();
        return timeLeft;
    }

    private int getCoinsForTime(int seconds){
        return seconds/10;
    }

    private int getCoinsForCorrectAnswers(int correctAnswers){
        return correctAnswers*3;
    }

    private int getBackgroundColor(int correctAnswers, int uncorrectAnswers){
        if(correctAnswers>uncorrectAnswers)
            return activity.getResources().getColor(R.color.colorGreen);
        else if(correctAnswers==uncorrectAnswers)
            return activity.getResources().getColor(R.color.colorAccent);
        else
            return activity.getResources().getColor(R.color.colorPrimary);
    }

    private Drawable getDrawableColor(int correctAnswers, int uncorrectAnswers){
        if(correctAnswers>uncorrectAnswers)
            return activity.getResources().getDrawable(R.drawable.rectangle_button_green);
        else if(correctAnswers==uncorrectAnswers)
            return activity.getResources().getDrawable(R.drawable.rectangle_button_yellow);
        else
            return activity.getResources().getDrawable(R.drawable.rectangle_button_red);
    }

    private int getStatisticsColor(int correctAnswers, int uncorrectAnswers){
        if(correctAnswers>uncorrectAnswers)
            return activity.getResources().getColor(R.color.colorGreen);
        else if(correctAnswers==uncorrectAnswers)
            return activity.getResources().getColor(R.color.colorAccent);
        else
            return activity.getResources().getColor(R.color.colorPrimaryDark);
    }

    private void addCoins(int newCoins){
        int coins = sharedPreferences.getInt(QuestionPresenterImpl.COINS, 0);
        editor.putInt(QuestionPresenterImpl.COINS, coins + newCoins);
        editor.commit();
    }

    private void setCongratulationsText(int totalCoins){
        if(totalCoins>0)
            summaryView.setCongratulationsText("Gratulacje, wygrywasz " + totalCoins + " monet!");
        else
            summaryView.setCongratulationsText("Niestety tym razem nie udało Ci się wygrać. Próbuj dalej!");
    }

    private void saveStatistics(){
        statisticsDao.saveGamesPlayedStatistics();
        statisticsDao.saveCorrectAnswersNumberStatistics(correctAnswers);
        statisticsDao.saveEarnedCoinsStatistics(totalCoins);
        statisticsDao.savePercentageCorrectnessStatistics(percentageCorrectness);
        statisticsDao.saveTimeLeftStatistics(timeLeft);
        statisticsDao.saveUncorrectAnswersNumberStatistics(uncorrectAnswers);
    }

    @Override
    public void onBackPressed() {
        summaryView.setSummaryBackgroundColor(activity.getResources().getColor(android.R.color.transparent));
    }

    @Override
    public void onShareBtnClicked() {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "Wygrałem " + totalCoins + " monet! Pobierz aplikacje do quizowania " +
                "Ziemia Kopernika i spróbuj mnie przebić!";
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        activity.startActivity(Intent.createChooser(intent, "Udostępnij"));
    }

    @Override
    public void onPlayAgainBtnClicked(View view) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, view, "transition");
        SetOfQuestions setOfQuestions = getNewSetOfQuestions();
        Intent intent = new Intent(activity, RedInfoActivity.class);
        intent.putExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_X, getRevealX(view));
        intent.putExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_Y, getRevealY(view));
        intent.putExtra(MainPresenterImpl.QUESTION_SET, setOfQuestions);
        ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
        startTimerToCloseActivity();
    }

    private void startTimerToCloseActivity(){
        Timer timer = new TimerImpl(3000, new TimerReact() {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                activity.finish();
            }
        });
        timer.startTimer();
    }

    @Override
    public void onStatisticsBtnClicked() {
        StatisticsBottomSheet bottomSheet = new StatisticsBottomSheet();
        bottomSheet.show(activity.getSupportFragmentManager(), "fragmentManager");
    }

    private SetOfQuestions getNewSetOfQuestions(){
        return new SetOfQuestions(numberOfQuestions,secondsPerQuestion,
                questionsDao.getRandomQustions(numberOfQuestions), questionsDao.getRandomAnswers(numberOfQuestions));
    }

    private int getRevealX(View view){
        return (int) (view.getX() + view.getWidth() / 2);
    }

    private int getRevealY(View view){
        return (int) (view.getY() + view.getHeight() / 2);
    }


}
