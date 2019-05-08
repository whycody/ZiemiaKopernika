package pl.ziemiakopernika.ziemiakopernika.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import pl.ziemiakopernika.ziemiakopernika.R;

public class MainActivity extends AppCompatActivity implements MainView {

    private ImageView earthView;
    private ImageButton muteBtn, settingsBtn, statisticsBtn;
    private Button startGameBtn;
    private PulsatorLayout pulsatorLayout;
    private View animationView;
    private TextView curiosityView;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        earthView = findViewById(R.id.earth_view);
        muteBtn = findViewById(R.id.mute_btn);
        settingsBtn = findViewById(R.id.settings_btn);
        statisticsBtn = findViewById(R.id.statistics_btn);
        pulsatorLayout = findViewById(R.id.pulsator);
        startGameBtn = findViewById(R.id.start_game_btn);
        animationView = findViewById(R.id.animation_view);
        curiosityView = findViewById(R.id.curiosity_text);

        mainPresenter = new MainPresenterImpl(this, this);
        mainPresenter.onCreate();

        startEarthAnimation();
        pulsatorLayout.start();
        startGameBtn.setOnClickListener(startGameOnClick);
        muteBtn.setOnClickListener(muteOnClick);
        settingsBtn.setOnClickListener(settingsOnClick);
        statisticsBtn.setOnClickListener(statisticsOnClick);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    private void startEarthAnimation(){
        Animation rotateEarth = AnimationUtils.loadAnimation(this, R.anim.rotate_earth);
        earthView.startAnimation(rotateEarth);
    }

    private View.OnClickListener startGameOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainPresenter.startGameClicked(animationView);
        }
    };

    private View.OnClickListener muteOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainPresenter.muteClicked();
        }
    };

    private View.OnClickListener settingsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainPresenter.settingsClicked();
        }
    };

    private View.OnClickListener statisticsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainPresenter.statisticsClicked();
        }
    };

    @Override
    public void showMuteIcon(boolean enabled) {
        if(enabled)
            muteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_muteon));
        else
            muteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_muteoff));
    }

    @Override
    public void setCuriosityText(String text) {
        curiosityView.setText(text);
    }

    @Override
    public PulsatorLayout getPulsatorLayout() {
        return pulsatorLayout;
    }
}
