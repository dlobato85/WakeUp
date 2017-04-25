package com.dustin_domas_assignment.wakeup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    AlarmManager alar_manager;
    TimePicker alarm_timepicker;
    TextView update_textview;

    Context context;

    PendingIntent pendingIntent;

    int choose_sound;




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


                //put extra long value into intent
                //tells the cloack that you want certaint value from spinner

                intent.putExtra("sound_choose_pass", choose_sound);
                Log.i("Choose Sound ID MainA", String.valueOf(choose_sound));




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

                //must put extra long to alarm off to prevent crashes if null
                intent.putExtra("sound_choose_pass", choose_sound);


                //stop the ringtone
                //sendBroadcast will send signal to alarm receiver
                sendBroadcast(intent);



            }
        }); // end of stop_b






        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sound_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //onClickListener to register variable from spinner
        spinner.setOnItemSelectedListener(this);






    }//end of onCreate



    private void set_alarm_text(String s) {

        update_textview.setText(s);

    }




    //method for spinner class
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        //finding the id that user have selected
        choose_sound = (int) id;
        Toast.makeText(this, "This is id number"
                + id, Toast.LENGTH_SHORT).show();



    }

    //method for spinner class
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback


    }
}
