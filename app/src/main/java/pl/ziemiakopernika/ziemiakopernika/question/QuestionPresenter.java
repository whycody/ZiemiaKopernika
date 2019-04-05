package pl.ziemiakopernika.ziemiakopernika.question;

public interface QuestionPresenter extends FragmentInitializer{

    void onCreate();

    void onBackPressed();

    void onFiftyFiftyBtnClicked();

    void onAddSecondsBtnClicked();

    void onAnswerChoosed(boolean correct);

    boolean getAnswersClickable();

    void showNextQuestion();
}
