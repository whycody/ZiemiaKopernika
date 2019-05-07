package pl.ziemiakopernika.ziemiakopernika.dao;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.model.Question;

public interface QuestionsDbHelper {

    ArrayList<Question> getAllQuestions();

    void addShowedTimeToSongWithID(int id);

    int getMinShowedTimes();

    ArrayList<Question> getQuestionsByShowedTimes(int number);

}
