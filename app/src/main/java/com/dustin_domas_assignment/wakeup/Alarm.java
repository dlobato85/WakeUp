package com.dustin_domas_assignment.wakeup;

import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;


public class Alarm extends AppCompatActivity {
    public int icon;
    public String time;
    public String title;
    public ToggleButton togglesAlarm;

    public Alarm(){

    }

    public Alarm(int ic, String tm, String title){
        this.icon = ic;
        this.time = tm;
        this.title = title;

    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    public ToggleButton getTogglesAlarm() {
        return togglesAlarm;
    }

    public void setTogglesAlarm(ToggleButton togglesAlarm) {
        this.togglesAlarm = togglesAlarm;
    }
}
