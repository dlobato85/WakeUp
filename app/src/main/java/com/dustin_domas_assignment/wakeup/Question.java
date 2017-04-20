package com.dustin_domas_assignment.wakeup;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by dustinlobato on 4/19/17.
 */

public class Question extends AppCompatActivity {

    private String QUESTION;
    private String ANSWER;
    private int IDKEY;

    public Question(){
        QUESTION = "";
        ANSWER = "";
        IDKEY = 0;
    }

    public Question(String question, String answer, int idkey){
        QUESTION = question;
        ANSWER = answer;
        IDKEY = idkey;


    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String Question) {
        this.QUESTION = Question;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String Answer) {
        this.ANSWER = Answer;
    }

    public int getIDKEY() {
        return IDKEY;
    }

    public void setIDKEY(int Idkey) {
        this.IDKEY = Idkey;
    }
}
