package pl.ziemiakopernika.ziemiakopernika.question;

import android.support.v4.app.Fragment;

public interface QuestionView {

    void setQuestion(String question);

    void setTimeProgress(int progress, int fullTime);

    void setFiftyFiftyBtnActivated(boolean activated);

    void setAddSecondsBtnActivated(boolean activated);

    void setCoinsNumber(int number);

    void applyFragment(Fragment fragment);

//    void setAnswerOneText(String answerOne);
//
//    void setAnswerTwoText(String answerOne);
//
//    void setAnswerThreeText(String answerOne);
//
//    void setAnswerFourText(String answerOne);
//
//    void setAnswerCorrect(int answerCorrect);
//
//    void setAnswerWrong(int answerWrong);

//    void showCorrectnessOfAnswerInCircle(int circle, boolean correct);
}
