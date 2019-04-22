package pl.ziemiakopernika.ziemiakopernika.choose.answer;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    public static final int NOT_CHOOSED = 4;

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
        questionPresenter.setChooserAnswerPresenter(this);
    }

    @Override
    public void onBtnClicked(View view) {
        if(questionPresenter.getAnswersClickable()) {
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

    @Override
    public void showCorrectAnswer() {
        setOfQuestions.getAnswers().get(numberOfQuestion).setChoosedAnswer(NOT_CHOOSED);
        chooseAnswer(chooseAnswerView.getButton(correctAnswer), true);
    }

    @Override
    public void disappearTwoUncorrectAnswers() {
        Button answerOneBtn = chooseAnswerView.getButton(getIndexOfAnswer(1));
        Button answerTwoBtn = chooseAnswerView.getButton(getIndexOfAnswer(2));
        Animation fadeOutAnim = AnimationUtils.loadAnimation(activity, R.anim.alpha_fade_in);
        answerOneBtn.startAnimation(fadeOutAnim);
        answerTwoBtn.startAnimation(fadeOutAnim);
        answerOneBtn.setOnClickListener(null);
        answerTwoBtn.setOnClickListener(null);
    }

    private int getIndexOfAnswer(int answer){
        return setOfQuestions.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(answer);
    }

    private void chooseAnswer(Button button, boolean correct){
        if(correct)
            button.setBackground(activity.getResources().getDrawable(R.drawable.gray_button_correct_answer));
        else
            button.setBackground(activity.getResources().getDrawable(R.drawable.gray_button_wrong_answer));
        button.setTextColor(activity.getResources().getColor(android.R.color.white));
    }

}
