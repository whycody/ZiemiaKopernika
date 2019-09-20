package pl.ziemiakopernika.ziemiakopernika.finish.round;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.ziemiakopernika.ziemiakopernika.R;

public class FinishRoundActivity extends AppCompatActivity implements FinishRoundView {

    private TextView roundFinishText;
    private Button continueGameBtn, leaveGameBtn;
    private FinishRoundPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_round);
        roundFinishText = findViewById(R.id.roundFinishText);
        continueGameBtn = findViewById(R.id.continueGameBtn);
        leaveGameBtn = findViewById(R.id.leaveGameBtn);
        presenter = new FinishRoundPresenterImpl(this, this);
        continueGameBtn.setOnClickListener(continueGameBtnListener);
        leaveGameBtn.setOnClickListener(leaveGameBtnListener);
        presenter.onCreate();
    }

    private View.OnClickListener continueGameBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.continueGameBtnClicked();
        }
    };

    private View.OnClickListener leaveGameBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.leaveGameBtnClicked();
        }
    };

    @Override
    public void setRoundText(String round) {
        roundFinishText.setText(round);
    }
}
