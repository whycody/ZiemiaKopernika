package pl.ziemiakopernika.ziemiakopernika.redinfo;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

public interface RedInfoAnimations {

    Animation getTranslateFromLeftAnimation();

    Animation getTranslateFromRightAnimation();

    Animation getTranslateFromDownAnimation();

    void startAnimation(View view, Bundle savedInstanceState);
}
