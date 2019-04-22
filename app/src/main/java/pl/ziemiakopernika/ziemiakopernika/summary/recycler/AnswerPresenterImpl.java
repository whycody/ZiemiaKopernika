package pl.ziemiakopernika.ziemiakopernika.summary.recycler;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.choose.answer.ChooseAnswerPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;

public class AnswerPresenterImpl implements AnswerPresenter {

    private SetOfQuestions setOfQuestions;
    private ArrayList<Answer> answersList;
    private Activity activity;

    public AnswerPresenterImpl(SetOfQuestions setOfQuestions, Activity activity){
        this.setOfQuestions = setOfQuestions;
        this.answersList = setOfQuestions.getAnswers();
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryRecyclerHolder summaryRecyclerHolder, int i) {
        Answer currentAnswer = answersList.get(i);
        summaryRecyclerHolder.setQuestionText(setOfQuestions.getQuestions().get(i).getQuestion());
        int correctAnswer = currentAnswer.getSetOfAnswers().get(0);
        int choosedAnswer = currentAnswer.getChoosedAnswer();
        TextView correctAnswerView = (TextView) summaryRecyclerHolder.getAnswerByNumber(correctAnswer);
        TextView choosedAnswerView = (TextView) summaryRecyclerHolder.getAnswerByNumber(choosedAnswer);

        for(int numberOfQuestion = 0; numberOfQuestion<4; numberOfQuestion++){
            if(numberOfQuestion==correctAnswer || numberOfQuestion==choosedAnswer)
                continue;
            TextView textView = (TextView) summaryRecyclerHolder.getAnswerByNumber(numberOfQuestion);
            textView.setText(getRandomAnswerByNumber(i, numberOfQuestion));
        }

        if(correctAnswer!=choosedAnswer && correctAnswer!=ChooseAnswerPresenterImpl.NOT_CHOOSED){
            correctAnswerView.setText("Cienkie zielone");
            correctAnswerView.setText(getSpannable(getRandomAnswerByNumber(i,correctAnswer),
                    activity.getResources().getColor(R.color.colorGreen), false));
            choosedAnswerView.setText(getSpannable(getRandomAnswerByNumber(i,choosedAnswer),
                    activity.getResources().getColor(R.color.colorGrayDark), true));
        }else
            correctAnswerView.setText(getSpannable(getRandomAnswerByNumber(i,correctAnswer),
                    activity.getResources().getColor(R.color.colorGreen), correctAnswer==choosedAnswer));

    }

    private Spannable getSpannable(String text, int color, boolean bold){
        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(bold)
            spannable.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, text.length()
                    , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    private String getRandomAnswerByNumber(int i, int number){
        switch(number){
            case 0:
                return getAnswerByNumber(i, setOfQuestions.getAnswers().get(i).getSetOfAnswers().indexOf(0));
            case 1:
                return getAnswerByNumber(i, setOfQuestions.getAnswers().get(i).getSetOfAnswers().indexOf(1));
            case 2:
                return getAnswerByNumber(i, setOfQuestions.getAnswers().get(i).getSetOfAnswers().indexOf(2));
            default:
                return getAnswerByNumber(i, setOfQuestions.getAnswers().get(i).getSetOfAnswers().indexOf(3));
        }
    }

    private String getAnswerByNumber(int i, int number){
        switch(number){
            case 0:
                return setOfQuestions.getQuestions().get(i).getAnswerOne();
            case 1:
                return setOfQuestions.getQuestions().get(i).getAnswerTwo();
            case 2:
                return setOfQuestions.getQuestions().get(i).getAnswerThree();
            default:
                return setOfQuestions.getQuestions().get(i).getAnswerFour();
        }
    }


    @Override
    public int getItemCount() {
        return setOfQuestions.getAnswers().size();
    }
}
