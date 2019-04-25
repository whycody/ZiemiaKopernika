package pl.ziemiakopernika.ziemiakopernika.arrange.answer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenter;

public class ArrangeAnswerFragment extends Fragment {

    private RecyclerView arrangeAnswerRecycler;
    private SetOfQuestions setOfQuestions;
    private ArrangeAnswerTouchHelper touchHelper;
    private ArrangeAnswerRowPresenter arrangeAnswerRowPresenter;
    private ArrangeAnswerRowAdapter adapter;
    private QuestionPresenter questionPresenter;
    private ItemTouchHelper itemTouchHelper;
    private int numberOfQuestion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arrange_answer, container, false);
        arrangeAnswerRecycler = view.findViewById(R.id.arrange_answer_recycler);
        arrangeAnswerRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrangeAnswerRowPresenter = new ArrangeAnswerRowPresenterImpl(setOfQuestions, numberOfQuestion);
        adapter = new ArrangeAnswerRowAdapter(getActivity(), arrangeAnswerRowPresenter);
        arrangeAnswerRecycler.setAdapter(adapter);
        touchHelper = new ArrangeAnswerTouchHelper(adapter, numberOfQuestion, setOfQuestions);
        itemTouchHelper = new ItemTouchHelper(touchHelper);
        itemTouchHelper.attachToRecyclerView(arrangeAnswerRecycler);
        arrangeAnswerRowPresenter.setItemTouchHelper(itemTouchHelper);
        arrangeAnswerRowPresenter.setQuestionPresenter(questionPresenter);
        arrangeAnswerRowPresenter.setActivity(getActivity());
        questionPresenter.setArrangeAnswerRowPresenter(arrangeAnswerRowPresenter);
        return view;
    }

    public void setProperties(SetOfQuestions setOfQuestions, int numberOfQuestion){
        this.setOfQuestions = setOfQuestions;
        this.numberOfQuestion = numberOfQuestion;
    }

    public void setQuestionPresenter(QuestionPresenter questionPresenter){
        this.questionPresenter = questionPresenter;
    }

    public ArrangeAnswerRowAdapter getAdapter() {
        return adapter;
    }
}
