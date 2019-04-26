package pl.ziemiakopernika.ziemiakopernika.arrange.answer;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class ArrangeAnswerRecyclerHolder extends RecyclerView.ViewHolder implements ArrangeAnswerRowView{

    private Button answerOneBtn;
    private ImageView dragImageLeft, dragImageRight;

    ArrangeAnswerRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        dragImageLeft = itemView.findViewById(R.id.drag_image_left);
        dragImageRight = itemView.findViewById(R.id.drag_image_right);
        answerOneBtn = itemView.findViewById(R.id.answer_btn);
    }

    @Override
    public void setBtnAnswer(String answer) {
        answerOneBtn.setText(answer);
    }

    @Override
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        dragImageLeft.setOnTouchListener(onTouchListener);
        dragImageRight.setOnTouchListener(onTouchListener);
        answerOneBtn.setOnTouchListener(onTouchListener);
    }

    @Override
    public void setBtnDrawable(Drawable drawable) {
        answerOneBtn.setBackground(drawable);
    }

    @Override
    public void setDragImageVisiblity(int visiblity) {
        dragImageLeft.setVisibility(visiblity);
        dragImageRight.setVisibility(visiblity);
    }

    @Override
    public void setBtnTextColor(int color) {
        answerOneBtn.setTextColor(color);
    }


}
