package pl.ziemiakopernika.ziemiakopernika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView earthView;
    private PulsatorLayout pulsatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        earthView = findViewById(R.id.earth_view);
        pulsatorLayout = findViewById(R.id.pulsator);

        pulsatorLayout.start();
        startEarthAnimation();
    }

    private void startEarthAnimation(){
        Animation rotateEarth = AnimationUtils.loadAnimation(this, R.anim.rotate_earth);
        earthView.startAnimation(rotateEarth);
    }
}
