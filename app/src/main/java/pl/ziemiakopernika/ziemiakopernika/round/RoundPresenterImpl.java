package pl.ziemiakopernika.ziemiakopernika.round;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenterImpl;

public class RoundPresenterImpl implements RoundPresenter {

    private Activity activity;
    private RoundView roundView;
    private SetOfQuestions setOfQuestions;
    private int numberOfQuestion;

    public RoundPresenterImpl(Activity activity, RoundView roundView){
        this.activity = activity;
        this.roundView = roundView;
        setOfQuestions = getSetOfQuestions();
        numberOfQuestion = getNumberOfQuestion();
    }

    private SetOfQuestions getSetOfQuestions(){
        return (SetOfQuestions)activity.getIntent().getSerializableExtra(MainPresenterImpl.QUESTION_SET);
    }

    private int getNumberOfQuestion(){
        return activity.getIntent().getIntExtra(QuestionPresenterImpl.NUMBER_OF_QUESTION, 0);
    }

    @Override
    public void onCreate() {
        roundView.setRoundText("Runda " + (numberOfQuestion+1));
        addViewsToLinearLayout();
    }

    private void addViewsToLinearLayout(){
        addCorrectAndUncorrectViews();
        addDefaultViews();
    }

    private void addCorrectAndUncorrectViews(){
        for(int i =0; i< numberOfQuestion; i++) {
            View view = getDefaultView();
            int correctAnswer = setOfQuestions.getAnswers().get(i).getSetOfAnswers().get(0);
            int choosedAnswer = setOfQuestions.getAnswers().get(i).getChoosedAnswer();
            if(choosedAnswer==correctAnswer)
                view.setBackground(activity.getResources().getDrawable(R.drawable.circle_button_green));
            else
                view.setBackground(activity.getResources().getDrawable(R.drawable.circle_button_red));
            roundView.addViewToLinearLayout(view);
        }
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
