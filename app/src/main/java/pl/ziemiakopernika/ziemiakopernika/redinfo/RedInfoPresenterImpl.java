package pl.ziemiakopernika.ziemiakopernika.redinfo;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfRounds;
import pl.ziemiakopernika.ziemiakopernika.question.QuestionActivity;

public class RedInfoPresenterImpl implements RedInfoPresenter {

    private Activity activity;
    private RedInfoView redInfoView;
    private SetOfQuestions setOfQuestions;
    private SetOfRounds setOfRounds;

    RedInfoPresenterImpl(Activity activity, RedInfoView redInfoView){
        this.activity = activity;
        this.redInfoView = redInfoView;
        this.setOfQuestions = getSetOfQuestions();
        setOfRounds = getSetOfRounds();
    }

    @Override
    public void onCreate() {
        int numberOfQuestions = setOfRounds.getSetOfQuestions().get(0).getNumOfQuestion();
        if(numberOfQuestions<10) redInfoView.setQuestionsNumber("0" + numberOfQuestions);
        else redInfoView.setQuestionsNumber(setOfQuestions.getNumOfQuestion() + "");
        redInfoView.setSecondsNumber(setOfRounds.getSetOfQuestions().get(0).getSecondsPerQuestion() + "");
    }

    @Override
    public void onAnimationEnded() {
        Intent intent = new Intent(activity, QuestionActivity.class);
        intent.putExtra(MainPresenterImpl.QUESTION_SET, setOfQuestions);
        intent.putExtra(MainPresenterImpl.ROUND_SET, setOfRounds);
        activity.startActivity(intent);
        activity.finish();
    }

    private void printDebbuging(SetOfRounds setOfRounds){
        for(int i = 0; i<setOfRounds.getSetOfQuestions().size(); i++){
            SetOfQuestions setOfQuestions = setOfRounds.getSetOfQuestions().get(i);
            Log.d("REDTAG", "SetOFQuestion: " + (i+1));
            for(int j=0; j<setOfQuestions.getNumOfQuestion(); j++) {
                Log.d("REDTAG", setOfQuestions.getQuestions().get(j).getQuestion() + " " + setOfQuestions.getQuestions().get(j).getAnswerOne()
                        + ", " + setOfQuestions.getQuestions().get(j).getAnswerTwo() + ", " + setOfQuestions.getQuestions().get(j).getAnswerThree()
                        + ", " + setOfQuestions.getQuestions().get(j).getAnswerFour());
            }
        }
    }

    private SetOfQuestions getSetOfQuestions(){
        return (SetOfQuestions)activity.getIntent().getSerializableExtra(MainPresenterImpl.QUESTION_SET);
    }

    private SetOfRounds getSetOfRounds(){
        return (SetOfRounds)activity.getIntent().getSerializableExtra(MainPresenterImpl.ROUND_SET);
    }

    @Override
    public Animation getTranslateFromLeftAnimation() {
        return AnimationUtils.loadAnimation(activity, R.anim.translate_from_left);
    }

    @Override
    public Animation getTranslateFromRightAnimation() {
        return AnimationUtils.loadAnimation(activity, R.anim.translate_from_right);
    }

    @Override
    public Animation getTranslateFromDownAnimation() {
        return AnimationUtils.loadAnimation(activity, R.anim.translate_from_down_red);
    }

    private int revealX, revealY;

    @Override
    public void startAnimation(final View view, Bundle savedInstanceState) {
        Intent intent = activity.getIntent();
        if (savedInstanceState == null && intent.hasExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_Y)) {
            view.setVisibility(View.INVISIBLE);
            revealX = intent.getIntExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(RedInfoActivity.EXTRA_CIRCULAR_REVEAL_Y, 0);
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(new Point(revealX, revealY), view);
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    private void revealActivity(Point point, View view){
        float finalRadius = (float) (Math.max(view.getWidth(), view.getHeight()) * 1.1);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal
                (view, point.x, point.y, 0, finalRadius);
        circularReveal.setDuration(600);
        circularReveal.setInterpolator(new AccelerateInterpolator());
        view.setVisibility(View.VISIBLE);
        circularReveal.start();
    }
}
