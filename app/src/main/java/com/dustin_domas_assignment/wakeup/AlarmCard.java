package com.dustin_domas_assignment.wakeup;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;


public class AlarmCard extends AppCompatActivity {


    public String time;
    public String title;
    //public ToggleButton togglesAlarm;

    public AlarmCard(){

        //title = "Alarm";
    }

    public AlarmCard( String tm, String title){

        this.time = tm;
        this.title = title;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String get_title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
/*
    public ToggleButton getTogglesAlarm() {
        return togglesAlarm;
    }

    public void setTogglesAlarm(ToggleButton togglesAlarm) {
        this.togglesAlarm = togglesAlarm;
    }
    */
}
