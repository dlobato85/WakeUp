package com.dustin_domas_assignment.wakeup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;
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
    public void onCreate(SQLiteDatabase sqLiteDatabase1) {
        database = sqLiteDatabase1;




      String sql = "CREATE TABLE  " + TABLE + " ( "
                + KEY_ID+ " INTEGER PRIMARY KEY , "
                + QUESTION_KEY + " TEXT, "
                + ANSWER_KEY + " TEXT, "
                + OPTION_A_KEY + " TEXT, "
                + OPTION_B_KEY + " TEXT, "
                + OPTION_C_KEY+ " TEXT, "
                + OPTION_D_KEY+ " TEXT)";


        database.execSQL(sql);
        //HistoryBank();
       // MathBank();
       // CountriesAndCapitolsBank();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase1, int i, int i1) {

        String sql ="DROP TABLE IF EXISTS " + TABLE;

        sqLiteDatabase1.execSQL(sql);
        onCreate(sqLiteDatabase1);


    }



    private void HistoryBank(){



        Question historyQuest1 = new Question(0,"What was the first country to recognize Mexico's independence in 1836? ","The U.S. ","Russia" ,"The U.S. ","Venezuela ","Brazil ");
        addContentValues(historyQuest1);

        Question historyQuest2 = new Question(1,"What structure was 26.5 miles long until 1989?","The Berlin Wall","Great Wall of China","U.S.S.R.","The Berlin Wall","Eiffel Tower");
        addContentValues(historyQuest2);


        Question historyQuest3 = new Question(2,"Which U.S. President was shot five days after the end of the American Civil War?","Abraham Lincoln","John F. Kennedy","Abraham Lincoln","George W. Bush","Bill Clinton");
        addContentValues(historyQuest3);

        Question historyQuest4 = new Question(3,"What was the name of the Austrian-born dictator who succeeded Hindenburg as Germany's head of state?","Adolf Hitler","Goebells","Obama","James","Adolf Hitler");
        addContentValues(historyQuest4);

        Question historyQuest5 = new Question(4,"Which country was ruled by the Romanov dynasty 1613-1917?","Russia","China","Russia","Rome","Mongolia");
        addContentValues(historyQuest5);

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

        Question mathQuest4 = new Question(9,"2*(170/2) = ?", "170","122","174","170","98");
        addContentValues(mathQuest4);

        Question mathQuest5 = new Question(10,"(300*2)-55 = ?", "545","545","655","405","755");
        addContentValues(mathQuest5);

        Question mathQuest6 = new Question(11,"2+4-5-7+10-1 = ?", "3","-5","6","5","3");
        addContentValues(mathQuest6);
    }

    private void CountriesAndCapitolsBank(){

        Question countryQuest1 = new Question(12,"What is the capital of Canada", "Ottawa", "Ottawa","Montreal","Toronto","Quebec");
        addContentValues(countryQuest1);

        Question countryQuest2 = new Question(13,"What is the largest state in the United States", "Alaska", "Texas","California","Alaska","Florida");
        addContentValues(countryQuest2);

       Question countryQuest3 = new Question(14,"What is the the capital of Austria", "Vienna", "Sidney","Vienna","Madrid","Vatican City");
       addContentValues(countryQuest3);

        Question countryQuest4 = new Question(15,"Which one is the longest river in the world", "Amazon River", "Nile River","Amazon River","Congo River","Lena River");
        addContentValues(countryQuest4);

        Question countryQuest5 = new Question(16,"Long Island is a part of which US state?", "New York", "New York","New Hampshire","Nebraska","Columbia");
        addContentValues(countryQuest5);

        Question countryQuest6 = new Question(17,"What is the tallest building in New York?", "One World Trade Center", "Rockefeller Center","Empire State Building","Statue of Liberty","One World Trade Center");
        addContentValues(countryQuest6);

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
