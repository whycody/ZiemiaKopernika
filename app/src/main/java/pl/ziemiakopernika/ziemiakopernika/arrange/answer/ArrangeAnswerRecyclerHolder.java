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
    private ImageView dragImage;

    ArrangeAnswerRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        dragImage = itemView.findViewById(R.id.drag_image);
        answerOneBtn = itemView.findViewById(R.id.answer_btn);
    }

    @Override
    public void setBtnAnswer(String answer) {
        answerOneBtn.setText(answer);
    }

    @Override
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        dragImage.setOnTouchListener(onTouchListener);
    }

    @Override
    public void setBtnDrawable(Drawable drawable) {
        answerOneBtn.setBackground(drawable);
    }

    @Override
    public void setBtnTextColor(int color) {
        answerOneBtn.setTextColor(color);
    }


}
