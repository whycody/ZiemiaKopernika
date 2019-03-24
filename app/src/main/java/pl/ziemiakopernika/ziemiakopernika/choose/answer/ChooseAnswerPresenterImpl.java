package pl.ziemiakopernika.ziemiakopernika.choose.answer;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenter;

public class ChooseAnswerPresenterImpl implements ChooseAnswerPresenter{

    private Activity activity;
    private ChooseAnswerView chooseAnswerView;
    private SetOfQuestions setOfQuestions;
    private QuestionPresenter questionPresenter;
    private int numberOfQuestion, correctAnswer;

    ChooseAnswerPresenterImpl(Activity activity, ChooseAnswerView chooseAnswerView){
        this.activity = activity;
        this.chooseAnswerView = chooseAnswerView;
        this.setOfQuestions = chooseAnswerView.getSetOfQuestion();
        this.numberOfQuestion = chooseAnswerView.getNumberOfQuestion();
        this.questionPresenter = chooseAnswerView.getQuestionPresenter();
    }

    @Override
    public void onCreate() {
        correctAnswer = setOfQuestions.getAnswers().get(numberOfQuestion).getSetOfAnswers().indexOf(0);
        chooseAnswerView.getButton(setOfQuestions.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(0))
                .setText(setOfQuestions.getQuestions().get(numberOfQuestion).getAnswerOne());
        chooseAnswerView.getButton(setOfQuestions.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(1))
                .setText(setOfQuestions.getQuestions().get(numberOfQuestion).getAnswerTwo());
        chooseAnswerView.getButton(setOfQuestions.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(2))
                .setText(setOfQuestions.getQuestions().get(numberOfQuestion).getAnswerThree());
        chooseAnswerView.getButton(setOfQuestions.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(3))
                .setText(setOfQuestions.getQuestions().get(numberOfQuestion).getAnswerFour());
    }

    @Override
    public void onBtnClicked(View view) {
        if(chooseAnswerView.getButton(correctAnswer).equals(view)){
            Button correctBtn = (Button)view;
            correctBtn.setText("DOBRZE!");
            questionPresenter.onAnswerChoosed(true);
            setOfQuestions.getAnswers().get(numberOfQuestion).setChoosedAnswer(correctAnswer);
        }else{
            Button wrongBtn = (Button)view;
            wrongBtn.setText("ŹLE!");
            Button correctBtn = chooseAnswerView.getButton(correctAnswer);
            correctBtn.setText("To powinno byc dobre");
            questionPresenter.onAnswerChoosed(false);
        }
    }

}
