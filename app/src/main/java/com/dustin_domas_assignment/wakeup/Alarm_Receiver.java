package com.dustin_domas_assignment.wakeup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public class Alarm_Receiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {


        Log.i("Currently in !!!!", "ALARM_RECEIVER");

        //extra string must be fetched from mainactivity just for alarm ON

        String get_string = intent.getExtras().getString("extra");

        //shot the extra name
        Log.i ("THIS IS THE KEY", get_string);

        //fetch long from intent
        //which will tell us what value was selected from spinner
        //also tells if value selected Alarm ON or Alarm OFF
        Integer get_sound = intent.getExtras().getInt("sound_choose_pass");

        Log.i ("THIS SOUNDS ID RECEIVER", get_sound.toString());


        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        //pass the extra string from MainActivity to RingtonePlayi....

       service_intent.putExtra("extra",get_string);

        //pass sound id to RingtonePlayingService
        service_intent.putExtra("sound_chooce_pass", get_sound);

       context.startService(service_intent);

        Intent quesIntent = new Intent(context, QuestionActivity.class);
        quesIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(quesIntent);



    }// end of onReceiver
}// end of Alarm_Receiver
