package pl.ziemiakopernika.ziemiakopernika.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsContract.*;
import pl.ziemiakopernika.ziemiakopernika.model.Question;

public class QuestionsDbHelperImpl extends SQLiteOpenHelper implements QuestionsDbHelper {

    private static final String DATABASE_NAME = "ZiemiaKopernikaDatabse.db";
    private static final int DATABASE_VERSION = 9;

    private SQLiteDatabase database;

    public QuestionsDbHelperImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.database = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.TYPE_OF_QUESTION + " INTEGER, " +
                QuestionsTable.QUESTION + " TEXT, " +
                QuestionsTable.ANSWER_ONE + " TEXT, " +
                QuestionsTable.ANSWER_TWO + " TEXT, " +
                QuestionsTable.ANSWER_THREE + " TEXT, " +
                QuestionsTable.ANSWER_FOUR + " TEXT" + ")";
        database.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable(){
        addQuestion(new Question(0,"Który księżyc jako jedyny w Układzie Słonecznym posiada gęstą atmosferę?", "Tytan", "Księżyc", "Io", "Europa"));
        addQuestion(new Question(0,"Jądro której galaktyki znajduje się najbliżej Układu Słonecznego?", "Karzeł Wielkiego Psa", "Karzeł Strzelca", "Wielki Obłok Magellana", "Droga Mleczna"));
        addQuestion(new Question(0,"Jaką średnicę ma Mars?", "6794 km", "6894 km", "6994 km", "6694 km"));
        addQuestion(new Question(0,"Fobos jest księżycem której planety?", "Marsa", "Uranu", "Saturna", "Jowisza"));
        addQuestion(new Question(0,"Co było pierwszą udaną misją planetarną NASA?", "Zbliżenie się do Wenus", "Zbliżenie się do Słońca", "Zbliżenie się do Plutona", "Zbliżenie się do Marsa"));
        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
    }

    private void addQuestion(Question question){
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsTable.TYPE_OF_QUESTION, question.getTypeOfQuestion());
        contentValues.put(QuestionsTable.QUESTION, question.getQuestion());
        contentValues.put(QuestionsTable.ANSWER_ONE, question.getAnswerOne());
        contentValues.put(QuestionsTable.ANSWER_TWO, question.getAnswerTwo());
        contentValues.put(QuestionsTable.ANSWER_THREE, question.getAnswerThree());
        contentValues.put(QuestionsTable.ANSWER_FOUR, question.getAnswerFour());
        database.insert(QuestionsTable.TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.database = sqLiteDatabase;
        database.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(database);
    }

    @Override
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionsList = new ArrayList<>();
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setTypeOfQuestion(cursor.getInt(cursor.getColumnIndex(QuestionsTable.TYPE_OF_QUESTION)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.QUESTION)));
                question.setAnswerOne(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_ONE)));
                question.setAnswerTwo(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_TWO)));
                question.setAnswerThree(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_THREE)));
                question.setAnswerFour(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_FOUR)));
                questionsList.add(question);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }


}
