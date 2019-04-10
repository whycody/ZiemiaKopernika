package pl.ziemiakopernika.ziemiakopernika.round;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerImpl;
import pl.ziemiakopernika.ziemiakopernika.timer.TimerReact;

public class RoundActivity extends AppCompatActivity implements RoundView, TimerReact {

    private TextView roundView;
    private View summaryView;
    private LinearLayout roundLayout;
    private RoundPresenter roundPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        roundView = findViewById(R.id.round_view);
        summaryView = findViewById(R.id.summary_view);
        roundLayout = findViewById(R.id.round_layout);
        roundPresenter = new RoundPresenterImpl(this, this);
        roundPresenter.onCreate();

        new TimerImpl(1000, this).startTimer();
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
    public void setSummaryViewBackground(Drawable drawable) {
        summaryView.setBackground(drawable);
    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {
        roundPresenter.onFinish();
    }
}
