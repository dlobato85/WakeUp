package com.dustin_domas_assignment.wakeup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dustinlobato on 4/19/17.
 */

public class QuestionDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AlarmTrivia";
    private static final String TABLE = "Question table";
    private static final int DATABASE_SCHEMA = 1;
    private static final String ANSWER_KEY = "answer";
    private static final String QUESTION_KEY = "question";
    private static final String OPTION_B_KEY = "option B";
    private static final String OPTION_C_KEY = "option C";
    private static final String OPTION_D_KEY = "option D";
    private static final int KEY_ID = 0;
    private SQLiteDatabase database;

    private static final String SQL_STATEMENT = "SELECT * FROM " + TABLE;

    public QuestionDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        database = sqLiteDatabase;

        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + QUESTION_KEY + " TEXT, "
                + ANSWER_KEY + " TEXT, " + OPTION_B_KEY + " TEXT, "
                + OPTION_C_KEY + " TEXT, " + OPTION_D_KEY + " TEXT)";

        database.execSQL(CREATE_TABLE);
        HistoryBank();
        MathBank();
        CountriesAndCapitolsBank();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        database.execSQL("DROP TABLE IF EXISTS " + TABLE);

        onCreate(database);
    }

    public void addContentValues(Question question){

        ContentValues contValues = new ContentValues();

        contValues.put(QUESTION_KEY,question.getQUESTION());
        contValues.put(ANSWER_KEY,question.getANSWER());
        contValues.put(OPTION_B_KEY,question.getOptionB());
        contValues.put(OPTION_C_KEY,question.getOptionC());
        contValues.put(OPTION_D_KEY,question.getOptionD());

        database.insert(TABLE,null,contValues);
    }

    private void HistoryBank(){
        // Question historyQuest4 = new Question("","",);
        //this.addContentValues(historyQuest4);

        //http://www.triviaplaying.com/70_history_Q_.htm
        Question historyQuest1 = new Question(1,"What was the first country to reqognize Mexico's independence in 1836?","The U.S.","Russia","Venezuela","Brazil");
        this.addContentValues(historyQuest1);

        Question historyQuest2 = new Question(2,"What structure was 26.5 miles long until 1989?","The Berlin Wall","Great Wall of China","U.S.S.R. ","");
        this.addContentValues(historyQuest2);

        //http://www.triviacountry.com/154-History-trivia.htm
        Question historyQuest3 = new Question(3,"Which U.S. President was shot five days after the end of the American Civil War?","Abraham Lincoln","John F. Kennedy","","");
        this.addContentValues(historyQuest3);

        Question historyQuest4 = new Question(4,"What was the name of the Austrian-born dictator who succeeded Hindenburg as Germany's head of state?","Adolf Hitler","Goebells","","");
        this.addContentValues(historyQuest4);

        Question historyQuest5 = new Question(5,"Which country was ruled by the Romanov dynasty 1613-1917?","Russia","China","Rome","Mongolia");
        this.addContentValues(historyQuest5);

        //http://www.triviaplaying.com/199-%20trivia-questions-kids.htm
        Question historyQuest6 = new Question(6,"What was the first name of the first man in space?","Yuri","Neil","Tyler","John");
        this.addContentValues(historyQuest6);


    }

    private void MathBank(){

        Question mathQuest1 = new Question(100,"43+23+96 = ?", "162","158","152","172");
        this.addContentValues(mathQuest1);

        Question mathQuest2 = new Question(101, "191-49+82 = ?", "224","222","234","223");
        this.addContentValues(mathQuest2);

        Question mathQuest3 = new Question(102, "43+23+96 = ?", "162","158","152","172");
        this.addContentValues(mathQuest3);
    }

    private void CountriesAndCapitolsBank(){
        Question countryQuest1 = new Question(200,"What is the capital of Canada", "Ottawa","Montreal","Toronto","Quebec");
        this.addContentValues(countryQuest1);

    }

    public List<Question> getAllQuestions(){

        List<Question> questionArray = new ArrayList<Question>();

        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(SQL_STATEMENT, null);

        while (cursor.moveToNext()){
            Question temp = new Question();

            temp.setIDKEY(0);
            temp.setQUESTION(cursor.getString(1));
            temp.setANSWER(cursor.getString(2));
            temp.setOptionB(cursor.getString(3));
            temp.setOptionC(cursor.getString(4));
            temp.setOptionD(cursor.getString(5));

            questionArray.add(temp);

        }
        return questionArray;
    }


}
