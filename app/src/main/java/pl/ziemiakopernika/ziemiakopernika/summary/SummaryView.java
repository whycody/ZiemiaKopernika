package pl.ziemiakopernika.ziemiakopernika.summary;

import android.view.View;

public interface SummaryView {

    void setSummaryBackgroundColor(int color);

    void addViewToRoundLayout(View view);

    void setBadgeText(String badge);

    void setTimeLeftText(String time);

    void setAnswersProportions(String proportions);

    void setCoinsForTimeNumber(int coins);

    void setCoinsForCorrectAnswers(int coins);

    void setTotalCoinsBtn(int coins);

    void setCongratulationsText(String text);

}
