package com.example.pradhyumna.quiztrivia;

import android.provider.BaseColumns;

/**
 * Created by pradhyumna on 6/15/2018.
 */

public final class QuizContract {
    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }

}
