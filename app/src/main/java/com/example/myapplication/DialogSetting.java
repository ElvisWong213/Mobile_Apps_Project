package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        ImageView homeswitchimage = dialog.findViewById(R.id.homeswitchimage);
        ImageView homeswitch = dialog.findViewById(R.id.homeswitch);
        ImageView closebutton = dialog.findViewById(R.id.closebutton);
        LinearLayout CardLayout = dialog.findViewById(R.id.CardLayout);
        dialog.show();
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





        effectswitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    effectswitch.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    effectswitchimage.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    effectswitch.clearColorFilter();
                    effectswitchimage.clearColorFilter();
                }
                return false;
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


        homeswitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    homeswitch.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    homeswitchimage.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    homeswitch.clearColorFilter();
                    homeswitchimage.clearColorFilter();
                }
                return false;
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


        bgmswitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    bgmswitch.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    bgmswitchimage.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    bgmswitch.clearColorFilter();
                    bgmswitchimage.clearColorFilter();
                }
                return false;
            }
        });
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


        closebutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    closebutton.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    closebutton.clearColorFilter();
                }
                return false;
            }
        });
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(effectsound)
                    mediaPlayer.start();
                dialog.dismiss();
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

