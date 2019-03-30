package pl.ziemiakopernika.ziemiakopernika.question;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class QuestionActivity extends AppCompatActivity implements QuestionView {

    private TextView questionView, coinsNumberView, progressTextView;
    private Button fiftyFiftyBtn, addSecondsBtn;
    private ProgressBar progressBarView;
    private QuestionPresenter questionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionView = findViewById(R.id.question_view);
        coinsNumberView = findViewById(R.id.coins_number_view);
        progressTextView = findViewById(R.id.progress_text_view);
        fiftyFiftyBtn = findViewById(R.id.fifty_fifty_btn);
        addSecondsBtn = findViewById(R.id.add_seconds_btn);
        progressBarView = findViewById(R.id.progress_bar_view);
        questionPresenter = new QuestionPresenterImpl(this, this);

        fiftyFiftyBtn.setOnClickListener(fifityFiftyBtnClickListener);
        addSecondsBtn.setOnClickListener(addSecondsBtnClickListener);
        questionPresenter.onCreate();
    }

    @Override
    public void onBackPressed() {
        finish();
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
        transaction.commit();
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

}
