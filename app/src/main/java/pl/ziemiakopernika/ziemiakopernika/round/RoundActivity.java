package pl.ziemiakopernika.ziemiakopernika.round;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.timer.Timer;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerImpl;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerReact;

public class RoundActivity extends AppCompatActivity implements RoundView, TimerReact {

    private TextView roundView;
    private LinearLayout roundLayout;
    private RoundPresenter roundPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        roundView = findViewById(R.id.round_view);
        roundLayout = findViewById(R.id.round_layout);
        roundPresenter = new RoundPresenterImpl(this, this);
        roundPresenter.onCreate();

        new TimerImpl(1500, this).startTimer();
    }

    @Override
    public void setRoundText(String roundText) {
        roundView.setText(roundText);
    }

    @Override
    public void addViewToLinearLayout(View view) {
        roundLayout.addView(view);
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
