package pl.ziemiakopernika.ziemiakopernika.statistics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import pl.ziemiakopernika.ziemiakopernika.question.QuestionPresenterImpl;

public class StatisticsDaoImpl implements StatisticsDao {

    private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String GAMES_PLAYED = "gamesPlayed";
    private static final String TIME_LEFT = "timeLeft";
    private static final String CORRECT_ANSWERS = "correctAnswers";
    private static final String UNCORRECT_ANSWERS = "uncorrectAnswers";
    private static final String PERCENTAGE_TOTAL = "percentageTotal";
    private static final String EARNED_COINS = "earnedCoins";
    private static final String LIFEBUOYS_TAKED = "lifebuoysTaked";

    public StatisticsDaoImpl(Activity activity){
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    @Override
    public void saveGamesPlayedStatistics() {
        editor.putInt(GAMES_PLAYED, sharedPreferences.getInt(GAMES_PLAYED, 0)+1).commit();
    }

    @Override
    public void saveLifebuoyStatistics() {
        editor.putInt(LIFEBUOYS_TAKED, sharedPreferences.getInt(LIFEBUOYS_TAKED, 0)+1).commit();
    }

    @Override
    public void saveTimeLeftStatistics(int timeLeft) {
        editor.putInt(TIME_LEFT, sharedPreferences.getInt(TIME_LEFT, 0)+timeLeft).commit();
    }

    @Override
    public void saveCorrectAnswersNumberStatistics(int correctAnswers) {
        editor.putInt(CORRECT_ANSWERS, sharedPreferences.getInt(CORRECT_ANSWERS, 0)+correctAnswers).commit();
    }

    @Override
    public void saveUncorrectAnswersNumberStatistics(int uncorrectAnswers) {
        editor.putInt(UNCORRECT_ANSWERS, sharedPreferences.getInt(UNCORRECT_ANSWERS, 0)+uncorrectAnswers).commit();
    }

    @Override
    public void savePercentageCorrectnessStatistics(int percentageCorrectness) {
        editor.putInt(PERCENTAGE_TOTAL, sharedPreferences.getInt(PERCENTAGE_TOTAL, 0)+percentageCorrectness).commit();
    }

    @Override
    public void saveEarnedCoinsStatistics(int earnedCoins) {
        editor.putInt(EARNED_COINS, sharedPreferences.getInt(EARNED_COINS, 0)+earnedCoins).commit();
    }

    @Override
    public int getGamesPlayedStatistics() {
        return sharedPreferences.getInt(GAMES_PLAYED, 0);
    }

    @Override
    public int getLifebuoysPerQuestionStatistics() {
        if(sharedPreferences.getInt(LIFEBUOYS_TAKED, 0)==0)
            return 0;
        else
            return getQuestionsAnsweredStatistics()/sharedPreferences.getInt(LIFEBUOYS_TAKED, 0);
    }

    @Override
    public int getQuestionsAnsweredStatistics() {
        return getGamesPlayedStatistics() * 5;
    }

    @Override
    public int getTimeLeftTotalStatistics() {
        return sharedPreferences.getInt(TIME_LEFT, 0);
    }

    @Override
    public double getTimeLeftPerQuestionStatistics() {
        int timeLeftTotal = getTimeLeftTotalStatistics();
        int gamesPlayed = getGamesPlayedStatistics();
        if(timeLeftTotal!=0 && gamesPlayed!=0)
            return (double) Math.round(timeLeftTotal / gamesPlayed * 10) / 50;
        else return 0;
    }

    @Override
    public int getCorrectAnswersNumberStatistics() {
        return sharedPreferences.getInt(CORRECT_ANSWERS, 0);
    }

    @Override
    public int getUncorrectAnswersNumberStatistics() {
        return sharedPreferences.getInt(UNCORRECT_ANSWERS, 0);
    }

    @Override
    public double getPercentageCorrectnessAverageStatistics() {
        int percentageCorrectnessTotal = sharedPreferences.getInt(PERCENTAGE_TOTAL, 0);
        int gamesPlayed = getGamesPlayedStatistics();
        return (double) Math.round(percentageCorrectnessTotal / gamesPlayed * 10) / 10;
    }

    @Override
    public int getEarnedCoinsStatistics() {
        return sharedPreferences.getInt(EARNED_COINS, 0);
    }

    @Override
    public int getCoinsStatistics() {
        return sharedPreferences.getInt(QuestionPresenterImpl.COINS, 20);
    }

    @Override
    public int getSpentCoinsStatistics() {
        int coins = getCoinsStatistics();
        int coinsInGame = getEarnedCoinsStatistics() + 20;
        return coinsInGame - coins;
    }

    @Override
    public int getPercentageOfCoinsHaveStatistics(){
        if(getEarnedCoinsStatistics()!=0 && getCoinsStatistics() != 0)
            return (getCoinsStatistics()*100)/getEarnedCoinsStatistics();
        else
            return 100;
    }
}
