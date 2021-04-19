package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class gamePage extends AppCompatActivity {
    ImageButton btn_game_setting;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.effect);






        btn_game_setting = findViewById(R.id.btn_game_setting);
        btn_game_setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())){
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






////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        Intent i = new Intent(gamePage.this, MainActivity.class);
        startActivity(i);
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