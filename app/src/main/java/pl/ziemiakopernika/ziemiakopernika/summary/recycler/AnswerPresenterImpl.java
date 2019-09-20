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
import pl.ziemiakopernika.ziemiakopernika.answer.checker.AnswerChecker;
import pl.ziemiakopernika.ziemiakopernika.answer.checker.AnswerCheckerImpl;
import pl.ziemiakopernika.ziemiakopernika.choose.answer.ChooseAnswerPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfRounds;

public class AnswerPresenterImpl implements AnswerPresenter {

    private SetOfQuestions setOfQuestions;
    private SetOfRounds setOfRounds;
    private ArrayList<Answer> answersList;
    private Activity activity;
    private AnswerChecker answerChecker;

    public AnswerPresenterImpl(SetOfQuestions setOfQuestions, Activity activity){
        this.setOfQuestions = setOfQuestions;
        this.answersList = setOfQuestions.getAnswers();
        this.activity = activity;
        answerChecker = new AnswerCheckerImpl(setOfQuestions);
    }

    public AnswerPresenterImpl(SetOfRounds setOfRounds, Activity activity){
        this.setOfRounds = setOfRounds;
        this.answersList = setOfQuestions.getAnswers();
        this.activity = activity;
        answerChecker = new AnswerCheckerImpl(setOfQuestions);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryRecyclerHolder summaryRecyclerHolder, int i) {
        Answer currentAnswer = answersList.get(i);
        summaryRecyclerHolder.setQuestionText(setOfQuestions.getQuestions().get(i).getQuestion());
        if(setOfRounds!=null){
            setOfQuestions = setOfRounds.getSetOfQuestions().get(i/
                    (setOfRounds.getSetOfQuestions().size()*setOfRounds.getSetOfQuestions().get(0).getQuestions().size()));
            i -= (i/ (setOfRounds.getSetOfQuestions().size()*setOfRounds.getSetOfQuestions().get(0).getQuestions().size()))
                    *setOfRounds.getSetOfQuestions().get(0).getQuestions().size();
        }

        if(setOfQuestions.getQuestions().get(i).getTypeOfQuestion()==0) {
            int correctAnswer = currentAnswer.getSetOfAnswers().get(0);
            int choosedAnswer = currentAnswer.getChoosedAnswer();
            for (int numberOfQuestion = 0; numberOfQuestion < 4; numberOfQuestion++) {
                TextView currentTextView = (TextView) summaryRecyclerHolder.getAnswerByNumber(numberOfQuestion);
                if (numberOfQuestion == choosedAnswer && choosedAnswer == correctAnswer) {
                    currentTextView.setText(getSpannable(getRandomAnswerByNumber(i, correctAnswer),
                            activity.getResources().getColor(R.color.colorGreen), true));
                } else if (numberOfQuestion == choosedAnswer) {
                    currentTextView.setText(getSpannable(getRandomAnswerByNumber(i, choosedAnswer),
                            activity.getResources().getColor(R.color.colorPrimaryDark), true));
                } else if (numberOfQuestion == correctAnswer) {
                    currentTextView.setText(getSpannable(getRandomAnswerByNumber(i, correctAnswer),
                            activity.getResources().getColor(R.color.colorGreen), false));
                } else {
                    currentTextView.setText(getRandomAnswerByNumber(i, numberOfQuestion));
                }
            }
        }else{
            for(int numberOfQuestion = 0; numberOfQuestion < 4; numberOfQuestion++){
                TextView currentTextView = (TextView) summaryRecyclerHolder.getAnswerByNumber(numberOfQuestion);
                if (currentAnswer.getChoosedAnswers().get(numberOfQuestion) == numberOfQuestion)
                    currentTextView.setText(getSpannable(getAnswerByNumber(i, currentAnswer.getChoosedAnswers().get(numberOfQuestion)),
                            activity.getResources().getColor(R.color.colorGreen), true));
                else {
                    currentTextView.setText(getSpannable(getAnswerByNumber(i, currentAnswer.getChoosedAnswers().get(numberOfQuestion)),
                            activity.getResources().getColor(R.color.colorPrimaryDark), true));
                }
            }
        }
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
        if(setOfRounds!=null) return setOfRounds.getSetOfQuestions().size()*setOfRounds.getSetOfQuestions().get(0).getQuestions().size();
        return setOfQuestions.getAnswers().size();
    }
}
