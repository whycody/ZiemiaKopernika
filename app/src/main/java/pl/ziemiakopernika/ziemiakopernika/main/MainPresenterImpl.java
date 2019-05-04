package pl.ziemiakopernika.ziemiakopernika.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDao;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.redinfo.RedInfoActivity;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsBottomSheet;

public class MainPresenterImpl implements MainPresenter{

    private MainActivity activity;
    private Context context;
    private MainActivityView activityView;
    private SharedPreferences sharedPreferences;
    private QuestionsDao questionsDao;

    private static String MUTE_ENABLED = "mute_enabled";
    public static final String QUESTION_SET = "QuestionSet";
    private int secondsPerQuestion = 15;
    private int numberOfQuestions = 5;

    MainPresenterImpl(Activity activity, MainActivityView activityView){
        this.activity = (MainActivity)activity;
        this.activityView = activityView;
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        questionsDao = new QuestionsDaoImpl(activity);
    }

    @Override
    public void onCreate() {
        activityView.showMuteIcon(getMuteBoolean());
    }

    @Override
    public void startGameClicked(View view) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, view, "transition");
        startGameActivity(view,optionsCompat);
    }

    private void startGameActivity(View view, ActivityOptionsCompat optionsCompat){
        SetOfQuestions setOfQuestions = getSetOfQuestions();
        Intent intent = new Intent(activity, RedInfoActivity.class);
        intent.putExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_X, getRevealX(view));
        intent.putExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_Y, getRevealY(view));
        intent.putExtra(QUESTION_SET, setOfQuestions);
        ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
    }

    private int getRevealX(View view){
        return (int) (view.getX() + view.getWidth() / 2);
    }

    private int getRevealY(View view){
        return (int) (view.getY() + view.getHeight() / 2);
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
        StatisticsBottomSheet bottomSheet = new StatisticsBottomSheet();
        bottomSheet.show(activity.getSupportFragmentManager(), "fragmentManager");
    }
}
