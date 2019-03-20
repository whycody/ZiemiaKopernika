package pl.ziemiakopernika.ziemiakopernika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RedInfoActivity extends AppCompatActivity implements Animation.AnimationListener {

    private LinearLayout questionsLayout, secondsLayout;
    private TextView goodLuckView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_info);

        questionsLayout = findViewById(R.id.questions_layout);
        secondsLayout = findViewById(R.id.seconds_layout);
        goodLuckView = findViewById(R.id.good_luck_view);
        startAllAnimations();
    }

    private void startAllAnimations(){
        questionsLayout.startAnimation(getTranslateFromLeftAnimation());
        secondsLayout.startAnimation(getTranslateFromRightAnimation());
        goodLuckView.startAnimation(getTranslateFromDownAnimation());
    }

    private Animation getTranslateFromRightAnimation(){
        return AnimationUtils.loadAnimation(this, R.anim.translate_from_right);
    }

    private Animation getTranslateFromLeftAnimation(){
        return AnimationUtils.loadAnimation(this, R.anim.translate_from_left);
    }

    private Animation getTranslateFromDownAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_from_down_red);
        animation.setAnimationListener(this);
        return animation;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
