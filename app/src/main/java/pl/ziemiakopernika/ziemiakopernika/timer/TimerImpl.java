package pl.ziemiakopernika.ziemiakopernika.timer;

import android.os.CountDownTimer;

public class TimerImpl implements Timer {

    private int time, currentTimeInSeconds;
    private boolean finishMethodIsCallable = true;
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
                currentTimeInSeconds = (int)l/1000;
            }

            @Override
            public void onFinish() {
                if(finishMethodIsCallable)
                    timerReact.onFinish();
            }
        }.start();
    }

    @Override
    public void stopTimer() {
        countDownTimer.cancel();
    }

    @Override
    public int getSeconds() {
        return currentTimeInSeconds;
    }

    @Override
    public void setFinishMethodIsCallable(boolean callable) {
        this.finishMethodIsCallable = callable;
    }
}
