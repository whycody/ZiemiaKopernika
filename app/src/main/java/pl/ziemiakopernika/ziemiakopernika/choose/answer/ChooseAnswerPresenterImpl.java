package pl.ziemiakopernika.ziemiakopernika.choose.answer;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenter;

public class ChooseAnswerPresenterImpl implements ChooseAnswerPresenter{

    private Activity activity;
    private ChooseAnswerView chooseAnswerView;
    private SetOfQuestions setOfQuestions;
    private QuestionPresenter questionPresenter;
    private int numberOfQuestion, correctAnswer;
    private boolean answerChoosed;

    ChooseAnswerPresenterImpl(Activity activity, ChooseAnswerView chooseAnswerView){
        this.activity = activity;
        this.chooseAnswerView = chooseAnswerView;
        this.setOfQuestions = chooseAnswerView.getSetOfQuestion();
        this.numberOfQuestion = chooseAnswerView.getNumberOfQuestion();
        this.questionPresenter = chooseAnswerView.getQuestionPresenter();
    }

    @Override
    public void onCreate() {
        correctAnswer = setOfQuestions.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(0);
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
        if(!answerChoosed) {
            int choosedAnswer = chooseAnswerView.getNumber(view);
            if (choosedAnswer == correctAnswer)
                questionPresenter.onAnswerChoosed(true);
            else {
                chooseAnswer((Button)view, false);
                questionPresenter.onAnswerChoosed(false);
            }
            setOfQuestions.getAnswers().get(numberOfQuestion).setChoosedAnswer(choosedAnswer);
            Button correctBtn = chooseAnswerView.getButton(correctAnswer);
            chooseAnswer(correctBtn, true);
            answerChoosed = true;
        }
    }

    private void chooseAnswer(Button button, boolean correct){
        if(correct)
            button.setBackground(activity.getResources().getDrawable(R.drawable.gray_button_correct_answer));
        else
            button.setBackground(activity.getResources().getDrawable(R.drawable.gray_button_wrong_answer));
        button.setTextColor(activity.getResources().getColor(android.R.color.white));
    }

}
