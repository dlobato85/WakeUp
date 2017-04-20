package com.dustin_domas_assignment.wakeup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    AlarmManager alar_manager;
    TimePicker alarm_timepicker;
    TextView update_textview;

    Context context;

    PendingIntent pendingIntent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.context = this;


        //initialize timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        //initialize alarm manager
        alar_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //initialize textview for alarm updates
        update_textview = (TextView) findViewById(R.id.update_textview);

        //Create and isntance of calender
        final Calendar calendar = Calendar.getInstance();


        //Create  an intent to the Alarm_Receiveer
        final Intent intent = new Intent(this.context, Alarm_Receiver.class);









        //create onClickListeners for buttons

        Button start_b = (Button) findViewById(R.id.start_button);
        start_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //setting calender to the hour and minute
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());


                // get the int of time
                int h = alarm_timepicker.getHour();
                int m = alarm_timepicker.getMinute();



                //converting to String

                String hour = String.valueOf(h);
                String minute = String.valueOf(m);



                //set pm
                if (h > 12) {
                    hour = String.valueOf(h - 12);
                }//end of if

                if (m < 10) {
                    minute = "0" + String.valueOf(minute);
                }//end of


                //will update textview
                set_alarm_text("Alarm was set to " +hour + " : " +minute );


                //put in extra string into intent
                //which will tell that you press START ALARm
                //must be before send to pendingIntent
                intent.putExtra("extra", "alarm ON");



            //Create pending intent
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
                      intent, PendingIntent.FLAG_UPDATE_CURRENT );


                //set the alarm manager
                alar_manager.set(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(), pendingIntent);


            }
        });// end of start_b Listener


        Button stop_b = (Button) findViewById(R.id.stop_button);
        stop_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //will update textview
                set_alarm_text("AAAALARM IS OFFF!!!!");


                //will cancel the alarm
                alar_manager.cancel(pendingIntent);


                //put extra string into intent
                //to set OFFFFF button
                intent.putExtra("extra", "alarm OFF");


                //stop the ringtone
                //sendBroadcast will send signal to alarm receiver
                sendBroadcast(intent);



            }
        }); // end of stop_b






    }//end of onCreate

    private void set_alarm_text(String s) {

        update_textview.setText(s);

    }
}
