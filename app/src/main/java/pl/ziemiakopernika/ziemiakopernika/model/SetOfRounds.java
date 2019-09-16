package pl.ziemiakopernika.ziemiakopernika.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SetOfRounds implements Serializable {

    private int numOfRounds;
    private ArrayList<SetOfQuestions> setOfQuestions;

    public SetOfRounds(){

    }

    public SetOfRounds(int numOfRounds, ArrayList<SetOfQuestions> setOfQuestions) {
        this.numOfRounds = numOfRounds;
        this.setOfQuestions = setOfQuestions;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    public void setNumOfRounds(int numOfRounds) {
        this.numOfRounds = numOfRounds;
    }

    public ArrayList<SetOfQuestions> getSetOfQuestions() {
        return setOfQuestions;
    }

    public void setSetOfQuestions(ArrayList<SetOfQuestions> setOfQuestions) {
        this.setOfQuestions = setOfQuestions;
    }
}
