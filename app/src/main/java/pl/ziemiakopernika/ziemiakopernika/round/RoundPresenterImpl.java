package pl.ziemiakopernika.ziemiakopernika.round;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.LinearLayout;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.answer.checker.AnswerChecker;
import pl.ziemiakopernika.ziemiakopernika.answer.checker.AnswerCheckerImpl;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfRounds;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.summary.SummaryActivity;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerImpl;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerReact;

public class RoundPresenterImpl implements RoundPresenter{

    private Activity activity;
    private RoundView roundView;
    private AnswerChecker answerChecker;
    private SetOfQuestions setOfQuestions;
    private SetOfRounds setOfRounds;
    private int numberOfQuestion, requestCode, balance;

    public RoundPresenterImpl(Activity activity, RoundView roundView){
        this.activity = activity;
        this.roundView = roundView;
        setOfRounds = getSetOfRounds();
        setOfQuestions = setOfRounds.getSetOfQuestions().get(setOfRounds.getNumOfRound());
        answerChecker = new AnswerCheckerImpl(setOfQuestions);
        numberOfQuestion = getNumberOfQuestion();
        requestCode = getRequestCode();
    }

    private int getRequestCode() {
        return activity.getIntent().getIntExtra(QuestionPresenterImpl.REQUEST_CODE, 0);
    }

    private SetOfQuestions getSetOfQuestions(){
        return (SetOfQuestions)activity.getIntent().getSerializableExtra(MainPresenterImpl.QUESTION_SET);
    }

    private SetOfRounds getSetOfRounds(){
        return (SetOfRounds) activity.getIntent().getSerializableExtra(MainPresenterImpl.ROUND_SET);
    }

    private int getNumberOfQuestion(){
        return activity.getIntent().getIntExtra(QuestionPresenterImpl.NUMBER_OF_QUESTION, 0);
    }

    @Override
    public void onCreate() {
        if(requestCode ==0) {
            if (numberOfQuestion == 0)
                roundView.setRoundText("Runda " + (setOfRounds.getNumOfRound() + 1));
            else roundView.setRoundText("Pytanie " + (numberOfQuestion + 1));
        }else if(requestCode==3){
            roundView.setRoundText("Pytanie " + (numberOfQuestion + 1));
        }else roundView.setRoundText("Koniec gry");
        addViewsToLinearLayout();
    }

    @Override
    public void onFinish() {
        activity.setResult(Activity.RESULT_OK);
        if(requestCode ==1) startSummaryActivity(roundView.getSummaryView());
        else if(requestCode == 0 && numberOfQuestion == 0) {
            startAgainActivity();
            activity.finish();
        }
        else activity.finish();
    }

    private void startAgainActivity(){
        Intent intent = new Intent(activity, RoundActivity.class);
        intent.putExtra(MainPresenterImpl.ROUND_SET, setOfRounds);
        intent.putExtra(QuestionPresenterImpl.REQUEST_CODE, 3);
        activity.startActivityForResult(intent, 3);
    }

    private void startSummaryActivity(View view){
        Intent intent = new Intent(activity, SummaryActivity.class);
        intent.putExtra(MainPresenterImpl.QUESTION_SET, setOfQuestions);
        intent.putExtra(MainPresenterImpl.ROUND_SET, setOfRounds);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                (activity, view, ViewCompat.getTransitionName(view));
        activity.startActivity(intent, options.toBundle());
        new TimerImpl(2000, new TimerReact() {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                activity.finish();
            }
        }).startTimer();
    }

    private void addViewsToLinearLayout(){
        addCorrectAndUncorrectViews();
        if(numberOfQuestion!=0 || setOfRounds.getNumOfRound()!=0) setSummaryViewDrawable();
        addDefaultViews();
    }

    private void addCorrectAndUncorrectViews(){
        for(int i =0; i< numberOfQuestion; i++) {
            View view = getDefaultView();
            if(answerChecker.answerIsCorrect(i)){
                view.setBackground(activity.getResources().getDrawable(R.drawable.circle_button_green));
                balance++;
            }else{
                view.setBackground(activity.getResources().getDrawable(R.drawable.circle_button_red));
                balance--;
            }
            roundView.addViewToLinearLayout(view);
        }
        setBalanceOfAllSetOfQuestions();
    }

    private void setBalanceOfAllSetOfQuestions(){
        if(setOfRounds.getNumOfRound()!=0){
            for(int i=0; i<setOfRounds.getNumOfRound(); i++){
                SetOfQuestions setOfQuestions = setOfRounds.getSetOfQuestions().get(i);
                AnswerChecker newAnswerChecker = new AnswerCheckerImpl(setOfQuestions);
                for(int j =0; j< setOfQuestions.getNumOfQuestion(); j++) {
                    if(newAnswerChecker.answerIsCorrect(j)) balance++;
                    else balance--;
                }
            }
        }
    }

    private void setSummaryViewDrawable(){
        if(balance>0)
            roundView.setSummaryViewBackground(activity.getDrawable(R.drawable.circle_button_green));
        else if(balance == 0)
            roundView.setSummaryViewBackground(activity.getDrawable(R.drawable.circle_button_yellow));
        else
            roundView.setSummaryViewBackground(activity.getDrawable(R.drawable.circle_button_red));
    }

    private void addDefaultViews(){
        for(int i = numberOfQuestion+1; i<=setOfQuestions.getQuestions().size(); i++){
            roundView.addViewToLinearLayout(getDefaultView());
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


}
