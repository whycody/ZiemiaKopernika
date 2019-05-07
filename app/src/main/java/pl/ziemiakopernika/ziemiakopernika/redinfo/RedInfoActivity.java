package pl.ziemiakopernika.ziemiakopernika.redinfo;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;

public class RedInfoActivity extends AppCompatActivity implements Animation.AnimationListener, RedInfoView {

    private LinearLayout questionsLayout, secondsLayout;
    private TextView goodLuckView, questionsNumberView, secondsNumberView;
    private RedInfoPresenter redInfoPresenter;

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

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
        redInfoPresenter.startAnimation(findViewById(R.id.red_info_activity), savedInstanceState);
        redInfoPresenter.onCreate();
        startAllAnimations();
        playSongIfMuteDisabled();
    }

    private void playSongIfMuteDisabled(){
        if(!getMuteEnabled()) {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.redsong);
            mediaPlayer.start();
        }
    }

    private boolean getMuteEnabled(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferences", MODE_PRIVATE);
        return sharedPreferences.getBoolean(MainPresenterImpl.MUTE_ENABLED, false);
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
    public void setQuestionsNumber(String number) {
        questionsNumberView.setText(number);
    }

    @Override
    public void setSecondsNumber(String number) {
        secondsNumberView.setText(number);
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
