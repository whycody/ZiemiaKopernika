package pl.ziemiakopernika.ziemiakopernika.model;

import java.io.Serializable;

public class Question implements Serializable {

    private int typeOfQuestion, showedTimes, id = 0;
    private String question, answerOne, answerTwo, answerThree, answerFour;

    public Question(){

    }

    public Question(int typeOfQuestion, String question, String answerOne, String answerTwo,
                    String answerThree, String answerFour, int showedTimes) {
        this.typeOfQuestion = typeOfQuestion;
        this.question = question;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.showedTimes = showedTimes;
    }

    public Question(String question, String answerOne, String answerTwo,
                    String answerThree, String answerFour) {
        this.question = question;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
    }

    public Question(int typeOfQuestion, String question, String answerOne, String answerTwo,
                    String answerThree, String answerFour) {
        this.typeOfQuestion = typeOfQuestion;
        this.question = question;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
    }

    public int getTypeOfQuestion() {
        return typeOfQuestion;
    }

    public void setTypeOfQuestion(int typeOfQuestion) {
        this.typeOfQuestion = typeOfQuestion;
    }

    public int getShowedTimes() {
        return showedTimes;
    }

    public void setShowedTimes(int showedTimes) {
        this.showedTimes = showedTimes;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
