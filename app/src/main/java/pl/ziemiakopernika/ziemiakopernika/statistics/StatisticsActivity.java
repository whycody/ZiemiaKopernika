package pl.ziemiakopernika.ziemiakopernika.statistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class StatisticsActivity extends AppCompatActivity implements StatisticsView{

    private StatisticsPresenter presenter;
    private TextView roundsPlayedText, questionsAnsweredText, correctAnswersText, earnedCoinsText,
            spentCoinsText, lifebuoyText, secondsLeftPerQuestionText;
    private ProgressBar correctAnswersProgress, earnedCoinsProgress;
    private Button backBtn, playAgainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        presenter = new StatisticsPresenterImpl(this, this);
        roundsPlayedText = findViewById(R.id.rounds_played_text);
        questionsAnsweredText = findViewById(R.id.questions_answered_text);
        correctAnswersText = findViewById(R.id.correct_answers_text);
        earnedCoinsText = findViewById(R.id.earned_coins_text);
        spentCoinsText = findViewById(R.id.spent_coins_text);
        lifebuoyText = findViewById(R.id.lifebuoy_text);
        secondsLeftPerQuestionText = findViewById(R.id.seconds_left_per_question_text);
        correctAnswersProgress = findViewById(R.id.correct_answers_progress);
        earnedCoinsProgress = findViewById(R.id.earned_coins_progress);
        backBtn = findViewById(R.id.back_btn);
        playAgainBtn = findViewById(R.id.play_again_btn);
        backBtn.setOnClickListener(onBackBtnClicked);
        playAgainBtn.setOnClickListener(onPlayAgainClicked);
        presenter.onCreate();
    }

    @Override
    public void setRoundsPlayedText(String text) {
        roundsPlayedText.setText(text);
    }

    @Override
    public void setQuestionsAnsweredText(String text) {
        questionsAnsweredText.setText(text);
    }

    @Override
    public void setCorrectAnswersText(String text) {
        correctAnswersText.setText(text);
    }

    @Override
    public void setEarnedCoinsText(String text) {
        earnedCoinsText.setText(text);
    }

    @Override
    public void setSpentCoinsText(String text) {
        spentCoinsText.setText(text);
    }

    @Override
    public void setLifebuoyText(String text) {
        lifebuoyText.setText(text);
    }

    @Override
    public void setSecondsLeftPerQuestionText(String text) {
        secondsLeftPerQuestionText.setText(text);
    }

    @Override
    public void setCorrectAnswersProgress(int maxValue, int progress) {
        correctAnswersProgress.setMax(maxValue);
        correctAnswersProgress.setProgress(progress);
    }

    @Override
    public void setEarnedCoinsProgress(int maxValue, int progress) {
        earnedCoinsProgress.setMax(maxValue);
        earnedCoinsProgress.setProgress(progress);
    }

    private View.OnClickListener onBackBtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.onBackBtnClicked();
        }
    };

    private View.OnClickListener onPlayAgainClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.onPlayAgainBtnClicked(playAgainBtn);
        }
    };
}
