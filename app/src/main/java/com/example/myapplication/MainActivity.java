package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    AlertDialog.Builder builder;
    Dialog dialog;
    MediaPlayer mediaPlayer;
    public static Intent svc;
    private TextView textView;
    private int x, y; // The touch coordinates
    private ViewGroup mainLayout;
    private RelativeLayout rl;
    private ImageView rocket, main_title;
    private CountDownTimer timer,rockettimer,rockettimer2;
    private Boolean blue = true;
    private ImageButton main_play,btn_home_setting;
    private ImageView logo1, logo2, logo3,logo4,logo5,logo6,logo7,logo8,logo9,logo10;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.effect);
        main_title = findViewById(R.id.main_title);
        main_play = findViewById(R.id.main_play);
        main_play.setOnClickListener(this);
        main_play.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())){
                        mediaPlayer.start();
                    }
                    main_play.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    main_play.clearColorFilter();
                }
                return false;
            }
        });
        dialog = new Dialog(this);

        btn_home_setting = findViewById(R.id.btn_home_setting);
        setlocation(btn_home_setting,945,0);
        btn_home_setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())){
                        mediaPlayer.start();
                    }
                    btn_home_setting.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_home_setting.clearColorFilter();
                    DialogSetting.DialogManager(MainActivity.this);
                }
                return false;
            }
        });



        rocket = findViewById(R.id.rocket);
        rocket.setRotation(-50);
        rocketMove();

        rl = findViewById(R.id.rl);
        rl.setOnTouchListener(this);

        logo1 = findViewById(R.id.logo1);
        logo2 = findViewById(R.id.logo2);
        logo2.setRotation(45);
        logo3 = findViewById(R.id.logo3);
        logo3.setRotation(-20);
        logo4 = findViewById(R.id.logo4);
        logo5 = findViewById(R.id.logo5);
        logo5.setRotation(40);
        logo6 = findViewById(R.id.logo6);
        logo7 = findViewById(R.id.logo7);
        logo8 = findViewById(R.id.logo8);
        logo8.setRotation(20);
        logo9 = findViewById(R.id.logo9);
        logo9.setRotation(-22);
        logo10 = findViewById(R.id.logo10);
        logo10.setRotation(33);
        setlocation(logo1, 250, 675);
        setlocation(logo2, 905, 400);
        setlocation(logo3, 349, 149);
        setlocation(logo4, 720, 65);
        setlocation(logo5, 80, 1175);
        setlocation(logo6, 896, 921);
        setlocation(logo7, 928, 1470);
        setlocation(logo8, 35, 250);
        setlocation(logo9, 132, 1577);
        setlocation(logo10, 790, 1629);
        logoSize();

//==============timer====================\\
        timer = new CountDownTimer(1500, 20) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                try{
                    if(blue==true) {
                        changeYellow();
                    }else{
                        changeBlue();
                    }
                }catch(Exception e){
                    Log.e("Error", "Error: " + e.toString());
                }
            }
        }.start();

        rockettimer = new CountDownTimer(20000, 20) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                try{
                    rocket.setImageResource(R.drawable.ufo);
                    rocket.setRotation(35);
                    rockettimer2.start();
                }catch(Exception e){
                    Log.e("Error", "Error: " + e.toString());
                }
            }
        }.start();

        rockettimer2 = new CountDownTimer(10000, 20) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                try{
                    rocket.setImageResource(R.drawable.rocket);
                    rocket.setRotation(-50);
                    rockettimer.start();
                }catch(Exception e){
                    Log.e("Error", "Error: " + e.toString());
                }
            }
        }.start();
    }








    public void setlocation(ImageView logo,float x, float y){
        logo.setX(x);
        logo.setY(y);
    }


    public void changeYellow(){
        main_title.setImageResource(R.drawable.title2);
        blue=false;
        timer.start();
    }
    public void changeBlue(){
        main_title.setImageResource(R.drawable.title);
        blue=true;
        timer.start();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            showQuitDialog();
            return true;
        }
        else
            return false;
    }


    private void showQuitDialog(){
        builder = new AlertDialog.Builder(this);
        builder.setMessage("你要離開嗎?");
        builder.setCancelable(false);

        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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
        alert.setTitle("關閉程式");
        alert.show();
    }


    public void onClick(View v){
        Intent it = new Intent();
        switch (v.getId()) {
            case R.id.main_play:
                it.setClass(MainActivity.this,gamePage.class);
                startActivity(it);
                CustomIntent.customType(MainActivity.this, "up-to-bottom");
                DialogSetting.counter++;
                finish();
            break;
        }
    }


    public boolean onTouch(View v, MotionEvent e){
        int eventAction = e.getAction();

        x = (int) e.getX();
        y = (int) e.getY();

        switch (eventAction){
            case MotionEvent.ACTION_DOWN:
                textView.setText("Action_down: x=" + x + ", y = "  + y);
                break;
            case MotionEvent.ACTION_UP:
                textView.setText("Action_up: x=" + x + ", y = "  + y);
                break;
            case MotionEvent.ACTION_MOVE:
                textView.setText("Action_move: x=" + x + ", y = "  + y);
                break;
        }
        return true;
    }

    public void rocketMove() {
        Animation a = new TranslateAnimation(1000, Animation.ABSOLUTE - 2000,
                950, Animation.ABSOLUTE - 300);
        a.setDuration(10000);
        a.setRepeatCount(Animation.INFINITE);
        a.setRepeatMode(Animation.RESTART);
        rocket.startAnimation(a);
    }

    public void logoSize() {
        Animation a = new ScaleAnimation(1.0f, 0.97f,
                1.0f, 0.97f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        a.setDuration(6000);
        a.setRepeatCount(Animation.INFINITE);
        a.setRepeatMode(Animation.REVERSE);
        logo2.startAnimation(a);
        logo3.startAnimation(a);
        logo4.startAnimation(a);
        logo5.startAnimation(a);
        logo6.startAnimation(a);
        logo7.startAnimation(a);
        logo8.startAnimation(a);

    }

////////////////////////////////////////////////////////////
    @Override
    protected void onPause() {
        super.onPause();
        DialogSetting.checkpause();
        DialogSetting.counter--;
    }

    @Override
    protected void onResume() {
        super.onResume();
        svc =new Intent(this, BackgroundSoundService.class);
        startService(svc);
        DialogSetting.counter = 0;
    }
}