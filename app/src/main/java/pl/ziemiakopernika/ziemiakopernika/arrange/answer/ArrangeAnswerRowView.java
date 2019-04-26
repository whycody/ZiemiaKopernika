package pl.ziemiakopernika.ziemiakopernika.arrange.answer;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface ArrangeAnswerRowView {

    void setBtnAnswer(String answer);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setBtnDrawable(Drawable drawable);

    void setDragImageVisiblity(int visiblity);

    void setBtnTextColor(int color);
}
