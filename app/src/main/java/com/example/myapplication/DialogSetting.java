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
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DialogSetting{
    public static Boolean effectsound;
    public static Boolean bgmsound;
    public static MediaPlayer mediaPlayer;
    public static SharedPreferences pref;
    public static int counter = 0;
    public static Dialog dialog;

    public static void DialogManager(Context page) {
        dialog = new Dialog(page);

        pref = page.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        dialog.setContentView(R.layout.setting_layout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView effectswitch = dialog.findViewById(R.id.effectswitch);
        ImageView bgmswitch = dialog.findViewById(R.id.bgmswitch);
        ImageView effectswitchimage = dialog.findViewById(R.id.effectswitchimage);
        ImageView bgmswitchimage = dialog.findViewById(R.id.bgmswitchimage);
        ImageView homeswitch = dialog.findViewById(R.id.homeswitch);
        ImageView closebutton = dialog.findViewById(R.id.closebutton);
        LinearLayout CardLayout = dialog.findViewById(R.id.CardLayout);
        CardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        mediaPlayer = MediaPlayer.create(page, R.raw.effect);
        if(pref.getBoolean("effectsound",true) == false)
            effectswitchimage.setImageResource(R.drawable.muteon);
        if(pref.getBoolean("bgmsound",true) == false)
            bgmswitchimage.setImageResource(R.drawable.musicoff);

        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(effectsound)
                    mediaPlayer.start();
                dialog.dismiss();
            }
        });

        effectswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (effectsound == true) {
                    effectsound = false;
                    editor.putBoolean("effectsound", false);
                    editor.commit();
                    effectswitchimage.setImageResource(R.drawable.muteon);

                }else if(effectsound == false) {
                    effectsound = true;
                    editor.putBoolean("effectsound", true);
                    editor.commit();
                    mediaPlayer.start();
                    effectswitchimage.setImageResource(R.drawable.muteoff);
                }
            }
        });

        homeswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pref.getBoolean("effectsound",true) == true)
                    mediaPlayer.start();
                    Intent it = new Intent();
                if(page.getClass()!=MainActivity.class) {
                    it.setClass(page, MainActivity.class);
                    page.startActivity(it);
                    ((Activity) page).finish();
                }
                    dialog.dismiss();
            }

        });
        dialog.show();

        bgmswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bgmsound == true) {
                    bgmsound = false;
                    if(effectsound)
                        mediaPlayer.start();
                    BackgroundSoundService.stopbgm();
                    editor.putBoolean("bgmsound", false);
                    editor.commit();
                    bgmswitchimage.setImageResource(R.drawable.musicoff);

                }else if(bgmsound == false) {
                    bgmsound = true;
                    if(effectsound)
                        mediaPlayer.start();
                    BackgroundSoundService.playbgm();
                    editor.putBoolean("bgmsound", true);
                    editor.commit();
                    bgmswitchimage.setImageResource(R.drawable.music);
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

