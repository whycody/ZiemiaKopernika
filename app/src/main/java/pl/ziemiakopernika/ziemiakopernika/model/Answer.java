package pl.ziemiakopernika.ziemiakopernika.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Answer implements Serializable, Cloneable {

    private int choosedAnswer, timeLeft;
    private ArrayList<Integer> setOfAnswers;
    private ArrayList<Integer> choosedAnswers;

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

    public ArrayList<Integer> getChoosedAnswers() {
        return choosedAnswers;
    }

    public void setChoosedAnswers(ArrayList<Integer> choosedAnswers) {
        this.choosedAnswers = choosedAnswers;
    }

    public String setOfAnswersToString(){
        return "SetOfAnswers: " + setOfAnswers.get(0) + setOfAnswers.get(1) + setOfAnswers.get(2) + setOfAnswers.get(3);
    }

    public String choosedAnswersToString(){
        return "SetOfAnswers: " + choosedAnswers.get(0) + choosedAnswers.get(1) + choosedAnswers.get(2) + choosedAnswers.get(3);
    }
}
