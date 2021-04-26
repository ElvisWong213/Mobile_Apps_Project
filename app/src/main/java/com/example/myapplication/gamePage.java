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
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class gamePage extends AppCompatActivity implements View.OnClickListener {
    ImageButton btn_game_setting;
    MediaPlayer mediaPlayer;
    TextView highestLevel;
    Button btn_win;
    Button btn_gamestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        highestLevel = findViewById(R.id.highestLevel);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.effect);
        btn_gamestart = findViewById(R.id.gamestart);
        btn_gamestart.setOnClickListener(this);
        btn_game_setting = findViewById(R.id.btn_game_setting);
        highestLevel.setText(String.valueOf(DialogSetting.getLevel(getApplicationContext())));
        btn_game_setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())) {
                        mediaPlayer.start();
                    }
                    btn_game_setting.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_game_setting.clearColorFilter();
                    DialogSetting.DialogManager(gamePage.this);
                }
                return false;
            }
        });

        btn_gamestart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())) {
                        mediaPlayer.start();
                    }
                    btn_game_setting.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_game_setting.clearColorFilter();
                }
                return false;
            }
        });
    }

        public void onClick(View v){


                    Intent i = new Intent(gamePage.this,gameContent.class);
                    startActivity(i);
        }






////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        Intent i = new Intent(gamePage.this, MainActivity.class);
        startActivity(i);
        CustomIntent.customType(gamePage.this, "bottom-to-up");
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