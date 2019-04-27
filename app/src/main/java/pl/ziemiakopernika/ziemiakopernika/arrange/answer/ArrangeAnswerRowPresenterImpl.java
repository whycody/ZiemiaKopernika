package pl.ziemiakopernika.ziemiakopernika.arrange.answer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.answer.checker.AnswerChecker;
import pl.ziemiakopernika.ziemiakopernika.answer.checker.AnswerCheckerImpl;
import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.Question;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenter;

public class ArrangeAnswerRowPresenterImpl implements ArrangeAnswerRowPresenter{

    private SetOfQuestions setOfQuestions;
    private int numberOfQuestion;
    private ArrayList<Question> questions;
    private ArrayList<Answer> answers;
    private ArrangeAnswerRowAdapter adapter;
    private ItemTouchHelper itemTouchHelper;
    private AnswerChecker answerChecker;
    private QuestionPresenter questionPresenter;
    private Activity activity;

    private boolean fiftyFiftyBtnClicked = false;

    public ArrangeAnswerRowPresenterImpl(SetOfQuestions setOfQuestions, int numberOfQuestion){
        this.setOfQuestions = setOfQuestions;
        this.numberOfQuestion = numberOfQuestion;
        questions = setOfQuestions.getQuestions();
        answers = cloneAnswersList(setOfQuestions.getAnswers());
        answerChecker = new AnswerCheckerImpl(setOfQuestions);
    }

    private ArrayList<Answer> cloneAnswersList(ArrayList<Answer> list){
        ArrayList<Answer> cloneList = new ArrayList<>(list.size());
        for (Answer answer : list){
            Answer newAnswer = new Answer();
            newAnswer.setTimeLeft(answer.getTimeLeft());
            newAnswer.setSetOfAnswers(cloneIntegersList(answer.getSetOfAnswers()));
            newAnswer.setChoosedAnswer(answer.getChoosedAnswer());
            newAnswer.setChoosedAnswers(answer.getChoosedAnswers());
            cloneList.add(newAnswer);
        }
        return cloneList;
    }

    private ArrayList<Integer> cloneIntegersList(ArrayList<Integer> integers){
        ArrayList<Integer> setOfAnswers = new ArrayList<>();
        setOfAnswers.addAll(integers);
        return setOfAnswers;
    }

    @Override
    public void onBindViewHolder(@NonNull final ArrangeAnswerRecyclerHolder arrangeAnswerRecyclerHolder, int i) {
        if(setOfQuestions.getAnswers().get(numberOfQuestion).getChoosedAnswers()!=null){
            arrangeAnswerRecyclerHolder.setDragImageVisiblity(View.GONE);
            arrangeAnswerRecyclerHolder.setBtnTextColor(activity.getResources().getColor(android.R.color.white));
            if(setOfQuestions.getAnswers().get(numberOfQuestion).getChoosedAnswers().get(i)==i)
                arrangeAnswerRecyclerHolder.setBtnDrawable(activity.getResources().getDrawable(R.drawable.rectangle_button_green));
            else
                arrangeAnswerRecyclerHolder.setBtnDrawable(activity.getResources().getDrawable(R.drawable.rectangle_button_red));
            arrangeAnswerRecyclerHolder.setBtnAnswer(getAnswerNumber(setOfQuestions.getAnswers()
                    .get(numberOfQuestion).getChoosedAnswers().get(i)));
        }else{
            if(fiftyFiftyBtnClicked && (i == 0 || i == 1)) {
                arrangeAnswerRecyclerHolder.setDragImageVisiblity(View.GONE);
                arrangeAnswerRecyclerHolder.setBtnAnswer(getAnswerNumber(i));
            }else {
                arrangeAnswerRecyclerHolder.setBtnAnswer(getAnswerNumber(setOfQuestions.getAnswers()
                        .get(numberOfQuestion).getSetOfAnswers().get(i)));
                arrangeAnswerRecyclerHolder.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        itemTouchHelper.startDrag(arrangeAnswerRecyclerHolder);
                        return false;
                    }
                });
            }
        }
    }

    private String getAnswerNumber(int number){
        switch(number){
            case(0):
                return questions.get(numberOfQuestion).getAnswerOne();
            case(1):
                return questions.get(numberOfQuestion).getAnswerTwo();
            case(2):
                return questions.get(numberOfQuestion).getAnswerThree();
            default:
                return questions.get(numberOfQuestion).getAnswerFour();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    @Override
    public void setTwoFirstCorrectAnswersBlockedUp() {
        this.fiftyFiftyBtnClicked = true;
    }

    @Override
    public void setQuestionPresenter(QuestionPresenter questionPresenter) {
        this.questionPresenter = questionPresenter;
    }

    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    @Override
    public void showCorrectAnswers() {
        if(answerChecker.answerIsCorrect(numberOfQuestion)){
            questionPresenter.onAnswerChoosed(true);
        }else
            questionPresenter.onAnswerChoosed(false);
    }
}
