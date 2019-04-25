package pl.ziemiakopernika.ziemiakopernika.arrange.answer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.Question;

public class ArrangeAnswerRowAdapter extends RecyclerView.Adapter<ArrangeAnswerRecyclerHolder> {

    private Activity activity;
    private ArrangeAnswerRowPresenter presenter;

    public ArrangeAnswerRowAdapter(Activity activity, ArrangeAnswerRowPresenter presenter){
        this.activity = activity;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ArrangeAnswerRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.arrange_answer_row, viewGroup, false);
        int height = viewGroup.getHeight() / 4;
        int width = viewGroup.getMeasuredWidth();

        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));

        return new ArrangeAnswerRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrangeAnswerRecyclerHolder arrangeAnswerRecyclerHolder, int i) {
        presenter.onBindViewHolder(arrangeAnswerRecyclerHolder, i);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    public ArrayList<Answer> getAnswers(){
        return presenter.getAnswers();
    }
}
