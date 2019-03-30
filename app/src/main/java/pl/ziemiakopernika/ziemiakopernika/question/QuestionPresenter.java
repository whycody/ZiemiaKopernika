package pl.ziemiakopernika.ziemiakopernika.question;

public interface QuestionPresenter extends FragmentInitializer{

    void onCreate();

    void onFiftyFiftyBtnClicked();

    void onAddSecondsBtnClicked();

    void onAnswerChoosed(boolean correct);

    boolean getAnswersClickable();

    void setAnswersClickable(boolean clickable);
}
