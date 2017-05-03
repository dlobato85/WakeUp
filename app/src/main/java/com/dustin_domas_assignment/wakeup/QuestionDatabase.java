package com.dustin_domas_assignment.wakeup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class QuestionDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "question.sqlite";
    private static final String TABLE = "questions";
    private static final int DATABASE_SCHEMA = 1;
    private static final String ANSWER_KEY = "answer_key";
    private static final String KEY_ID = "question_id";
    private static final String QUESTION_KEY = "question_key";
    private static final String OPTION_A_KEY = "optionA_key";
    private static final String OPTION_B_KEY = "optionB_key";
    private static final String OPTION_C_KEY = "optionC_key";
    private static final String OPTION_D_KEY = "optionD_key";

    private SQLiteDatabase database;

    public enum questionColumns{
        question_id, question_key, answer_key,
        optionA_key, optionB_key, optionC_key,
        optionD_key
    }

    public QuestionDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        database = sqLiteDatabase;

        String sql = "CREATE TABLE  " + TABLE + " ( "
                + KEY_ID+ " INTEGER PRIMARY KEY , "
                + QUESTION_KEY + " TEXT, "
                + ANSWER_KEY + " TEXT, "
                + OPTION_A_KEY + " TEXT, "
                + OPTION_B_KEY + " TEXT, "
                + OPTION_C_KEY+ " TEXT, "
                + OPTION_D_KEY+ " TEXT)";

        database.execSQL(sql);
        HistoryBank();
        MathBank();
        CountriesAndCapitolsBank();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }



    private void HistoryBank(){
        // Question historyQuest4 = new Question("","",);
        //this.addContentValues(historyQuest4);

        //http://www.triviaplaying.com/70_history_Q_.htm
        Question historyQuest1 = new Question(0,"What was the first country to reqognize Mexico's independence in 1836? ","The U.S. ","Russia" ,"The U.S. ","Venezuela ","Brazil ");
        addContentValues(historyQuest1);

        Question historyQuest2 = new Question(1,"What structure was 26.5 miles long until 1989?","The Berlin Wall","Great Wall of China","U.S.S.R. ","The Berlin Wall","");
        addContentValues(historyQuest2);

        //http://www.triviacountry.com/154-History-trivia.htm
        Question historyQuest3 = new Question(2,"Which U.S. President was shot five days after the end of the American Civil War?","Abraham Lincoln","John F. Kennedy","Abraham Lincoln","","");
        addContentValues(historyQuest3);

        Question historyQuest4 = new Question(3,"What was the name of the Austrian-born dictator who succeeded Hindenburg as Germany's head of state?","Adolf Hitler","Goebells","","","Adolf Hitler");
        addContentValues(historyQuest4);

        Question historyQuest5 = new Question(4,"Which country was ruled by the Romanov dynasty 1613-1917?","Russia","China","Russia","Rome","Mongolia");
        addContentValues(historyQuest5);

        //http://www.triviaplaying.com/199-%20trivia-questions-kids.htm
        Question historyQuest6 = new Question(5,"What was the first name of the first man in space?","Yuri","Yuri","Neil","Tyler","John");
        addContentValues(historyQuest6);


    }

    private void MathBank(){

        Question mathQuest1 = new Question(6,"43+23+96 = ?", "162","158","162","152","172");
        addContentValues(mathQuest1);

        Question mathQuest2 = new Question(7, "191-49+82 = ?", "224","222","234","223", "224");
        addContentValues(mathQuest2);

        Question mathQuest3 = new Question(8,"43+23+96 = ?", "162","158","162","152","172");
        addContentValues(mathQuest3);
    }

    private void CountriesAndCapitolsBank(){
        Question countryQuest1 = new Question(9,"What is the capital of Canada", "Ottawa", "Ottawa","Montreal","Toronto","Quebec");
        addContentValues(countryQuest1);

    }

    public void addContentValues(Question question){
// question_id, question_key, answer_key,
        //optionA_key, optionB_key, optionC_key,
                //optionD_key
        ContentValues contValues = new ContentValues();
        contValues.put(KEY_ID,question.getIDKEY());
        contValues.put(QUESTION_KEY,question.getQUESTION());
        contValues.put(ANSWER_KEY,question.getANSWER());
        contValues.put(OPTION_A_KEY,question.getOptionA());
        contValues.put(OPTION_B_KEY,question.getOptionB());
        contValues.put(OPTION_C_KEY,question.getOptionC());
        contValues.put(OPTION_D_KEY,question.getOptionD());

        database.insert(TABLE,null,contValues);
    }

    public List<Question> getAllQuestions(){

        List<Question> questionArray = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE;
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery(select, null);

        if (cursor.moveToFirst()){
            do {

            Question temp = new Question();

            temp.setIDKEY(cursor.getInt(0));
            temp.setQUESTION(cursor.getString(1));
            temp.setANSWER(cursor.getString(2));
            temp.setOptionA(cursor.getString(3));
            temp.setOptionB(cursor.getString(4));
            temp.setOptionC(cursor.getString(5));
            temp.setOptionD(cursor.getString(6));

            questionArray.add(temp);
            }while (cursor.moveToNext());
        }
        return questionArray;
    }


}
