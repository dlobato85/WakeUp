package com.dustin_domas_assignment.wakeup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Domo
 */

public class Alarm_Receiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {


        Log.i("Currently in !!!!", "ALARM_RECEIVER");

        //extra string must be fetched from mainactivity just for alarm ON

        String get_string = intent.getExtras().getString("extra");

        //shot the extra name
        Log.i ("THIS IS THE KEY", get_string);



        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        //pass the extra string from MainActivity to RingtonePlayi....

        service_intent.putExtra("extra",get_string);


        context.startService(service_intent);



    }// end of onReceiver
}// end of Alarm_Receiver
