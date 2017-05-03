package com.dustin_domas_assignment.wakeup;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static java.util.Calendar.AM_PM;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    AlarmManager alar_manager;
    TimePicker alarm_timepicker;
    TextView update_textview;

    Context context;

    PendingIntent pendingIntent;

    int choose_sound;


    Spinner spinner;

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


        //creating switch button

        Switch mySwitch = null;

        mySwitch = (Switch) findViewById(R.id.switch_button);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected


                    //setting calender to the hour and minute
                     calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                     calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                    //calendar.set(alarm_timepicker.set(AM_PM, Calendar.PM), AM_PM);

                    // get the int of time
                     int h = alarm_timepicker.getHour();
                    int m = alarm_timepicker.getMinute();

                    //  int cc = alarm_timepicker.get()

                    //converting to String
                       String hour = String.valueOf(h);
                        String minute = String.valueOf(m);

                //set pm
                if (h > 12) {
                    hour = String.valueOf(h - 12);
                   // calendar.set(Calendar.AM_PM, Calendar.PM);
                }//end of if

                //set formation for minutes
                if (m < 10) {
                    minute = "0" + String.valueOf(minute);
                }//end of

               /*if(spinner.equals("PM"))
                {
                    calendar.set(Calendar.AM_PM, Calendar.PM);
                }
                else {
                    calendar.set(Calendar.AM_PM, Calendar.AM);
                }
                */

                //will update textview
                set_alarm_text("Alarm was set to " +hour + " : " +minute + "  ");

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

                } //end IF statement

                else {
                    //do something when unchecked

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
            }
            }
        );//end of setOnCheckedChangeListener

/*
        //create onClickListeners for buttons
        Button start_b = (Button) findViewById(R.id.start_button);
        start_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ALARM AM_PM DOES NOT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                /*Calendar now = Calendar.getInstance().getInstance();
                
               // int a = now.get(Calendar.AM_PM);
               // String then = String.valueOf(now);

               // String result = "!!";

              //  calendar.get(Calendar.AM_PM);


               //void bo = calendar.set(Calendar.AM_PM,Calendar.AM);


               // String result = null;
                //if(calendar.get(Calendar.AM_PM) == Calendar.AM)
                {

                    result = "AM";
                }

                else if(AM_PM == Calendar.PM){

                    result = "PM";
                }

                */
                
                //setting calender to the hour and minute
               // calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
               // calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                //calendar.set(alarm_timepicker.set(AM_PM, Calendar.PM), AM_PM);



                // get the int of time
               // int h = alarm_timepicker.getHour();
                //int m = alarm_timepicker.getMinute();

              //  int cc = alarm_timepicker.get()


                //converting to String
             //   String hour = String.valueOf(h);
               // String minute = String.valueOf(m);

/*

                //set pm
                if (h > 12) {
                    hour = String.valueOf(h - 12);
                   // calendar.set(Calendar.AM_PM, Calendar.PM);
                }//end of if

                else {
                 //   calendar.set(Calendar.AM_PM, Calendar.AM);
                }

                //set formation for minutes
                if (m < 10) {
                    minute = "0" + String.valueOf(minute);
                }//end of

               /*if(spinner.equals("PM"))
                {
                    calendar.set(Calendar.AM_PM, Calendar.PM);
                }
                else {
                    calendar.set(Calendar.AM_PM, Calendar.AM);
                }


                //will update textview
                set_alarm_text("Alarm was set to " +hour + " : " +minute + "  ");


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

        */


/**********************************
 *
 *
 * the spinner
 */


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



    //Create action bar and assign menu layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //Set up buttons for action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.about){

            ((TextView)
                    new AlertDialog.Builder(MainActivity.this,R.style.DialogBox)
                            .setTitle("Developers Of WakeUp ")
                            .setMessage(Html.fromHtml("<h2>Dustin Lobato</h2>"
                                    + "<h2>Domas Budrys</h2>"
                                    +"<br>"
                                    + "<h3>Icons:</h3>"
                                    + "<font color='#FF7F27'><a href=\"https://icons8.com/android-icons/\">icons8.com</a> </font>"
                                    + "<h3>Code Credit:</h3>"
                                    + "<font color='#FF7F27'><a href=\"https://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-touch-interaction--mobile-19202\">Tutplus Tutorial</a> </font>"
                                    +"<br>"
                                    + "<font color='#FF7F27'><a href=\"http://stackoverflow.com/questions/10095335/android-link-in-dialog\">Stackoverflow</a> </font>"
                            ))
                            .show()

                            .findViewById(android.R.id.message))
                    .setMovementMethod(LinkMovementMethod.getInstance());


        }// end of if
        else if (item.getItemId() == R.id.exit){

            finish();
            System.exit(0);
        }// end of else if

        return(super.onOptionsItemSelected(item));
    }// end of onOptionsItemSelected






}
