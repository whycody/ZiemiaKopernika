package pl.ziemiakopernika.ziemiakopernika.model;

import android.graphics.Bitmap;

public class Question {

    private int typeOfQuestion, imageDrawable;
    private String question, answerOne, answerTwo, answerThree, answerFour;

    public Question(){

    }

    public Question(int typeOfQuestion, int imageDrawable, String question, String answerOne,
                    String answerTwo, String answerThree, String answerFour) {
        this.typeOfQuestion = typeOfQuestion;
        this.imageDrawable = imageDrawable;
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

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
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
}
