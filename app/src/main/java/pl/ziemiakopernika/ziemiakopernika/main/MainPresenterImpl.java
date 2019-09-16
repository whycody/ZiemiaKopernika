package pl.ziemiakopernika.ziemiakopernika.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.credits.CreditsBottomSheet;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDao;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.model.Question;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfRounds;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.redinfo.RedInfoActivity;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsBottomSheet;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsDao;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsPresenter;
import pl.ziemiakopernika.ziemiakopernika.statistics.StatisticsPresenterImpl;

public class MainPresenterImpl implements MainPresenter, MediaPlayer.OnCompletionListener {

    private MainActivity activity;
    private MainView mainView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor preferencesEditor;
    private StatisticsDao statisticsDao;
    private QuestionsDao questionsDao;
    private MediaPlayer mediaPlayer;

    public static final String MUTE_ENABLED = "mute_enabled";
    public static final String QUESTION_SET = "QuestionSet";
    public static final String FIRST_LOGIN = "FirstLogin";
    private int secondsPerQuestion = 20;
    private int numberOfQuestions = 5;
    private int numberOfRounds = 3;
    private boolean muteEnabled;

    MainPresenterImpl(Activity activity, MainView mainView){
        this.activity = (MainActivity)activity;
        this.mainView = mainView;
        sharedPreferences = activity.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        preferencesEditor = sharedPreferences.edit();
        statisticsDao = new StatisticsDaoImpl(activity);
        questionsDao = new QuestionsDaoImpl(activity);
        mediaPlayer = MediaPlayer.create(activity, R.raw.backgrounds);
        mediaPlayer.setOnCompletionListener(this);
        muteEnabled = getMuteBoolean();
    }

    private boolean getMuteBoolean(){
        return sharedPreferences.getBoolean(MUTE_ENABLED, false);
    }

    @Override
    public void onCreate() {
        mainView.showMuteIcon(muteEnabled);
        if(!muteEnabled)
            mediaPlayer.start();
        mainView.setCuriosityText(getRandomCuriosity());
        checkFirstLogin();
    }

    private void checkFirstLogin(){
        if(sharedPreferences.getBoolean(FIRST_LOGIN, true)){
            statisticsDao.saveEarnedCoinsStatistics(30);
            preferencesEditor.putInt(QuestionPresenterImpl.COINS, 30);
            preferencesEditor.putBoolean(FIRST_LOGIN, false);
            preferencesEditor.apply();
        }
    }

    private String getRandomCuriosity(){
        Random random = new Random();
        ArrayList<String> curiosities = getCuriosities();
        return curiosities.get(random.nextInt(curiosities.size()));
    }

    private ArrayList<String> getCuriosities(){
        ArrayList<String> curiosities = new ArrayList<>();
        curiosities.add("Obecnie rękopis dzieła Kopernika przechowywany jest w Bibliotece Jagielońskiej");
        curiosities.add("Mikołaj Kopernik zmarł na skutek wylewu krwi do mózgu");
        curiosities.add("Od roku 1616 do 1822 dzieło Kopernika było figurantem wykazu ksiąg zakazanych");
        curiosities.add("Pierwszy wydruk „O obrotach sfer niebieskich” pojawił się w 1543 roku, tuż przed śmiercią Kopernika");
        curiosities.add("Na cześć astronoma nazwano jeden z kraterów na księżycu i na marsie, oraz planetoidę Coppernicus");
        curiosities.add("W 1965 roku NBP wyprodukował banknot z wizerunkiem Mikołaja Kopernika");
        curiosities.add("W 2010 na cześć Kopernika nazwano pierwiastek chemiczny");
        curiosities.add("Kopenik znał tylko podstawy języka polskiego");
        curiosities.add("Kopernik nie miał lunety ani teleskopu do obserwacji astronomicznych");
        curiosities.add("W Toruniu znajduje się Dom Kopernika będący muzeum poświęconym astronomowi");
        return curiosities;
    }

    private boolean songWasPlayed = false;

    @Override
    public void onPause() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            songWasPlayed = true;
        }
    }

    @Override
    public void onResume() {
        if(!mainView.getPulsatorLayout().isStarted())
            mainView.getPulsatorLayout().start();
        if(songWasPlayed) {
            mediaPlayer.start();
            songWasPlayed = !songWasPlayed;
        }
    }

    @Override
    public void startGameClicked(View view) {
        mainView.getPulsatorLayout().stop();
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

    private SetOfRounds getSetOfRounds(){
        SetOfRounds setOfRounds = new SetOfRounds();
        setOfRounds.setNumOfRounds(numberOfRounds);
        ArrayList<SetOfQuestions> setsOfQuestions = new ArrayList<>();
        ArrayList notShowedQuestions = questionsDao.getRandomNotShowedQuestions(numberOfQuestions*numberOfRounds);
        for(int i=0;i<numberOfQuestions*numberOfRounds;i+=numberOfQuestions){
            ArrayList newQuestions = new ArrayList();
            for(int j=i; j<numberOfQuestions+i; j++)
                newQuestions.add(notShowedQuestions.get(j));
            setsOfQuestions.add(getSetOfQuestions(newQuestions));
        }
        setOfRounds.setSetOfQuestions(setsOfQuestions);

        printDebbuging(setOfRounds);
        return setOfRounds;
    }

    private void printDebbuging(SetOfRounds setOfRounds){
        for(int i=0;i<setOfRounds.getNumOfRounds();i++){
            SetOfQuestions setOfQuestions = setOfRounds.getSetOfQuestions().get(i);
            Log.d("MOJTAG", "SetOFQuestion: " + (i+1));
            for(int j=0; j<setOfQuestions.getNumOfQuestion(); j++) {
                Log.d("MOJTAG", setOfQuestions.getQuestions().get(j).getQuestion() + " " + setOfQuestions.getQuestions().get(j).getAnswerOne()
                        + ", " + setOfQuestions.getQuestions().get(j).getAnswerTwo() + ", " + setOfQuestions.getQuestions().get(j).getAnswerThree()
                        + ", " + setOfQuestions.getQuestions().get(j).getAnswerFour());
            }
        }
    }

    private SetOfQuestions getSetOfQuestions(ArrayList<Question> questions){
        return new SetOfQuestions(numberOfQuestions,secondsPerQuestion,
                questions, questionsDao.getRandomAnswers(numberOfQuestions));
    }

    private SetOfQuestions getSetOfQuestions(){
        return new SetOfQuestions(numberOfQuestions,secondsPerQuestion,
                questionsDao.getRandomNotShowedQuestions(numberOfQuestions), questionsDao.getRandomAnswers(numberOfQuestions));
    }

    @Override
    public void muteClicked() {
        muteEnabled = !muteEnabled;
        mainView.showMuteIcon(muteEnabled);
        setMute(muteEnabled);
        if(muteEnabled) mediaPlayer.stop();
        else{
            tryPrepareAsync();
            mediaPlayer.start();
        }
    }

    private void tryPrepareAsync(){
        try {
            mediaPlayer.prepareAsync();
        }catch(Exception ignored){ }
    }

    private void setMute(boolean enabled){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MUTE_ENABLED, enabled);
        editor.apply();
    }

    @Override
    public void creditsClicked() {
        CreditsBottomSheet creditsBottomSheet = new CreditsBottomSheet();
        creditsBottomSheet.show(activity.getSupportFragmentManager(), "fragmentManager");
    }

    @Override
    public void statisticsClicked() {
        StatisticsBottomSheet bottomSheet = new StatisticsBottomSheet();
        bottomSheet.show(activity.getSupportFragmentManager(), "fragmentManager");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
