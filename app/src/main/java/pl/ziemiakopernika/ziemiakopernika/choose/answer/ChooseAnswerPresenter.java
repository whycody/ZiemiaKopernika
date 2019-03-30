package pl.ziemiakopernika.ziemiakopernika.choose.answer;

import android.view.View;

public interface ChooseAnswerPresenter {

    void onCreate();

    void onBtnClicked(View view);

    void showCorrectAnswer();
}
