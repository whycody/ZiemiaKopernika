package pl.ziemiakopernika.ziemiakopernika.arrange.answer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.Question;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;

public class ArrangeAnswerTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ArrangeAnswerRowAdapter adapter;
    private ArrayList<Answer> answers;
    private int numberOfQuestion;
    private boolean fiftyFiftyBtnClicked = false;
    private SetOfQuestions setOfQuestions;

    public ArrangeAnswerTouchHelper(ArrangeAnswerRowAdapter adapter, int numberOfQuestion, SetOfQuestions setOfQuestions){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
        this.adapter = adapter;
        this.answers = adapter.getAnswers();
        this.numberOfQuestion = numberOfQuestion;
        this.setOfQuestions = setOfQuestions;
    }


    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder current, @NonNull RecyclerView.ViewHolder target) {
        return super.canDropOver(recyclerView, current, target);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder viewHolder1) {
        int positionDragged = viewHolder.getAdapterPosition();
        int positionTarget = viewHolder1.getAdapterPosition();
        if(fiftyFiftyBtnClicked) {
            if (positionTarget == 0 || positionTarget == 1)
                return false;
        }
        Collections.swap(answers.get(numberOfQuestion).getSetOfAnswers(), positionDragged, positionTarget);
        adapter.notifyItemMoved(positionDragged, positionTarget);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    public void setFiftyFiftyBtnClicked(boolean clicked){
        fiftyFiftyBtnClicked = clicked;
    }
}
