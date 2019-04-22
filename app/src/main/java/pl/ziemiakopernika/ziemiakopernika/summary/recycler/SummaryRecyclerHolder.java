package pl.ziemiakopernika.ziemiakopernika.summary.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class SummaryRecyclerHolder extends RecyclerView.ViewHolder implements AnswerRowView{

    private TextView questionView, answerOneView, answerTwoView, answerThreeView, answerFourView;

    public SummaryRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        questionView = itemView.findViewById(R.id.question_view);
        answerOneView = itemView.findViewById(R.id.answer_one_view);
        answerTwoView = itemView.findViewById(R.id.answer_two_view);
        answerThreeView = itemView.findViewById(R.id.answer_three_view);
        answerFourView = itemView.findViewById(R.id.answer_four_view);
    }

    @Override
    public void setQuestionText(String text) {
        questionView.setText(text);
    }

    @Override
    public void setAnswerOneText(Spannable spannable) {
        answerOneView.setText(spannable);
    }

    @Override
    public void setAnswerTwoText(Spannable spannable) {
        answerTwoView.setText(spannable);
    }

    @Override
    public void setAnswerThreeText(Spannable spannable) {
        answerThreeView.setText(spannable);
    }

    @Override
    public void setAnswerFourText(Spannable spannable) {
        answerFourView.setText(spannable);
    }

    @Override
    public View getAnswerByNumber(int number) {
        switch (number){
            case 0:
                return answerOneView;
            case 1:
                return answerTwoView;
            case 2:
                return answerThreeView;
            default:
                return answerFourView;
        }
    }


}
