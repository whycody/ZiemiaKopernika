package pl.ziemiakopernika.ziemiakopernika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView earthView;
    private ImageButton muteBtn, settingsBtn, statisticsBtn;
    private Button startGameBtn;
    private PulsatorLayout pulsatorLayout;

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

        pulsatorLayout.start();
        startEarthAnimation();
        startButtonsAnimations();
        startGameBtn.setOnClickListener(startGameOnClick);
    }

    private void startEarthAnimation(){
        Animation rotateEarth = AnimationUtils.loadAnimation(this, R.anim.rotate_earth);
        earthView.startAnimation(rotateEarth);
    }

    private void startButtonsAnimations(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_from_down);
        muteBtn.startAnimation(animation);
        settingsBtn.startAnimation(animation);
        statisticsBtn.startAnimation(animation);
    }

    private View.OnClickListener startGameOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startGameActivity();
        }
    };

    private void startGameActivity() {
        Intent intent = new Intent(this, RedInfoActivity.class);
        startActivity(intent);
    }

}
