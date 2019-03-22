package pl.ziemiakopernika.ziemiakopernika.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDao;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.redinfo.RedInfoActivity;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;

public class MainPresenterImpl implements MainPresenter{

    private Activity activity;
    private MainActivityView activityView;
    private SharedPreferences sharedPreferences;
    private QuestionsDao questionsDao;

    private static String MUTE_ENABLED = "mute_enabled";
    public static final String QUESTION_SET = "QuestionSet";
    private int secondsPerQuestion = 20;
    private int numberOfQuestions = 5;

    MainPresenterImpl(Activity activity, MainActivityView activityView){
        this.activity = activity;
        this.activityView = activityView;
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        questionsDao = new QuestionsDaoImpl();
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
        intent.putExtra(QUESTION_SET, setOfQuestions);
        activity.startActivity(intent);
    }

    private SetOfQuestions getSetOfQuestions(){
        return new SetOfQuestions(numberOfQuestions,secondsPerQuestion,
                questionsDao.getRandomQustions(5), questionsDao.getRandomAnswers(5));
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
