package pl.ziemiakopernika.ziemiakopernika.main;

import android.view.View;

public interface MainPresenter {

    void onCreate();

    void startGameClicked(View view);

    void muteClicked();

    void settingsClicked();

    void statisticsClicked();
}
