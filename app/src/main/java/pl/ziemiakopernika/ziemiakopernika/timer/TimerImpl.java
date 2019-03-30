package pl.ziemiakopernika.ziemiakopernika.timer;

import android.os.CountDownTimer;

public class TimerImpl implements Timer {

    private int time;
    private CountDownTimer countDownTimer;
    private TimerReact timerReact;

    public TimerImpl(int time, TimerReact timerReact){
        this.time = time;
        this.timerReact = timerReact;
    }

    @Override
    public void startTimer() {
        countDownTimer = new CountDownTimer(time, 50) {
            @Override
            public void onTick(long l) {
                timerReact.onTick(l);
            }

            @Override
            public void onFinish() {
                timerReact.onFinish();
            }
        }.start();
    }

    @Override
    public void stopTimer() {
        countDownTimer.cancel();
    }
}
