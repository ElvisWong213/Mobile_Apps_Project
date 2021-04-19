package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class gamePage extends AppCompatActivity {
    ImageButton btn_game_setting;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        btn_game_setting = findViewById(R.id.btn_game_setting);
        btn_game_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.effect);
                if (DialogSetting.effectsoundcontrol(getApplicationContext())){
                    mediaPlayer.start();
                }
                DialogSetting.DialogManager(gamePage.this);
            }
        });
    }






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