package pl.ziemiakopernika.ziemiakopernika.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import pl.ziemiakopernika.ziemiakopernika.RedInfoActivity;

public class MainPresenterImpl implements MainPresenter{

    private Activity activity;
    private MainActivityView activityView;
    private SharedPreferences sharedPreferences;
    private static String MUTE_ENABLED = "mute_enabled";

    MainPresenterImpl(Activity activity, MainActivityView activityView){
        this.activity = activity;
        this.activityView = activityView;
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public void onCreate() {
        activityView.showMuteIcon(getMuteBoolean());
    }

    @Override
    public void startGameClicked() {
        startGameActivity();
    }

    private void startGameActivity() {
        Intent intent = new Intent(activity, RedInfoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void muteClicked() {
        boolean muteEnabled = getMuteBoolean();
        activityView.showMuteIcon(!muteEnabled);
        setMute(!muteEnabled);
    }

    private boolean getMuteBoolean(){
        return sharedPreferences.getBoolean(MUTE_ENABLED, true);
    }

    private void setMute(boolean enabled){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MUTE_ENABLED, enabled);
        editor.apply();
    }

    @Override
    public void settingsClicked() {

    }

    @Override
    public void statisticsClicked() {

    }
}
