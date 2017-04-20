package com.dustin_domas_assignment.wakeup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dustinlobato on 4/19/17.
 */

public class QuestionDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AlarmTrivia";
    private static final String TABLE = "question_table";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;

    public QuestionDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
