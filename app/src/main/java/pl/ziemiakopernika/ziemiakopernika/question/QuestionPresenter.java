package pl.ziemiakopernika.ziemiakopernika.question;

import pl.ziemiakopernika.ziemiakopernika.arrange.answer.ArrangeAnswerRowAdapter;

public interface QuestionPresenter extends FragmentInitializer{

    void onCreate();

    void onBackPressed();

    void onFiftyFiftyBtnClicked();

    void onAddSecondsBtnClicked();

    void progressTextClicked();

    void onAnswerChoosed(boolean correct);

    boolean getAnswersClickable();

    void showNextQuestion();

    void showNextRound();

    void startNewActivityIfNotPaused(int requestCode);

    void onResume();
}
