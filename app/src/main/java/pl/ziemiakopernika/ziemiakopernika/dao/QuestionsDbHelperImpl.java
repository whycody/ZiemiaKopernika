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
    private static final int DATABASE_VERSION = 12;

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
//        addQuestion(new Question(0,"Który księżyc jako jedyny w Układzie Słonecznym posiada gęstą atmosferę?", "Tytan", "Księżyc", "Io", "Europa"));
//        addQuestion(new Question(0,"Jądro której galaktyki znajduje się najbliżej Układu Słonecznego?", "Karzeł Wielkiego Psa", "Karzeł Strzelca", "Wielki Obłok Magellana", "Droga Mleczna"));
//        addQuestion(new Question(0,"Jaką średnicę ma Mars?", "6794 km", "6894 km", "6994 km", "6694 km"));
//        addQuestion(new Question(0,"Fobos jest księżycem której planety?", "Marsa", "Uranu", "Saturna", "Jowisza"));
//        addQuestion(new Question(0,"Co było pierwszą udaną misją planetarną NASA?", "Zbliżenie się do Wenus", "Zbliżenie się do Słońca", "Zbliżenie się do Plutona", "Zbliżenie się do Marsa"));
//        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
//        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
//        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
//        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
//        addQuestion(new Question(1, "Co pierwsze byda?", "1", "2", "3", "4"));
        addQuestion(new Question(1, "Uporządkuj planety od najbliżej znajdującej się słońca", "Merkury", "Wenus", "Ziemia", "Mars"));
        addQuestion(new Question(1, "Uporządkuj planety od najbliżej znajdującej się słońca", "Merkury", "Jowisz", "Uran", "Neptun"));
        addQuestion(new Question(1, "Uporządkuj planety od najbliżej znajdującej się słońca", "Wenus", "Ziemia", "Jowisz", "Neptun"));
        addQuestion(new Question(1, "Uporządkuj planety od najbliżej znajdującej się słońca", "Mars", "Jowisz", "Saturn", "Uran"));
        addQuestion(new Question(0, "W skład Układu Słonecznego nie wchodzą", "Gwiazda Centauri", "Planetoidy", "Asteroidy", "Planety"));
        addQuestion(new Question(0, "Jednostka astronomiczna jest równa odległości", "Ziemi od Słońca", "Ziemi od Księżyca", "Księżyca od Słońca", "Najbliższej planety od Słońca"));
        addQuestion(new Question(0, "Ziemia wykonuje ruch", "Obiegowy i obrotowy", "Obrotowy", "Nie porusza się", "Obiegowy"));
        addQuestion(new Question(0, "Ruch obiegowy Ziemi trwa", "12 miesięcy", "12 godzin", "24 godziny", "Miesiąc"));
        addQuestion(new Question(0, "Ruch obrotowy Ziemi trwa", "24 godziny", "12 godzin", "12 miesięcy", "Miesiąc"));
        addQuestion(new Question(0, "Księżyc jest", "Naturalnym satelitą Ziemi", "Sztucznym satelitą Marsa", "Naturalnym satelitą Marsa", "Sztucznym satelitą Ziemi"));
        addQuestion(new Question(0, "Prawo naturalnego ciążenia dotyczy", "Wszystkich ciał materialnych", "Słońca i Ziemi", "Słońca i Księżyca", "Tylko obiektów astronomicznych"));
        addQuestion(new Question(0, "Prędkość planet wokół Slońca", "Rośnie i maleje na przemian", "Stale rośnie", "Stale maleje", "Jest stała"));
        addQuestion(new Question(0, "Siły powszechnego ciążenia zależą", "Tylko od masy i odległości", "Tylko od masy", "Tylko od odległości między ciałami", "Nie zależą od masy i odległości"));
        addQuestion(new Question(0, "Pełnia Księżyca zdarza się", "Co miesiąc", "Co tydzień", "Co kwartał", "Co rok"));
        addQuestion(new Question(0, "Na satelitę geostacjonarnego", "Działa siła wzdłuż promienia orbity", "Nie działa żadna siła", "Działa siła w kierunku ruchu", "Działa siła prostopadła do promienia orbity"));
        addQuestion(new Question(0, "Ile istnieje wokół Ziemi orbit stacjonarnych?", "Jedna", "Kilka", "Kilkanaście", "Nie ma takich orbit"));
        addQuestion(new Question(0, "Zaćmienie Księżyca zachodzi, gdy Księżyc jest", "W pełni", "W nowiu", "W pierwszym kwadrze", "W drugim kwadrze"));
        addQuestion(new Question(0, "Teoria heliocentryczna nie zakłada, że", "Ziemia jest nieruchoma", "Słońce jest nieruchome", "Planety krążą wokół Słońca", "Żadna z odpowiedzi nie jest poprawna"));
        addQuestion(new Question(0, "Ile gwiazd jest w Układzie Słonecznym?", "Jedna", "Nie ma żadnej", "Osiem", "Dziewięć"));
        addQuestion(new Question(0, "Ile planet jest w Układzie Słonecznym?", "Osiem", "Dziewięć", "Siedem", "Dziesięć"));
        addQuestion(new Question(0, "Jaka jest największa planeta Układu Słonecznego?", "Jowisz", "Słońce", "Saturn", "Uran"));
        addQuestion(new Question(0, "Jaka jest najmniejsza planeta Układu Słonecznego?", "Merkury", "Mars", "Neptun", "Uran"));
        addQuestion(new Question(0, "Która planeta w naszym układzie jest najgorętsza?", "Merkury", "Mars", "Wenus", "Ziemia"));
        addQuestion(new Question(0, "Która planeta w naszym układzie jest najzimniejsza?", "Uran", "Neptun", "Saturn", "Mars"));
        addQuestion(new Question(0, "Uważa się, że wewnątrz tej planety powstają diamenty. Jaka to planeta?", "Neptun", "Żadna z wymienionych", "Jowisz", "Saturn"));
        addQuestion(new Question(0, "Ile księżycy ma Uran?", "27", "24", "36", "29"));
        addQuestion(new Question(0, "Ile pierścieni ma Uran?", "13", "Żadnych", "14", "17"));
        addQuestion(new Question(0, "W którym roku odkryto planetę Uran?", "1781", "1652", "1651", "1740"));
        addQuestion(new Question(0, "W którym roku odkryto planetę Neptun?", "1846", "1862", "1894", "1901"));
        addQuestion(new Question(0, "Ile pierścieni ma Saturn?", "9", "12", "13", "10"));
        addQuestion(new Question(0, "Ile księżycy ma Jowisz?", "67", "69", "70", "63"));
        addQuestion(new Question(0, "Ile księżycy ma Saturn?", "62", "58", "67", "64"));
        addQuestion(new Question(0, "Ile księżycy ma Neptun?", "14", "18", "11", "17"));
        addQuestion(new Question(0, "Która planeta nie ma żadnego księżyca?", "Merkury", "Mars", "Neptun", "Uran"));
        addQuestion(new Question(0, "Która z planet nie jest kamienista?", "Jowisz", "Merkury", "Wenus", "Mars"));
        addQuestion(new Question(0, "W jakim czasie dociera światło ze Słońca do Ziemi?", "8 minut", "21 minut", "18 minut", "24 minut"));
        addQuestion(new Question(0, "Czym jest pluton?", "Planetą karłowatą", "Kwazarem", "Gwiazdą", "Satelitą naturalnym"));
        addQuestion(new Question(0, "Która z gwiazd znajduje się najbliżej Ziemi?", "Słońce", "Syriusz", "Altair", "Betelgeza"));
        addQuestion(new Question(0, "Parsek, złożenie pochodzące od słów paralaka i sekunda, to", "Jednostka odległości używana w astronomii", "Element kombinezonu kosmonauty", "Część statku kosmicznego", "Część statku kosmicznego"));
        addQuestion(new Question(0, "Rok świetlny to w astronomii jednostka", "Odległości", "Czasu", "Masy", "Siły"));
        addQuestion(new Question(0, "Kto był twórcą teorii heliocentrycznej?", "Mikołaj Kopernik", "Jan Heweliusz", "Galileusz", "Ptolemeusz"));
        addQuestion(new Question(0, "Z jakiego kalendarza korzystamy w Polsce?", "Z gregoriańskiego", "Z juliańskiego", "Ze zwrotnikowego", "Z równikowego"));
        addQuestion(new Question(0, "Kiedy możemy obserwować całkowite zaćmienie Słońca?", "Gdy Księżyc znajdzie się pomiędzy Słońcem a Ziemią", "Co roku w lecie", "Gdy Ziemia znajduje się między Słońcem a Księżycem", "Gdy wszystkie planety ułożą się w jednej linii"));
        addQuestion(new Question(0, "Od czego nazwano planety: Merkury, Wenus, Mars, Jowisz i Saturn?", "Od imion mitologicznych bogów", "Od imion wynalazców", "Od ich kolorów", "Od tego, z czego są zbudowane"));
        addQuestion(new Question(0, "Ile wynosi rekord prędkości wiatru?", "2100 km/h", "1900 km/h", "1700 km/h", "15000 km/h"));
        addQuestion(new Question(0, "Która z wymienionych planet zalicza się do planet olbrzymów?", "Uran", "Merkury", "Wenus", "Ziemia"));
        addQuestion(new Question(0, "Gdzie można znaleźć Wielką Czerwoną Plamę?", "Na Jowiszu", "Na Marsie", "Na Merkurym", "Na Saturnie"));
        addQuestion(new Question(0, "Gdzie można znaleźć Wielką Ciemną Plamę?", "Na Neptunie", "Na Uranie", "Na Saturnie", "Na Jowiszu"));
        addQuestion(new Question(0, "Jak powstała nazwa planeta?", "Od greckiego słowa oznaczającego wędrowca", "Od greckiego słowa oznaczającego kulę", "Od greckiego słowa oznaczającego skałę", "Od greckiego słowa oznaczającego podróżnika"));
        addQuestion(new Question(0, "Jak nazywa się najbliższa, oprócz Słońca, gwiazda Ziemi?", "Proxima Centauri", "Rigel", "Polaris", "Arktur"));
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
