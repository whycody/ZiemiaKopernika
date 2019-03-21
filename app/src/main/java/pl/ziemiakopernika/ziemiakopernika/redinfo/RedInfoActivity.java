package pl.ziemiakopernika.ziemiakopernika.redinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class RedInfoActivity extends AppCompatActivity implements Animation.AnimationListener, RedInfoView {

    private LinearLayout questionsLayout, secondsLayout;
    private TextView goodLuckView, questionsNumberView, secondsNumberView;
    private RedInfoPresenter redInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_info);

        questionsLayout = findViewById(R.id.questions_layout);
        secondsLayout = findViewById(R.id.seconds_layout);
        goodLuckView = findViewById(R.id.good_luck_view);
        questionsNumberView = findViewById(R.id.questions_number_view);
        secondsNumberView = findViewById(R.id.seconds_number_view);

        redInfoPresenter = new RedInfoPresenterImpl(this, this);
        redInfoPresenter.onCreate();
        startAllAnimations();
    }

    private void startAllAnimations(){
        questionsLayout.startAnimation(redInfoPresenter.getTranslateFromLeftAnimation());
        secondsLayout.startAnimation(redInfoPresenter.getTranslateFromRightAnimation());
        goodLuckView.startAnimation(getTranslateFromDownAnimation());
    }

    private Animation getTranslateFromDownAnimation(){
        Animation animation = redInfoPresenter.getTranslateFromDownAnimation();
        animation.setAnimationListener(this);
        return animation;
    }

    @Override
    public void setQuestionsNumber(int number) {
        questionsNumberView.setText(number + "");
    }

    @Override
    public void setSecondsNumber(int number) {
        secondsNumberView.setText(number + "");
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        redInfoPresenter.onAnimationEnded();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
