package com.dustin_domas_assignment.wakeup;

import android.support.v7.app.AppCompatActivity;


public class Question extends AppCompatActivity {

    private String QUESTION;
    private String ANSWER;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int IDKEY;

    public Question(){
        IDKEY = 0;
        QUESTION = "";
        ANSWER = "";
        optionA = "";
        optionB = "";
        optionC = "";
        optionD = "";

    }

    public Question(int x, String question, String answer, String opA, String opB, String opC, String opD){

        IDKEY = x;
        QUESTION = question;
        ANSWER = answer;
        optionA = opA;
        optionB = opB;
        optionC = opC;
        optionD = opD;

    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String Question) {
        QUESTION = Question;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String Answer) {
        ANSWER = Answer;
    }

    public String getOptionA(){return optionA;};

    public void setOptionA(String optA){optionA = optA;};

    public String getOptionB(){return optionB;};

    public void setOptionB(String optB){optionB = optB;};

    public String getOptionC(){return optionC;};

    public void setOptionC(String optC){optionC = optC;};

    public String getOptionD(){return optionD;};

    public void setOptionD(String optD){optionD = optD;};

    public int getIDKEY() {
        return IDKEY;
    }

    public void setIDKEY(int Idkey) {
        IDKEY = Idkey;
    }
}
