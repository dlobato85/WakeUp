package com.dustin_domas_assignment.wakeup;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;


public class RingtonePlayingService extends Service {

    MediaPlayer media_song;

    boolean isRunning;

    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }//end of onBinder




    //This method will activate alarm to play at selected time
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);


        /*
        Need to fetch values for MainActivity and AlarReceiver
        If statement to set if alarm is plaing or not
         */

        String state = intent.getExtras().getString("extra");

        Log.i ("EXTRAAAAAAAAAAAAA IS : " ,state);



        //fetch sound id  value from alarmReceiver
        Integer sound_pick = intent.getExtras().getInt("sound_chooce_pass");
        Log.i ("Sound RINGTONESERViCE" ,sound_pick.toString());




        //converts extra string from intent to 1 or 2
        assert state != null;
        if (state.equals("alarm ON")){
            startId = 1;
        }
        else if (state.equals("alarm OFF")){
            startId = 0;
        }
        else {
            startId= 0;
        }


        //Setting Notifications
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        //set the intent that will go to mainActivity after notification pop-up
        Intent intent_main = new Intent(this.getApplicationContext(), MainActivity.class);

        //must use pending Intent in order to pas value to notification builder
        PendingIntent pending_main = PendingIntent.getActivity(this, 0,
                intent_main, 0);


        Notification notification = new Notification.Builder(this)
                .setContentTitle("ALARM IS GOING OFF")
                .setContentText("Click me")
                .setContentIntent(pending_main)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .build();


        //notificationManager.notify(0, notification);




        //statements to call or cancel alarm sound

        //if no music and user press alarm ON
        //music should start playing
        if (! this.isRunning && startId == 1) {

            Log.i ("THERE IS NO MUSIC", "WANT TO START!!!!!!!!!!!!!!!!!");


            this.isRunning = true;
            this.startId = 0;


            //allow notification to display when alarm is on
            notificationManager.notify(0, notification);





            //play the song based on id from MainActivity Spinner

            if(sound_pick == 0 ) {
                // randomly picked audio file

                int minimum_n = 1;
                int max_n = 2;

                Random random = new Random();
                int random_n = random.nextInt(max_n + minimum_n );

                Log.i ("This is random song", String.valueOf(random_n));


                if(random_n == 1) {
                    media_song = MediaPlayer.create(this, R.raw.example_sound);
                    media_song.start();
                }
                else  if (random_n == 2) {
                    media_song = MediaPlayer.create(this, R.raw.tv_wave_example);
                    media_song.start();
                }

            }
            else if (sound_pick == 1){

                media_song = MediaPlayer.create(this, R.raw.example_sound);
                media_song.start();

            }

            else if (sound_pick == 2){

                media_song = MediaPlayer.create(this, R.raw.tv_wave_example);
                media_song.start();

            }


        }

        //if music is playing and the user press "alarm OFF"
        //stop playing
        else if (this.isRunning && startId == 0) {

            Log.i ("THERE IS  MUSIC", "WHANT TO END!!!!!!!!!!!!!!!!!");

            media_song.stop();
            media_song.reset();

            this.isRunning= false;
            this.startId = 0;



        }

        //if user presses random button while no music playing and press alar OFF
        //do nothing
        else if (! this.isRunning && startId == 0) {

            Log.i ("THERE IS NO MUSIC", "WHANT TO END!!!!!!!!!!!!!!!!!");

            media_song.stop();
            media_song.reset();


            this.isRunning = false;
            this.startId = 0;





        }

        //if user presses and user presssed alarm ON
        //do nothing
        else if (this.isRunning && startId == 1) {

            Log.i ("THERE IS MUSIC", "WHANT TO START!!!!!!!!!!!!!!!!!");

            this.isRunning = false;
            this.startId = 1;




        }

        //Do nothing if anyhting else random happens
        else   {

            Log.i ("ELSE", "Somehow you reached it!!!!!!!!!!!!!!!!!");

        }

/*
        media_song = MediaPlayer.create(this, R.raw.example_sound);
        media_song.start();
*/
        return START_NOT_STICKY;


    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, "on DEstroy CALLED", Toast.LENGTH_SHORT).show();

        Log.i ("ON Destroy Method", "WAS CALLED");

        super.onDestroy();
        this.isRunning = false;
    }







}//end og RingtonePlayingService
