package com.dustin_domas_assignment.wakeup;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;


public class MainListActivity extends AppCompatActivity implements View.OnClickListener {

    Context mainContext;
   private List<AlarmCard> alarms;
    AlarmAdapter alarmAdapter;

    private RecyclerView rView;
    private LinearLayoutManager linLayout;

    private ImageButton addAlarm;
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;
    public ToggleButton toggles;
    int position1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycle_view);

        mainContext = getApplicationContext();

        rView = (RecyclerView) findViewById(R.id.rcV);

        rView.setHasFixedSize(true);
        //Will not be changing size

        linLayout = new LinearLayoutManager(this);//linLayout for item positions
        rView.setLayoutManager(linLayout);



        addAlarm = (ImageButton) findViewById(R.id.addAlarm);

        alarms = new ArrayList<>();

        position1 = 0;

/*
        toggles = (ToggleButton) findViewById(R.id.alarmToggle);

        onToggle(toggles);

*/
       if( alarms.size() == 0) {
           createDefaultAlarm();
       }

        //createAdapter();
        addAlarm.setOnClickListener(this);

       //createAdapter();
    }


    private void createAdapter(){
       alarmAdapter = new AlarmAdapter(mainContext, alarms);

       rView.setAdapter( alarmAdapter);
    }

    private void createDefaultAlarm(){
        alarms.add(new AlarmCard("8:00 ","AM "));
        Log.i("IN CREATE ALARM +++",""+alarms.size());
        createAdapter();
    }

    public void updateAdapter(){



    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent,SECOND_ACTIVITY_RESULT_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //position1++;
        if(requestCode==0){
            String time = data.getStringExtra("Time");

            int some = 0;

            try {
                some = Integer.parseInt(time.toString());
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            if( some > 12) {

                Log.i("INTENT EXTTTTRA ", "" + time);
                //alarms.add(new AlarmCard(time," Everyday "));
                alarmAdapter.add(new AlarmCard(time, " PM "), position1);
                alarmAdapter.notifyItemInserted(position1);
                rView.scrollToPosition(position1);
            }
            else {
                Log.i("INTENT EXTTTTRA ", "" + time);
                //alarms.add(new AlarmCard(time," Everyday "));
                alarmAdapter.add(new AlarmCard(time, " AM "), position1);
                alarmAdapter.notifyItemInserted(position1);
                rView.scrollToPosition(position1);
            }

        }

    }

    }
