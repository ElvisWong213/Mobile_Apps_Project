package com.example.myapplication;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    private final IBinder mBinder = new LocalBinder();
    public static MediaPlayer player;

    public class LocalBinder extends Binder {
        BackgroundSoundService getService() {
            // Return this instance of LocalService so clients can call public methods
            return BackgroundSoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.chill);
        player.setLooping(true); // Set looping
        player.setVolume(0.25f,0.25f);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        DialogSetting.bgmsoundcontrol(getApplication());
        return START_STICKY;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {

    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }

    public static void stopbgm() {
        player.pause();
    }
    public static void playbgm(){
        player.start();
    }


}
