package pl.ziemiakopernika.ziemiakopernika.summary;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface SummaryView {

    void setSummaryBackgroundColor(int color);

    void addViewToRoundLayout(View view);

    void setBadgeText(String badge);

    void setCoinBtnsDrawable(Drawable drawable);

    void setStatisticsBtnColor(int color);

    void setTimeLeftText(String time);

    void setAnswersProportions(String proportions);

    void setCoinsForTimeNumber(int coins);

    void setCoinsForCorrectAnswers(int coins);

    void setTotalCoinsBtn(int coins);

    void setCongratulationsText(String text);

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

}
