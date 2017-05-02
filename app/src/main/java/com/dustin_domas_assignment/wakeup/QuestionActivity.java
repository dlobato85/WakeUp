package com.dustin_domas_assignment.wakeup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dustinlobato on 4/19/17.
 */

public class QuestionActivity extends AppCompatActivity {

    Question activeQuestion;
    TextView textView_Question;
    ProgressBar timeLeft;
    Button option_A, option_B, option_C, option_D;
    Button submit;
    int questionID = 0;
    List<Question> list_of_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        QuestionDatabase db = new QuestionDatabase(this);//QuestionDatabase Class
        list_of_questions = db.getAllQuestions();//Getting all questions from database

        //activeQuestion = list_of_questions.get();

        textView_Question = (TextView) findViewById(R.id.textQuestion);

        option_A = (Button) findViewById(R.id.optionA_button);
        option_B = (Button) findViewById(R.id.optionB_button);
        option_C = (Button) findViewById(R.id.optionC_button);
        option_D = (Button) findViewById(R.id.optionD_button);

        timeLeft = (ProgressBar) findViewById(R.id.timeBar);

    }

    private void setQuestion(){
        textView_Question.setText(activeQuestion.getQUESTION());
    }

}
