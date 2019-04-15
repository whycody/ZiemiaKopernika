package pl.ziemiakopernika.ziemiakopernika.summary;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenterImpl;

public class SummaryPresenterImpl implements SummaryPresenter {

    private Activity activity;
    private SummaryView summaryView;
    private SetOfQuestions setOfQuestions;
    private int correctAnswers, uncorrectAnswers, coinsForTime, coinsForCorrectAnswers, timeLeft;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SummaryPresenterImpl(Activity activity, SummaryView summaryView){
        this.activity = activity;
        this.summaryView = summaryView;
        setOfQuestions = getSetOfQuestions();
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
        summaryView.setSummaryBackgroundColor(getBackgroundColor(correctAnswers, uncorrectAnswers));
        coinsForTime = getCoinsForTime(timeLeft);
        coinsForCorrectAnswers = getCoinsForCorrectAnswers(correctAnswers);
        summaryView.setBadgeText(getBadge(getPercentCorrectness()));
        summaryView.setTimeLeftText("Pozostały czas: " + timeLeft + " sekund");
        summaryView.setAnswersProportions("Poprawność odpowiedzi: " + correctAnswers + ":" + uncorrectAnswers);
        summaryView.setCoinsForTimeNumber(coinsForTime);
        summaryView.setCoinsForCorrectAnswers(coinsForCorrectAnswers);
        int totalCoins = coinsForTime + coinsForCorrectAnswers;
        summaryView.setTotalCoinsBtn(totalCoins);
        summaryView.setCongratulationsText("Gratulacje, wygrywasz " + totalCoins + " monet!");
        addCoins(totalCoins);
    }

    private void addViewsAndSetNumberOfAnswers(){
        for(int i =0; i< setOfQuestions.getNumOfQuestion(); i++) {
            View view = getDefaultView();
            int correctAnswer = setOfQuestions.getAnswers().get(i).getSetOfAnswers().get(0);
            int choosedAnswer = setOfQuestions.getAnswers().get(i).getChoosedAnswer();
            if(choosedAnswer==correctAnswer) {
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

    private void addCoins(int newCoins){
        int coins = sharedPreferences.getInt(QuestionPresenterImpl.COINS, 0);
        editor.putInt(QuestionPresenterImpl.COINS, coins + newCoins);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        summaryView.setSummaryBackgroundColor(activity.getResources().getColor(android.R.color.transparent));
    }


}
