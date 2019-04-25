package pl.ziemiakopernika.ziemiakopernika.summary;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class SummaryActivity extends AppCompatActivity implements SummaryView{

    private View summaryBackground;
    private LinearLayout roundLayout;
    private TextView badgeText, timeLeftText, answersProportionsText, congratulationsText;
    private Button coinsForTimeBtn, coinsForCorrectAnswersBtn, totalCoinsBtn, shareBtn,
            playAgainBtn, statistitcsBtn;
    private LinearLayout buttonsLinear;
    private RecyclerView recyclerView;
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
        shareBtn = findViewById(R.id.share_btn);
        playAgainBtn = findViewById(R.id.play_again_btn);
        statistitcsBtn = findViewById(R.id.statistics_btn);
        buttonsLinear = findViewById(R.id.buttons_linear);
        recyclerView = findViewById(R.id.answers_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shareBtn.setOnClickListener(onShareBtnClicked);
        playAgainBtn.setOnClickListener(onPlayAgainBtnClicked);
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
    public void setCoinBtnsDrawable(Drawable drawable) {
        coinsForTimeBtn.setBackground(drawable);
        coinsForCorrectAnswersBtn.setBackground(drawable);
        totalCoinsBtn.setBackground(drawable);
    }

    @Override
    public void setStatisticsBtnColor(int color) {
        statistitcsBtn.setBackgroundColor(color);
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

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener onShareBtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            summaryPresenter.onShareBtnClicked();
        }
    };

    private View.OnClickListener onPlayAgainBtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            summaryPresenter.onPlayAgainBtnClicked(buttonsLinear);
        }
    };
}
