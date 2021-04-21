package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import maes.tech.intentanim.CustomIntent;

public class LoadingPage extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    boolean firsttime = true;

    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(150);
        progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        start_progressbar();

    }


    public void start_progressbar(){
        Intent intent = new Intent();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < progressBar.getMax()){
                    progressStatus++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(progressStatus == progressBar.getMax()){
                    intent.setClass(LoadingPage.this,MainActivity.class);
                    startActivity(intent);
                    CustomIntent.customType(LoadingPage.this, "fadein-to-fadeout");
                    finish();
                }

            }
        }).start();
    }

    @Override
    public void onBackPressed() {
    }
}