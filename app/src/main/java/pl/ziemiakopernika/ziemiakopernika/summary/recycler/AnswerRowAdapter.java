package pl.ziemiakopernika.ziemiakopernika.summary.recycler;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.ziemiakopernika.ziemiakopernika.R;

public class AnswerRowAdapter extends RecyclerView.Adapter<SummaryRecyclerHolder> {

    private AnswerPresenter answerPresenter;
    private Activity activity;

    public AnswerRowAdapter(AnswerPresenter answerPresenter, Activity activity){
        this.answerPresenter = answerPresenter;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SummaryRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.answer_row, viewGroup, false);

        return new SummaryRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryRecyclerHolder summaryRecyclerHolder, int i) {
        answerPresenter.onBindViewHolder(summaryRecyclerHolder, i);
    }

    @Override
    public int getItemCount() {
        return answerPresenter.getItemCount();
    }
}
