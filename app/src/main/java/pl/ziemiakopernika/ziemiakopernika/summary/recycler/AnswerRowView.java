package pl.ziemiakopernika.ziemiakopernika.summary.recycler;

import android.text.Spannable;
import android.view.View;

public interface AnswerRowView {

    void setQuestionText(String text);

    void setAnswerOneText(Spannable spannable);

    void setAnswerTwoText(Spannable spannable);

    void setAnswerThreeText(Spannable spannable);

    void setAnswerFourText(Spannable spannable);

    View getAnswerByNumber(int number);

}
