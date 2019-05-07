package pl.ziemiakopernika.ziemiakopernika.dao;

import android.provider.BaseColumns;

public final class QuestionsContract {

    private QuestionsContract(){

    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_database";
        public static final String TYPE_OF_QUESTION = "type_of_question";
        public static final String QUESTION = "question";
        public static final String ANSWER_ONE = "answer_one";
        public static final String ANSWER_TWO = "answer_two";
        public static final String ANSWER_THREE = "answer_three";
        public static final String ANSWER_FOUR = "answer_four";
        public static final String SHOWED_TIMES = "showed_times";
    }
}
