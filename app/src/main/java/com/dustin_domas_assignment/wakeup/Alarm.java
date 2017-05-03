package com.dustin_domas_assignment.wakeup;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;


public class Alarm extends AppCompatActivity {

    public int alaramID;
    public int numberOfQuestions;
    public Calendar time;
    public String title;
    public ToggleButton togglesAlarm;

    public Alarm(){
        alaramID = 0;
        numberOfQuestions = 0;
        title = "Alarm";

    }

    public Alarm(int ic, int num, Calendar tm, String title){

        this.alaramID = ic;
        this.numberOfQuestions = num;
        this.time = tm;
        this.title = title;
    }


    public int getAlaramID() {
        return alaramID;
    }

    public void setAlaramID(int icon) {
        this.alaramID = icon;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }


    public String get_title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ToggleButton getTogglesAlarm() {
        return togglesAlarm;
    }

    public void setTogglesAlarm(ToggleButton togglesAlarm) {
        this.togglesAlarm = togglesAlarm;
    }
}
