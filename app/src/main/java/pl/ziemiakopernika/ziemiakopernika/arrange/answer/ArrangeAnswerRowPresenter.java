package pl.ziemiakopernika.ziemiakopernika.arrange.answer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.Question;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenter;

public interface ArrangeAnswerRowPresenter {

    void onBindViewHolder(@NonNull ArrangeAnswerRecyclerHolder arrangeAnswerRecyclerHolder, int i);

    int getItemCount();

    void setItemTouchHelper(ItemTouchHelper itemTouchHelper);

    void setQuestionPresenter(QuestionPresenter questionPresenter);

    void setActivity(Activity activity);

    ArrayList<Answer> getAnswers();

    void showCorrectAnswers();
}
