package pl.ziemiakopernika.ziemiakopernika.main;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public interface MainActivityView {

    void showMuteIcon(boolean enabled);

    PulsatorLayout getPulsatorLayout();
}
