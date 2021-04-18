package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DialogSetting {
    public static Boolean effectsound;
    public static Boolean bgmsound;
    public static MediaPlayer mediaPlayer;
    public static SharedPreferences pref;
    public static int counter = 0;

    public static void DialogManager(Context page) {
        Dialog dialog;
        dialog = new Dialog(page);

        pref = page.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        dialog.setContentView(R.layout.setting_layout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView effectswitch = dialog.findViewById(R.id.effectswitch);
        ImageView bgmswitch = dialog.findViewById(R.id.bgmswitch);
        Button homepage = dialog.findViewById(R.id.homepage);
        mediaPlayer = MediaPlayer.create(page, R.raw.effect);
        if(pref.getBoolean("effectsound",true) == false)
            effectswitch.setImageResource(R.drawable.muteon);
        if(pref.getBoolean("bgmsound",true) == false)
            bgmswitch.setImageResource(R.drawable.muteon);


        effectswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (effectsound == true) {
                    effectsound = false;
                    editor.putBoolean("effectsound", false);
                    editor.commit();
                    effectswitch.setImageResource(R.drawable.muteon);

                }else if(effectsound == false) {
                    effectsound = true;
                    editor.putBoolean("effectsound", true);
                    editor.commit();
                    mediaPlayer.start();
                    effectswitch.setImageResource(R.drawable.muteoff);
                }
            }
        });

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pref.getBoolean("effectsound",true) == true)
                    mediaPlayer.start();
                Intent it = new Intent();
                it.setClass(page, MainActivity.class);
                page.startActivity(it);
                dialog.dismiss();
                ((Activity)page).finish();
            }
        });
        dialog.show();

        bgmswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bgmsound == true) {
                    bgmsound = false;
                    BackgroundSoundService.stopbgm();
                    editor.putBoolean("bgmsound", false);
                    editor.commit();
                    if(effectsound)
                        mediaPlayer.start();
                    bgmswitch.setImageResource(R.drawable.muteon);

                }else if(bgmsound == false) {
                    bgmsound = true;
                    BackgroundSoundService.playbgm();
                    editor.putBoolean("bgmsound", true);
                    editor.commit();
                    if(effectsound)
                        mediaPlayer.start();
                    bgmswitch.setImageResource(R.drawable.music);
                }
            }
        });
    }

    public static Boolean effectsoundcontrol(Context page){
        pref = page.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        effectsound = pref.getBoolean("effectsound",true);
        if (effectsound == false){
            return false;
        }
        return true;
    }

    public static void bgmsoundcontrol(Context page){
        pref = page.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        bgmsound = pref.getBoolean("bgmsound",true);
        if (bgmsound == false){
            BackgroundSoundService.stopbgm();
        }else if (bgmsound == true) {
            BackgroundSoundService.playbgm();
        }
    }


    public static void checkpause(){
        if(counter==0){
            BackgroundSoundService.stopbgm();
        }
    }


}

