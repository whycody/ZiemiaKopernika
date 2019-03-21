package pl.ziemiakopernika.ziemiakopernika.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Answer implements Serializable {

    private int choosedAnswer;
    private ArrayList<Integer> setOfAnswers;

    public Answer(){

    }

    public Answer(int choosedAnswer, ArrayList<Integer> setOfAnswers) {
        this.choosedAnswer = choosedAnswer;
        this.setOfAnswers = setOfAnswers;
    }

    public int getChoosedAnswer() {
        return choosedAnswer;
    }

    public void setChoosedAnswer(int choosedAnswer) {
        this.choosedAnswer = choosedAnswer;
    }

    public ArrayList<Integer> getSetOfAnswers() {
        return setOfAnswers;
    }

    public void setSetOfAnswers(ArrayList<Integer> setOfAnswers) {
        this.setOfAnswers = setOfAnswers;
    }
}
