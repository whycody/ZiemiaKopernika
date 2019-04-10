package pl.ziemiakopernika.ziemiakopernika.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Answer implements Serializable {

    private int choosedAnswer, timeLeft;
    private ArrayList<Integer> setOfAnswers;

    public Answer(){

    }

    public Answer(int choosedAnswer, int timeLeft, ArrayList<Integer> setOfAnswers) {
        this.choosedAnswer = choosedAnswer;
        this.timeLeft = timeLeft;
        this.setOfAnswers = setOfAnswers;
    }

    public int getChoosedAnswer() {
        return choosedAnswer;
    }

    public void setChoosedAnswer(int choosedAnswer) {
        this.choosedAnswer = choosedAnswer;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public ArrayList<Integer> getSetOfAnswers() {
        return setOfAnswers;
    }

    public void setSetOfAnswers(ArrayList<Integer> setOfAnswers) {
        this.setOfAnswers = setOfAnswers;
    }
}
