package pl.ziemiakopernika.ziemiakopernika.statistics;

public interface StatisticsDao {

    void saveGamesPlayedStatistics();

    void saveTimeLeftStatistics(int timeLeft);

    void saveCorrectAnswersNumberStatistics(int correctAnswers);

    void saveUncorrectAnswersNumberStatistics(int uncorrectAnswers);

    void savePercentageCorrectnessStatistics(int percentageCorrectness);

    void saveEarnedCoinsStatistics(int earnedCoins);

    int getGamesPlayedStatistics();

    int getQuestionsAnsweredStatistics();

    int getTimeLeftTotalStatistics();

    double getTimeLeftPerQuestionStatistics();

    int getCorrectAnswersNumberStatistics();

    int getUncorrectAnswersNumberStatistics();

    double getPercentageCorrectnessAverageStatistics();

    int getEarnedCoinsStatistics();

    int getCoinsStatistics();

    int getSpentCoinsStatistics();

    int getPercentageOfCoinsHaveStatistics();

}
