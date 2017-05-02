package com.dustin_domas_assignment.wakeup;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dustinlobato on 5/1/17.
 */

public class MainListActivity extends AppCompatActivity {


    private List<Alarm> alarms;

    private ImageButton addAlarm;
    private RecyclerView rView;
    private LinearLayoutManager linLayout;
    int icon_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycle_view);

        rView = (RecyclerView) findViewById(R.id.rcV);
        rView.setHasFixedSize(true);//Will not be changing size

        linLayout = new LinearLayoutManager(this);//linLayout for item positions

        rView.setLayoutManager(linLayout);
        addAlarm = (ImageButton) findViewById(R.id.addAlarm);
        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //icon_id = getDrawable(R.drawable.alarmclock);
                alarms = new ArrayList<Alarm>();
                alarms.add(new Alarm(R.id.alarmImage,"8:00","Everyday "));

                createAdapter();

            }


        });

       createAdapter();
    }

    private void createAdapter(){
        AlarmAdapter alarmAdapter = new AlarmAdapter(alarms);

        rView.setAdapter(alarmAdapter);
    }


}
