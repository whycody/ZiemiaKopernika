package pl.ziemiakopernika.ziemiakopernika.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SetOfQuestions implements Serializable {

    private int numOfQuestion;
    private int secondsPerQuestion;
    private ArrayList<Question> questions;
    private ArrayList<Answer> answers;

    public SetOfQuestions(){

    }

    public SetOfQuestions(int numOfQuestion, int secondsPerQuestion,
                          ArrayList<Question> questions, ArrayList<Answer> answers) {
        this.numOfQuestion = numOfQuestion;
        this.secondsPerQuestion = secondsPerQuestion;
        this.questions = questions;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public int getSecondsPerQuestion() {
        return secondsPerQuestion;
    }

    public void setSecondsPerQuestion(int secondsPerQuestion) {
        this.secondsPerQuestion = secondsPerQuestion;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
