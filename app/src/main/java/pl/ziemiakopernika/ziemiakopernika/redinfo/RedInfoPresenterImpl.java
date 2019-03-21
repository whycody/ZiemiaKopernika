package pl.ziemiakopernika.ziemiakopernika.redinfo;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;

public class RedInfoPresenterImpl implements RedInfoPresenter {

    private Activity activity;
    private RedInfoView redInfoView;
    private SetOfQuestions setOfQuestions;

    RedInfoPresenterImpl(Activity activity, RedInfoView redInfoView){
        this.activity = activity;
        this.redInfoView = redInfoView;
        this.setOfQuestions = getSetOfQuestions();
    }

    @Override
    public void onCreate() {
        redInfoView.setQuestionsNumber(setOfQuestions.getNumOfQuestion());
        redInfoView.setSecondsNumber(setOfQuestions.getSecondsPerQuestion());
    }

    @Override
    public void onAnimationEnded() {

    }

    private SetOfQuestions getSetOfQuestions(){
        return (SetOfQuestions)activity.getIntent().getSerializableExtra(MainPresenterImpl.QUESTION_SET);
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
}
