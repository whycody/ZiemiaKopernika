package pl.ziemiakopernika.ziemiakopernika.statistics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class StatisticsBottomSheet extends BottomSheetDialogFragment implements StatisticsView{

    private StatisticsPresenter presenter;
    private TextView roundsPlayedText, questionsAnsweredText, correctAnswersText, earnedCoinsText,
            spentCoinsText, lifebuoyText, secondsLeftPerQuestionText;
    private ProgressBar correctAnswersProgress, earnedCoinsProgress;
    private Button playAgainBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_statistics, container);
        presenter = new StatisticsPresenterImpl(getActivity(), this);
        roundsPlayedText = view.findViewById(R.id.rounds_played_text);
        questionsAnsweredText = view.findViewById(R.id.questions_answered_text);
        correctAnswersText = view.findViewById(R.id.correct_answers_text);
        earnedCoinsText = view.findViewById(R.id.earned_coins_text);
        spentCoinsText = view.findViewById(R.id.spent_coins_text);
        lifebuoyText = view.findViewById(R.id.lifebuoy_text);
        secondsLeftPerQuestionText = view.findViewById(R.id.seconds_left_per_question_text);
        correctAnswersProgress = view.findViewById(R.id.correct_answers_progress);
        earnedCoinsProgress = view.findViewById(R.id.earned_coins_progress);
        playAgainBtn = view.findViewById(R.id.play_again_btn);
        playAgainBtn.setOnClickListener(onPlayAgainClicked);
        presenter.onCreate();
        return view;
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

    private View.OnClickListener onPlayAgainClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.onPlayAgainBtnClicked(playAgainBtn);
        }
    };
}
