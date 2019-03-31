package pl.ziemiakopernika.ziemiakopernika.round;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.timer.Timer;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerImpl;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerReact;

public class RoundActivity extends AppCompatActivity implements TimerReact {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        new TimerImpl(2000, this).startTimer();
    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
