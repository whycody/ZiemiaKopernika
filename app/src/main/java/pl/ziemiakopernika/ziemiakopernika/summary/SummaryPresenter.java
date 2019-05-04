package pl.ziemiakopernika.ziemiakopernika.summary;

import android.view.View;

public interface SummaryPresenter {

    void onCreate();

    void onBackPressed();

    void onShareBtnClicked();

    void onPlayAgainBtnClicked(View view);

    void onStatisticsBtnClicked();
}
