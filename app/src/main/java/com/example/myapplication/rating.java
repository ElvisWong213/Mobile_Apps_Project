package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
=======
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class rating extends AppCompatActivity {
    RatingBar ratingBar;
    ImageView btSubmit;
<<<<<<< Updated upstream
    Dialog dialog;
=======
    public static Class currentPage;
    public static MediaPlayer mediaPlayer;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        mediaPlayer = MediaPlayer.create(rating.this, R.raw.effect);

<<<<<<< Updated upstream
        dialog = new Dialog(rating.this);
        dialog.setContentView(R.layout.activity_feedback_dialog_box);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.feedback_dialog_resource));
        }

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;

        Button yes = dialog.findViewById(R.id.btn_right);
        Button no = dialog.findViewById(R.id.btn_left);
        yes.setOnClickListener(new View.OnClickListener() {
=======
        ratingBar = findViewById(R.id.rating_bar);
        btSubmit = findViewById(R.id.ratebtnswitch);
        btSubmit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())){
                        mediaPlayer.start();
                    }
                    btSubmit.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btSubmit.clearColorFilter();
                }
                return false;
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
>>>>>>> Stashed changes
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "已送出評分", Toast.LENGTH_SHORT).show();
                Intent it = new Intent();
                it.setClass(rating.this,currentPage);
                startActivity(it);
                dialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ratingBar = findViewById(R.id.rating_bar);
        btSubmit = findViewById(R.id.ratebtnswitch);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    ////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        Intent i = new Intent(rating.this, currentPage);
        startActivity(i);
        CustomIntent.customType(rating.this, "up-to-bottom");
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