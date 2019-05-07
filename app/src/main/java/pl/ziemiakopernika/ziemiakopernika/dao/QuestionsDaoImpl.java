package pl.ziemiakopernika.ziemiakopernika.dao;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.Question;

public class QuestionsDaoImpl implements QuestionsDao{

    private Context context;
    private QuestionsDbHelper dbHelper;

    public QuestionsDaoImpl(Context context){
        this.context = context;
        dbHelper = new QuestionsDbHelperImpl(context);
    }

    @Override
    public ArrayList<Question> getRandomQustions(int number) {
        ArrayList<Question> allQuestions = dbHelper.getAllQuestions();
        Collections.shuffle(allQuestions);
        ArrayList<Question> questions = new ArrayList<>();
        for(int i =0; i<number; i++)
            questions.add(allQuestions.get(i));
        return questions;
    }

    @Override
    public ArrayList<Question> getRandomNotShowedQuestions(int number){
        int min = dbHelper.getMinShowedTimes();
        ArrayList<Question> minShowedTimesQuestions = dbHelper.getQuestionsByShowedTimes(min);
        Collections.shuffle(minShowedTimesQuestions);
        if(minShowedTimesQuestions.size()<number){
            int addedSongs = 0;
            ArrayList<Question> oneMoreShowedTimesQuestions = dbHelper.getQuestionsByShowedTimes(min+1);
            Collections.shuffle(oneMoreShowedTimesQuestions);
            while(minShowedTimesQuestions.size()<number) {
                minShowedTimesQuestions.add(oneMoreShowedTimesQuestions.get(addedSongs));
                addedSongs++;
            }
        }else{
            ArrayList<Question> questions = new ArrayList<>();
            Collections.shuffle(minShowedTimesQuestions);
            for(int i = 0; i<number; i++)
                questions.add(minShowedTimesQuestions.get(i));
            return questions;
        }
        return minShowedTimesQuestions;
    }

    @Override
    public ArrayList<Answer> getRandomAnswers(int number) {
        ArrayList<Answer> answers = new ArrayList<>();
        for(int i =1; i<=number; i++){
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(0);
            integers.add(1);
            integers.add(2);
            integers.add(3);
            Collections.shuffle(integers);
            answers.add(new Answer(0,0, integers));
        }
        return answers;
    }

    @Override
    public void addShowedTimeToQuestion(int id) {
        dbHelper.addShowedTimeToSongWithID(id);
    }

}
