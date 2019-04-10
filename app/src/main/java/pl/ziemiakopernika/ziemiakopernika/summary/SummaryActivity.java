package pl.ziemiakopernika.ziemiakopernika.summary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class SummaryActivity extends AppCompatActivity implements SummaryView{

    private View summaryBackground;
    private LinearLayout roundLayout;
    private TextView badgeText, timeLeftText, answersProportionsText, congratulationsText;
    private Button coinsForTimeBtn, coinsForCorrectAnswersBtn, totalCoinsBtn;
    private SummaryPresenter summaryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summaryPresenter = new SummaryPresenterImpl(this, this);
        summaryBackground = findViewById(R.id.summary_background);
        roundLayout = findViewById(R.id.round_layout);
        badgeText = findViewById(R.id.badge_text);
        timeLeftText = findViewById(R.id.time_left_text);
        answersProportionsText = findViewById(R.id.answers_proportions_text);
        congratulationsText = findViewById(R.id.congratulations_text);
        coinsForTimeBtn = findViewById(R.id.coins_for_time_btn);
        coinsForCorrectAnswersBtn = findViewById(R.id.coins_for_correct_answers_btn);
        totalCoinsBtn = findViewById(R.id.total_coins_btn);
        summaryPresenter.onCreate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        summaryPresenter.onBackPressed();
    }

    @Override
    public void setSummaryBackgroundColor(int color) {
        summaryBackground.setBackgroundColor(color);
    }

    @Override
    public void addViewToRoundLayout(View view) {
        roundLayout.addView(view);
    }

    @Override
    public void setBadgeText(String badge) {
        badgeText.setText(badge);
    }

    @Override
    public void setTimeLeftText(String time) {
        timeLeftText.setText(time);
    }

    @Override
    public void setAnswersProportions(String proportions) {
        answersProportionsText.setText(proportions);
    }

    @Override
    public void setCoinsForTimeNumber(int coins) {
        coinsForTimeBtn.setText(coins + "");
    }

    @Override
    public void setCoinsForCorrectAnswers(int coins) {
        coinsForCorrectAnswersBtn.setText(coins + "");
    }

    @Override
    public void setTotalCoinsBtn(int coins) {
        totalCoinsBtn.setText(coins + "");
    }

    @Override
    public void setCongratulationsText(String text) {
        congratulationsText.setText(text);
    }
}
