package pl.ziemiakopernika.ziemiakopernika.question;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class QuestionActivity extends AppCompatActivity implements QuestionView {

    private TextView questionView, coinsNumberView, progressTextView;
    private Button fiftyFiftyBtn, addSecondsBtn;
    private LinearLayout wheelsContainer;
    private ProgressBar progressBarView;
    private QuestionPresenter questionPresenter;

    private boolean activityPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionView = findViewById(R.id.question_view);
        coinsNumberView = findViewById(R.id.coins_number_view);
        progressTextView = findViewById(R.id.progress_text_view);
        fiftyFiftyBtn = findViewById(R.id.fifty_fifty_btn);
        addSecondsBtn = findViewById(R.id.add_seconds_btn);
        wheelsContainer = findViewById(R.id.wheels_container);
        progressBarView = findViewById(R.id.progress_bar_view);
        questionPresenter = new QuestionPresenterImpl(this, this);

        progressTextView.setOnClickListener(progressTextClicked);
        fiftyFiftyBtn.setOnClickListener(fifityFiftyBtnClickListener);
        addSecondsBtn.setOnClickListener(addSecondsBtnClickListener);
        questionPresenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityPaused = false;
        questionPresenter.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == QuestionPresenterImpl.FINISH_ROUND_REQUEST_CODE){
            if(resultCode == RESULT_OK) {
                questionPresenter.showNextRound();
                resetViewsToGray();
            }else if (resultCode == RESULT_CANCELED){
                questionPresenter.startNewActivityIfNotPaused(QuestionPresenterImpl.SHOW_FINAL_OF_ROUNDS);
            }
        }else if(requestCode == QuestionPresenterImpl.SHOW_NUMBER_OF_ROUND){
            if(resultCode == RESULT_OK) questionPresenter.showNextQuestion();
        }else if(requestCode == QuestionPresenterImpl.SHOW_FINAL_OF_ROUNDS)
            questionPresenter.startNewActivityIfNotPaused(QuestionPresenterImpl.SHOW_SUMMARY);
    }

    private void resetViewsToGray(){
        for (int i = 0; i < wheelsContainer.getChildCount(); i++) {
            wheelsContainer.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.circle_button_non_activated));
        }
    }

    @Override
    public void onBackPressed() {
       questionPresenter.onBackPressed();
    }

    @Override
    public void setQuestion(String question) {
        questionView.setText(question);
    }

    @Override
    public void setTimeProgress(int progress, int fullTime) {
        progressBarView.setProgress(progress/fullTime);
        progressTextView.setText(progress/1000 + "");
    }

    @Override
    public void setFiftyFiftyBtnActivated(boolean activated) {
        if(activated)
            fiftyFiftyBtn.setBackground(getResources().getDrawable(R.drawable.circle_button_activated_selector));
        else
            fiftyFiftyBtn.setBackground(getResources().getDrawable(R.drawable.circle_button_non_activated));
    }

    @Override
    public void setAddSecondsBtnActivated(boolean activated) {
        if(activated)
            addSecondsBtn.setBackground(getResources().getDrawable(R.drawable.circle_button_activated_selector));
        else
            addSecondsBtn.setBackground(getResources().getDrawable(R.drawable.circle_button_non_activated));
    }

    @Override
    public void setCoinsNumber(int number) {
        coinsNumberView.setText(number + "");
    }

    @Override
    public void applyFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void addViewToLinearLayout(View view) {
        wheelsContainer.addView(view);
    }

    @Override
    public void animateCorrectness(int index, boolean correct) {
        View view = wheelsContainer.getChildAt(index);
        setViewBackgroundByCorrectness(view, correct);
        TransitionDrawable transition = (TransitionDrawable)view.getBackground();
        transition.startTransition(1000);
    }

    @Override
    public boolean getActivityPaused() {
        return activityPaused;
    }

    private void setViewBackgroundByCorrectness(View view, boolean correct){
        if(correct)
            view.setBackground(getResources().getDrawable(R.drawable.transition_to_green));
        else
            view.setBackground(getResources().getDrawable(R.drawable.transition_to_red));
    }

    private View.OnClickListener fifityFiftyBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            questionPresenter.onFiftyFiftyBtnClicked();
        }
    };

    private View.OnClickListener addSecondsBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            questionPresenter.onAddSecondsBtnClicked();
        }
    };

    private View.OnClickListener progressTextClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            questionPresenter.progressTextClicked();
        }
    };

}
