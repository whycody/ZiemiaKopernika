package pl.ziemiakopernika.ziemiakopernika.summary.recycler;

import android.support.annotation.NonNull;

public interface AnswerPresenter {

    void onBindViewHolder(@NonNull SummaryRecyclerHolder summaryRecyclerHolder, int i);

    int getItemCount();

}
