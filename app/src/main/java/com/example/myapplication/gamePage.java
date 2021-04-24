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

public class gamePage extends AppCompatActivity implements View.OnClickListener {
    ImageButton btn_game_setting;
    MediaPlayer mediaPlayer;
    Button btn_win;
    Button btn_gamestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.effect);
        btn_win = findViewById(R.id.btn_win);
        btn_win.setOnClickListener(this);
        btn_gamestart = findViewById(R.id.gamestart);
        btn_gamestart.setOnClickListener(this);
        btn_game_setting = findViewById(R.id.btn_game_setting);
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
    }

        public void onClick(View v){
            switch (v.getId()) {
                case R.id.btn_win:
                    DialogSetting.win_DialogManager(gamePage.this);
                    break;
                case R.id.gamestart:
                    Intent i = new Intent(gamePage.this,gameContent.class);

            }
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