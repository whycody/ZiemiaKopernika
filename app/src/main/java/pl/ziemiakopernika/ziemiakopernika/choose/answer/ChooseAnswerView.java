package pl.ziemiakopernika.ziemiakopernika.choose.answer;

import android.widget.Button;

import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenter;

public interface ChooseAnswerView {

    Button getButton(int number);

    SetOfQuestions getSetOfQuestion();

    QuestionPresenter getQuestionPresenter();

    int getNumberOfQuestion();
}
