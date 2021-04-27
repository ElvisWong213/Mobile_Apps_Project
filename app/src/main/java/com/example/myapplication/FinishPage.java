package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import maes.tech.intentanim.CustomIntent;

public class FinishPage extends AppCompatActivity implements View.OnClickListener {

    Button backToHome, restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_page);

        backToHome = findViewById(R.id.backtohome);
        backToHome.setOnClickListener(this);
        restart = findViewById(R.id.restart);
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
}