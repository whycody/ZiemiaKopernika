package pl.ziemiakopernika.ziemiakopernika.question;

public interface QuestionPresenter {

    void onCreate();

    void onFiftyFiftyBtnClicked();

    void onAddSecondsBtnClicked();

    void onAnswerChoosed(boolean correct);
}
