package pl.ziemiakopernika.ziemiakopernika.statistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDao;
import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsDaoImpl;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.redinfo.RedInfoActivity;

public class StatisticsPresenterImpl implements StatisticsPresenter {

    private Activity activity;
    private StatisticsView statisticsView;
    private StatisticsDao statisticsDao;

    private int correctAnswers, uncorrectAnswers;

    public StatisticsPresenterImpl(Activity activity, StatisticsView statisticsView){
        this.activity = activity;
        this.statisticsView = statisticsView;
        statisticsDao = new StatisticsDaoImpl(activity);
        correctAnswers = statisticsDao.getCorrectAnswersNumberStatistics();
        uncorrectAnswers = statisticsDao.getUncorrectAnswersNumberStatistics();
    }

    @Override
    public void onCreate() {
        statisticsView.setRoundsPlayedText(getGamesPlayedText());
        statisticsView.setQuestionsAnsweredText(getQuestionsAnsweredText());
        statisticsView.setCorrectAnswersText(getCorrectAnswersText());
        statisticsView.setEarnedCoinsText(getEarnedCoinsText());
        statisticsView.setSpentCoinsText(getSpentCoinsText());
        statisticsView.setLifebuoyText(getLifebuoyText());
        statisticsView.setSecondsLeftPerQuestionText(getSecondsLeftPerQuestionText());
        statisticsView.setCorrectAnswersProgress(statisticsDao.getQuestionsAnsweredStatistics(),
                statisticsDao.getCorrectAnswersNumberStatistics());
        startCorrectAnswersProgressBarAnimation();
        statisticsView.setEarnedCoinsProgress(statisticsDao.getEarnedCoinsStatistics()+20,
                statisticsDao.getCoinsStatistics());
        startEarnedCoinsProgressBarAnimation();
    }

    private void startCorrectAnswersProgressBarAnimation(){
        ProgressBarAnimation progressBarAnimation = new ProgressBarAnimation(statisticsView.getCorrectAnswersProgressBar(),
                0, statisticsDao.getCorrectAnswersNumberStatistics());
        progressBarAnimation.setDuration(1000);
        statisticsView.getCorrectAnswersProgressBar().startAnimation(progressBarAnimation);
        if (statisticsDao.getQuestionsAnsweredStatistics()!=0) {
            if ((statisticsDao.getQuestionsAnsweredStatistics() * 100) / statisticsDao.getCorrectAnswersNumberStatistics() > 5)
                statisticsView.getCorrectAnswersProgressBar().setProgressDrawable
                        (activity.getResources().getDrawable(R.drawable.progress_rounded_drawable));
        }
    }

    private void startEarnedCoinsProgressBarAnimation(){
        Animation rotateAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotate_earth);
        rotateAnimation.setDuration(5000);
        statisticsView.getEarnedCoinsProgressBar().startAnimation(rotateAnimation);
    }

    private String getGamesPlayedText(){
        return "Dotychczas udało Ci się rozegrać " + statisticsDao.getGamesPlayedStatistics() + " rund.";
    }

    private String getQuestionsAnsweredText(){
        return "Odpowiedziałeś przy tym na " + statisticsDao.getQuestionsAnsweredStatistics() + " pytań.";
    }

    private String getCorrectAnswersText(){
        if(correctAnswers>10)
            return "Aż " + correctAnswers + " z nich było prawidłowych, gratulacje!";
        else
            return correctAnswers + " z nich było prawidłowych";
    }

    private String getEarnedCoinsText(){
        return "W ciągu całej gry zarobiłeś " + statisticsDao.getEarnedCoinsStatistics() + " monet";
    }

    private String getSpentCoinsText(){
        if(statisticsDao.getEarnedCoinsStatistics()!=0 && statisticsDao.getSpentCoinsStatistics()!=0)
            return "Wydałeś już " + statisticsDao.getSpentCoinsStatistics() + " z nich i posiadasz "
                + statisticsDao.getPercentageOfCoinsHaveStatistics() + "% z całej zgromadzonej sumy";
        else if(statisticsDao.getEarnedCoinsStatistics()!=0 && statisticsDao.getSpentCoinsStatistics()==0)
            return "Nie wydałeś jeszcze nic z nich i posiadasz "
                    + statisticsDao.getPercentageOfCoinsHaveStatistics() + "% z całej zgromadzonej sumy";
        else if(statisticsDao.getEarnedCoinsStatistics()==0 && statisticsDao.getSpentCoinsStatistics()==0)
            return "Nie wydałeś jeszcze żadnej monety ani żadnej nie zarobiłeś. Czas rozegrać rundę!";
        else
            return "Jeszcze nic nie zarobiłeś. Na co czekasz?";
    }

    private String getLifebuoyText(){
        if(statisticsDao.getLifebuoysPerQuestionStatistics()==0)
            return "Nie skorzystałeś jeszcze z żadnego koła ratunkowego. Do dzieła!";
        else
            return "Co około " + statisticsDao.getLifebuoysPerQuestionStatistics() +
                    " pytanie sięgałeś po koło ratunkowe 50/50 lub +20";
    }

    private String getSecondsLeftPerQuestionText(){
        double timeLeftPerQuestion = statisticsDao.getTimeLeftPerQuestionStatistics();
        if(timeLeftPerQuestion>5)
            return "Średnio na pytanie zostawało ci jeszcze aż " + timeLeftPerQuestion + " sekund!";
        else
            return "Średnio na pytanie zostawało ci jeszcze " + timeLeftPerQuestion + " sekund!";
    }

    @Override
    public void onPlayAgainBtnClicked(View view) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, view, "transition");
        SetOfQuestions setOfQuestions = getNewSetOfQuestions();
        Intent intent = new Intent(activity, RedInfoActivity.class);
        intent.putExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_X, getRevealX(view));
        intent.putExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_Y, getRevealY(view));
        intent.putExtra(MainPresenterImpl.QUESTION_SET, setOfQuestions);
        ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
    }

    private SetOfQuestions getNewSetOfQuestions(){
        int numberOfQuestions = 5;
        int secondsPerQuestion = 15;
        QuestionsDao questionsDao = new QuestionsDaoImpl(activity);
        return new SetOfQuestions(numberOfQuestions, secondsPerQuestion,
                questionsDao.getRandomNotShowedQuestions(numberOfQuestions), questionsDao.getRandomAnswers(numberOfQuestions));
    }

    private int getRevealX(View view){
        return (int) (view.getX() + view.getWidth() / 2);
    }

    private int getRevealY(View view){
        return (int) (view.getY() + view.getHeight() / 2);
    }
}
