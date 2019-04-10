package pl.ziemiakopernika.ziemiakopernika.round;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface RoundView {

    void setRoundText(String roundText);

    void addViewToLinearLayout(View view);

    void setSummaryViewBackground(Drawable drawable);
}
