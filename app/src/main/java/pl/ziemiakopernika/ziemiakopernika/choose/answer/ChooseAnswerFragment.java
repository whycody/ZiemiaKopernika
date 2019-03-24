package pl.ziemiakopernika.ziemiakopernika.choose.answer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenter;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenterImpl;

public class ChooseAnswerFragment extends Fragment implements ChooseAnswerView{

    private Button answerOneBtn, answerTwoBtn, answerThreeBtn, answerFourBtn;
    private SetOfQuestions setOfQuestions;
    private ChooseAnswerPresenter presenter;
    private QuestionPresenter questionPresenter;
    private int numberOfQuestion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_answer, container, false);
        answerOneBtn = view.findViewById(R.id.answer_one_btn);
        answerTwoBtn = view.findViewById(R.id.answer_two_btn);
        answerThreeBtn = view.findViewById(R.id.answer_three_btn);
        answerFourBtn = view.findViewById(R.id.answer_four_btn);

        answerOneBtn.setOnClickListener(onBtnClickListener);
        answerTwoBtn.setOnClickListener(onBtnClickListener);
        answerThreeBtn.setOnClickListener(onBtnClickListener);
        answerFourBtn.setOnClickListener(onBtnClickListener);

        presenter = new ChooseAnswerPresenterImpl(getActivity(), this);
        presenter.onCreate();

        return view;
    }

    public void setSetOfQuestions(SetOfQuestions setOfQuestions) {
        this.setOfQuestions = setOfQuestions;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public void setQuestionPresenter(QuestionPresenterImpl questionPresenter) {
        this.questionPresenter = questionPresenter;
    }

    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.onBtnClicked(view);
        }
    };

    @Override
    public Button getButton(int number) {
        if(number==0)
            return answerOneBtn;
        else if(number==1)
            return answerTwoBtn;
        else if(number==2)
            return answerThreeBtn;
        else
            return answerFourBtn;
    }

    @Override
    public SetOfQuestions getSetOfQuestion() {
        return setOfQuestions;
    }

    @Override
    public QuestionPresenter getQuestionPresenter() {
        return questionPresenter;
    }

    @Override
    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }
}
