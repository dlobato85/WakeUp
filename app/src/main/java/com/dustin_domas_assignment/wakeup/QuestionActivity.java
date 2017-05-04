package com.dustin_domas_assignment.wakeup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuestionActivity extends AppCompatActivity {

    Question activeQuestion;
    TextView textView_Question;
    ProgressBar timeLeft;
    Button option_A, option_B, option_C, option_D;
    Context questContext;

    int questionID = 0;
    int numberOfQuestions;
    int numAnsweredRight;
    boolean answerFlag = false;

    List<Question> list_of_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        QuestionDatabase db = new QuestionDatabase(this);//QuestionDatabase Class
        list_of_questions = db.getAllQuestions();//Getting all questions from database

        questContext = getApplicationContext();

        textView_Question = (TextView) findViewById(R.id.textQuestion);

        option_A = (Button) findViewById(R.id.optionA_button);
        option_B = (Button) findViewById(R.id.optionB_button);
        option_C = (Button) findViewById(R.id.optionC_button);
        option_D = (Button) findViewById(R.id.optionD_button);




        //setting button onClickListeners
        option_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    checkAnswer(option_A.getText().toString());
            }
        });

        option_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option_B.getText().toString());
            }
        });

        option_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option_C.getText().toString());
            }
        });

        option_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option_D.getText().toString());
            }
        });

        setQuestion();
    }

    private void setQuestion(){

        //randomly picking a question and its options from the list
        Random rand = new Random();
        int questID = rand.nextInt(9)+1;
        if (list_of_questions != null && list_of_questions.size() != 0) {
            activeQuestion = list_of_questions.get(questID);
            Log.i("set List isnt null ", ""+activeQuestion.getANSWER());
        }
        else{
            Log.i("IN SETQUESTION is null ", ""+list_of_questions.size());


        }


        //setting the question to the UI
        textView_Question.setText(activeQuestion.getQUESTION());

        option_A.setText(activeQuestion.getOptionA());
        option_B.setText(activeQuestion.getOptionB());
        option_C.setText(activeQuestion.getOptionC());
        option_D.setText(activeQuestion.getOptionD());

    }

    public void checkAnswer(String userAnswer){

        if(activeQuestion.getANSWER().equals(userAnswer) ){
            //insert alarm eliminator alar_manager.cancel(pendingIntent);
            //put extra string into intent
            //to set OFFFFF button

            Intent broadintent = new Intent( questContext,Alarm_Receiver.class);
            broadintent.putExtra("extra", "alarm OFF");

            //must put extra long to alarm off to prevent crashes if null
          //  broadintent.putExtra("sound_choose_pass", choose_sound);

            //stop the ringtone
            //sendBroadcast will send signal to alarm receiver
            sendBroadcast(broadintent);

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        else{
            setQuestion();
        }
    }



}
