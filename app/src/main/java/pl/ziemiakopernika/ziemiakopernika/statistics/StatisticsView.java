package pl.ziemiakopernika.ziemiakopernika.statistics;

import android.view.animation.Animation;
import android.widget.ProgressBar;

public interface StatisticsView {

    void setRoundsPlayedText(String text);

    void setQuestionsAnsweredText(String text);

    void setCorrectAnswersText(String text);

    void setEarnedCoinsText(String text);

    void setSpentCoinsText(String text);

    void setLifebuoyText(String text);

    void setSecondsLeftPerQuestionText(String text);

    void setCorrectAnswersProgress(int maxValue, int progress);

    void setEarnedCoinsProgress(int maxValue, int progress);

    ProgressBar getCorrectAnswersProgressBar();

    ProgressBar getEarnedCoinsProgressBar();

}
