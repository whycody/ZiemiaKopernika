package pl.ziemiakopernika.ziemiakopernika.timer;

public interface Timer {

    void startTimer();

    void stopTimer();

    void setFinishMethodIsCallable(boolean callable);

    int getSeconds();
}
