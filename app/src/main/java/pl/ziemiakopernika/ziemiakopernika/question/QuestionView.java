package pl.ziemiakopernika.ziemiakopernika.question;

import android.support.v4.app.Fragment;
import android.view.View;

public interface QuestionView {

    void setQuestion(String question);

    void setTimeProgress(int progress, int fullTime);

    void setFiftyFiftyBtnActivated(boolean activated);

    void setAddSecondsBtnActivated(boolean activated);

    void setCoinsNumber(int number);

    void applyFragment(Fragment fragment);

    void addViewToLinearLayout(View view);

    void animateCorrectness(int index, boolean correct);
}
