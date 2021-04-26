package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import maes.tech.intentanim.CustomIntent;

public class DialogSetting{
    public static Boolean effectsound;
    public static Boolean bgmsound;
    public static MediaPlayer mediaPlayer,mediaPlayer2;
    public static SharedPreferences pref, hints_pref, level_pref,temp_pref;
    public static int counter = 0;
    public static Dialog dialog, win_dialog,hint_dialog;
    public static TextView hint;
    public static int hintCounter = 2;
    private static boolean hintBool;
    public static ImageView hintsline;
    public static TextView tv_get;


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
        ImageView rateswitch = dialog.findViewById(R.id.rateswitch);
        ImageView rateswitchimage = dialog.findViewById(R.id.rateswitchimage);
        ImageView restartswitch = dialog.findViewById(R.id.restartswitch);
        ImageView restartimage = dialog.findViewById(R.id.restartswitchimage);

        rateswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                if(pref.getBoolean("effectsound",true) == true)
                    mediaPlayer.start();
                if(page.getClass()!=MainActivity.class || (page.getClass()==MainActivity.class)) {
                    rating.currentPage = page.getClass();
                    it.setClass(page, rating.class);
                    page.startActivity(it);
                    CustomIntent.customType(page, "bottom-to-up");
                    ((Activity) page).finish();
                }
                dialog.dismiss();
            }
        });

        rateswitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    rateswitch.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    rateswitchimage.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    rateswitch.clearColorFilter();
                    rateswitchimage.clearColorFilter();
                }
                return false;
            }
        });

        LinearLayout CardLayout = dialog.findViewById(R.id.CardLayout);
        dialog.show();
        CardLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    dialog.dismiss();
                }
                return false;
            }
        });

        mediaPlayer = MediaPlayer.create(page, R.raw.effect);
        if(pref.getBoolean("effectsound",true) == false)
            effectswitchimage.setImageResource(R.drawable.muteon);
        if(pref.getBoolean("bgmsound",true) == false)
            bgmswitchimage.setImageResource(R.drawable.musicoff);

        if(page.getClass()==MainActivity.class)
            homeswitchimage.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);


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

        restartswitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    restartswitch.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    restartimage.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    restartswitch.clearColorFilter();
                    restartimage.clearColorFilter();
                }
                return false;
            }
        });

        restartswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(page);
                    builder.setMessage("放棄目前進度，回到第一關重新玩?\n(不會保留燃料數量)");
                    builder.setCancelable(false);

                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(page, "關卡和燃料數量已經重設", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            resetHints(page.getApplicationContext());
                            resetLevel(page.getApplicationContext());
                            resettempHints(page.getApplicationContext());
                            intent.setClass(page,LoadingPage.class);
                            page.startActivity(intent);
                            dialog.dismiss();
                            System.exit(0);
                        }
                    });
                    builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.setTitle("重新開始遊戲");
                    alert.show();
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
                Intent it = new Intent();
                if(pref.getBoolean("effectsound",true) == true)
                    mediaPlayer.start();
                if(page.getClass()!=MainActivity.class) {
                    it.setClass(page, MainActivity.class);
                    page.startActivity(it);
                    CustomIntent.customType(page, "bottom-to-up");
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



    public static void win_DialogManager (Context page){
        mediaPlayer = MediaPlayer.create(page, R.raw.effect);
        mediaPlayer2 = MediaPlayer.create(page, R.raw.sound_win);
        if(effectsound)
        mediaPlayer2.start();
        win_dialog = new Dialog(page);
        win_dialog.setContentView(R.layout.win_layout_dialog);
        win_dialog.getWindow().setDimAmount(0.8f);
        win_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        win_dialog.setCanceledOnTouchOutside(false);
        win_dialog.setCancelable(false);
        win_dialog.show();
        Animation appearAnimation = new ScaleAnimation(0f, 1f,
                0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        appearAnimation.setDuration(300);
        Animation buttonAnimation = new AlphaAnimation(0.0f, 1.0f);
        buttonAnimation.setDuration(600);

        tv_get = win_dialog.findViewById(R.id.tv_get);
        tv_get.setVisibility(View.GONE);
        hintsline = win_dialog.findViewById(R.id.hintsline);
        gettempHints(page);
        ImageView img_win = win_dialog.findViewById(R.id.img_win);
        ImageView btn_next  = win_dialog.findViewById(R.id.btn_next);
        TextView tv_win = win_dialog.findViewById(R.id.tv_win);
        hintsline.startAnimation(appearAnimation);
        img_win.startAnimation(appearAnimation);
        btn_next.startAnimation(buttonAnimation);
        Animation disppearAnimation = new ScaleAnimation(1f, 0f,
                1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        disppearAnimation.setDuration(300);
        Animation buttonAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        buttonAnimation2.setDuration(300);
        buttonAnimation2.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                win_dialog.dismiss();
            }
        });

//////////////////////////////////////////////////////


        btn_next.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_next.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_next.clearColorFilter();
                }
                return false;
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(effectsound)
                    mediaPlayer.start();
                img_win.startAnimation(disppearAnimation);
                btn_next.startAnimation(buttonAnimation2);
                tv_win.startAnimation(buttonAnimation2);
                hintsline.startAnimation(disppearAnimation);
                tv_get.startAnimation(disppearAnimation);
            }
        });





    }

    public static void hint_DialogManager (Context page) {
        mediaPlayer = MediaPlayer.create(page, R.raw.effect);
        hint_dialog = new Dialog(page);
        hint_dialog.setContentView(R.layout.activity_game_hint);
        hint_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        hint_dialog.show();
        ImageView btn_close = hint_dialog.findViewById(R.id.btn_close);
        TextView tvclose = hint_dialog.findViewById(R.id.tv_close);
        ImageView btn_use = hint_dialog.findViewById(R.id.btn_use);
        Animation disppearAnimation = new ScaleAnimation(1f, 0f,
                1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        disppearAnimation.setDuration(300);
        Animation buttonAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        buttonAnimation2.setDuration(300);
        buttonAnimation2.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
            }
        });

        btn_close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_close.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_close.clearColorFilter();
                }
                return false;
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(effectsound)
                    mediaPlayer.start();
                set_hints_confirm(false);
                hint_dialog.dismiss();

            }
        });

        btn_use.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_use.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_use.clearColorFilter();
                }
                return false;
            }
        });
        btn_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(effectsound)
                    mediaPlayer.start();
                set_hints_confirm(true);
                hint_dialog.dismiss();
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

    public static int getHints(Context page){
        hints_pref = page.getSharedPreferences("Myhints", Context.MODE_PRIVATE);
        return hints_pref.getInt("Myhints",2);
    }

    public static void addHints(Context page){
        int temp = hints_pref.getInt("Myhints",2);
        hints_pref = page.getSharedPreferences("Myhints", Context.MODE_PRIVATE);
        SharedPreferences.Editor hints_editor = hints_pref.edit();

        hints_editor.putInt("Myhints", temp + 1);
        hints_editor.commit();
    }

    public static void tempHints(Context page){
        temp_pref = page.getSharedPreferences("temphints", Context.MODE_PRIVATE);
        SharedPreferences.Editor temp_editor = temp_pref.edit();
        if(temp_pref.getInt("temphints",0)!=3) {
            temp_editor.putInt("temphints", temp_pref.getInt("temphints", 0) + 1);
            temp_editor.commit();
        }
        if(temp_pref.getInt("temphints",0)==3){
            addHints(page);
            temp_editor.putInt("temphints", temp_pref.getInt("temphints", 0) - 3);
            temp_editor.commit();

        }
    }

    public static void gettempHints(Context page){
        Animation appearAnimation = new ScaleAnimation(0f, 1f,
                0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        appearAnimation.setDuration(300);
        Animation buttonAnimation = new AlphaAnimation(0.0f, 1.0f);
        buttonAnimation.setDuration(600);


        temp_pref = page.getSharedPreferences("temphints", Context.MODE_PRIVATE);
        switch (temp_pref.getInt("temphints",0)){
            case 0:
                hintsline.setImageResource(R.drawable.line);
                break;
            case 1:
                hintsline.setImageResource(R.drawable.line2);
                break;
            case 2:
                hintsline.setImageResource(R.drawable.line3);
                tv_get.setVisibility(View.VISIBLE);
                tv_get.startAnimation(appearAnimation);
                break;
        }

    }

    public static void useHints(Context page){
        int temp = hints_pref.getInt("Myhints",2);
        hints_pref = page.getSharedPreferences("Myhints", Context.MODE_PRIVATE);
        SharedPreferences.Editor hints_editor = hints_pref.edit();

        hints_editor.putInt("Myhints", temp - 1);
        hints_editor.commit();
    }

    public static void resetHints(Context page){
        hints_pref = page.getSharedPreferences("Myhints", Context.MODE_PRIVATE);
        SharedPreferences.Editor hints_editor = hints_pref.edit();

        hints_editor.putInt("Myhints", 2);
        hints_editor.commit();
    }

    public static void resettempHints(Context page){
        temp_pref = page.getSharedPreferences("temphints", Context.MODE_PRIVATE);
        SharedPreferences.Editor temp_editor = temp_pref.edit();

        temp_editor.putInt("temphints", 0);
        temp_editor.commit();
    }

    public static void set_hints_confirm(Boolean input) {
        hintBool = input;
    }

    public static boolean get_hints_confirm(){
        return hintBool;
    }




    public static int getLevel(Context page){
        level_pref = page.getSharedPreferences("Mylevel", Context.MODE_PRIVATE);
        return level_pref.getInt("Mylevel",1);
    }

    public static void addLevel(Context page){
        int temp = level_pref.getInt("Mylevel",1);
        level_pref = page.getSharedPreferences("Mylevel", Context.MODE_PRIVATE);
        SharedPreferences.Editor hints_editor = level_pref.edit();

        hints_editor.putInt("Mylevel", temp + 1);
        hints_editor.commit();
    }

    public static void resetLevel(Context page){
        level_pref = page.getSharedPreferences("Mylevel", Context.MODE_PRIVATE);
        SharedPreferences.Editor hints_editor = level_pref.edit();

        hints_editor.putInt("Mylevel", 1);
        hints_editor.commit();
    }


}

