package pl.ziemiakopernika.ziemiakopernika.choose.answer;

import android.view.View;
import android.widget.Button;

import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenter;

public interface ChooseAnswerView {

    Button getButton(int number);

    int getNumber(View view);

    SetOfQuestions getSetOfQuestion();

    QuestionPresenter getQuestionPresenter();

    int getNumberOfQuestion();
}
