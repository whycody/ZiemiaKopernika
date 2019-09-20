package pl.ziemiakopernika.ziemiakopernika.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SetOfRounds implements Serializable {

    private int numOfRound = 0;
    private ArrayList<SetOfQuestions> setOfQuestions;

    public SetOfRounds(){

    }

    public SetOfRounds(int numOfRound, ArrayList<SetOfQuestions> setOfQuestions) {
        this.numOfRound = numOfRound;
        this.setOfQuestions = setOfQuestions;
    }

    public int getNumOfRound() {
        return numOfRound;
    }

    public void setNumOfRound(int numOfRound) {
        this.numOfRound = numOfRound;
    }

    public ArrayList<SetOfQuestions> getSetOfQuestions() {
        return setOfQuestions;
    }

    public void setSetOfQuestions(ArrayList<SetOfQuestions> setOfQuestions) {
        this.setOfQuestions = setOfQuestions;
    }
}
