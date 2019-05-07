package pl.ziemiakopernika.ziemiakopernika.dao;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.Question;

public interface QuestionsDao {

    ArrayList<Question> getRandomQustions(int number);

    ArrayList<Answer> getRandomAnswers(int number);

    ArrayList<Question> getRandomNotShowedQuestions(int number);

    void addShowedTimeToQuestion(int id);
}
