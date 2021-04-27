package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import maes.tech.intentanim.CustomIntent;

public class FinishPage extends AppCompatActivity implements View.OnClickListener {

    Button backToHome, restart;
    public static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_page);
        mediaPlayer = MediaPlayer.create(FinishPage.this, R.raw.effect);

        backToHome = findViewById(R.id.backtohome);
        backToHome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())){
                        mediaPlayer.start();
                    }
                    backToHome.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    backToHome.getBackground().clearColorFilter();
                }
                return false;
            }
        });
        backToHome.setOnClickListener(this);
        restart = findViewById(R.id.restart);
        restart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())){
                        mediaPlayer.start();
                    }
                    restart.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    restart.getBackground().clearColorFilter();
                }
                return false;
            }
        });
        restart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.backtohome:
                i.setClass(FinishPage.this, MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.restart:
                DialogSetting.resetHints(getApplicationContext());
                DialogSetting.resetLevel(getApplicationContext());
                i.setClass(FinishPage.this, gameContent.class);
                startActivity(i);
                finish();
                break;
        }
    }

    ////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        Intent i = new Intent(FinishPage.this, MainActivity.class);
        startActivity(i);
        CustomIntent.customType(FinishPage.this, "bottom-to-up");
        DialogSetting.counter++;
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        DialogSetting.checkpause();
        DialogSetting.counter--;
    }

    @Override
    protected void onResume() {
        super.onResume();
        DialogSetting.counter=0;
        DialogSetting.bgmsoundcontrol(getApplication());
    }
}