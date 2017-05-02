package com.dustin_domas_assignment.wakeup;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by dustinlobato on 4/19/17.
 */

public class Question extends AppCompatActivity {

    private String QUESTION;
    private String ANSWER;
    private String optionB;
    private String optionC;
    private String optionD;
    private int IDKEY;

    public Question(){
        QUESTION = "";
        ANSWER = "";
        optionB = "";
        optionC = "";
        optionD = "";
        IDKEY = 0;
    }

    public Question(  int idkey,String question, String answer, String opB, String opC, String opD){
        QUESTION = question;
        ANSWER = answer;
        optionB = opB;
        optionC = opC;
        optionD = opD;
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

    public String getOptionB(){return optionB;};

    public void setOptionB(String optB){this.optionB = optB;};

    public String getOptionC(){return optionC;};

    public void setOptionC(String optC){this.optionC = optC;};

    public String getOptionD(){return optionD;};

    public void setOptionD(String optD){this.optionD = optD;};

    public int getIDKEY() {
        return IDKEY;
    }

    public void setIDKEY(int Idkey) {
        this.IDKEY = Idkey;
    }
}
