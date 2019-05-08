package pl.ziemiakopernika.ziemiakopernika.main;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public interface MainView {

    void showMuteIcon(boolean enabled);

    void setCuriosityText(String text);

    PulsatorLayout getPulsatorLayout();
}
