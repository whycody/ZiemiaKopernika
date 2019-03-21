package pl.ziemiakopernika.ziemiakopernika.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;

import pl.ziemiakopernika.ziemiakopernika.RedInfoActivity;
import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.Question;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;

public class MainPresenterImpl implements MainPresenter{

    private Activity activity;
    private MainActivityView activityView;
    private SharedPreferences sharedPreferences;
    private static String MUTE_ENABLED = "mute_enabled";

    private int secondsPerQuestion = 20;
    private int numberOfQuestions = 5;

    MainPresenterImpl(Activity activity, MainActivityView activityView){
        this.activity = activity;
        this.activityView = activityView;
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public void onCreate() {
        activityView.showMuteIcon(getMuteBoolean());
    }

    @Override
    public void startGameClicked() {
        startGameActivity();
    }

    private void startGameActivity() {
        SetOfQuestions setOfQuestions = getSetOfQuestions();
        Intent intent = new Intent(activity, RedInfoActivity.class);
        intent.putExtra("QuestionSet", setOfQuestions);
        activity.startActivity(intent);
    }

    private SetOfQuestions getSetOfQuestions(){
        return new SetOfQuestions(numberOfQuestions,secondsPerQuestion, getQuestions(), getAnswers());
    }

    private ArrayList<Question> getQuestions(){
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Który księżyc jako jedyny w Układzie Słonecznym posiada gęstą atmosferę?",
                "Tytan", "Księżyc", "Io", "Europa"));
        questions.add(new Question("Jądro której galaktyki znajduje się najbliżej Układu Słonecznego?",
                "Karzeł Wielkiego Psa", "Karzeł Strzelca",
                "Wielki Obłok Magellana", "Droga Mleczna"));
        questions.add(new Question("Jaką średnicę ma Mars?", "6794 km",
                "6894 km", "6994 km", "6694 km"));
        questions.add(new Question("Fobos jest księżycem której planety?",
                "Marsa", "Uranu", "Saturna", "Jowisza"));
        questions.add(new Question("Co było pierwszą udaną misją planetarną NASA?",
                "Zbliżenie się do Wenus", "Zbliżenie się do Słońca",
                "Zbliżenie się do Plutona", "Zbliżenie się do Marsa"));
        return questions;
    }

    private ArrayList<Answer> getAnswers(){
        ArrayList<Answer> answers = new ArrayList<>();
        for(int i =1; i<=numberOfQuestions; i++){
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            Collections.reverse(integers);
            answers.add(new Answer(0, integers));
        }
        return answers;
    }

    @Override
    public void muteClicked() {
        boolean muteEnabled = getMuteBoolean();
        activityView.showMuteIcon(!muteEnabled);
        setMute(!muteEnabled);
    }

    private boolean getMuteBoolean(){
        return sharedPreferences.getBoolean(MUTE_ENABLED, true);
    }

    private void setMute(boolean enabled){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MUTE_ENABLED, enabled);
        editor.apply();
    }

    @Override
    public void settingsClicked() {

    }

    @Override
    public void statisticsClicked() {

    }
}
