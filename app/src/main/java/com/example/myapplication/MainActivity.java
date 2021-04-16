package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    AlertDialog.Builder builder;
    private TextView textView;
    private int x, y; // The touch coordinates
    private ViewGroup mainLayout;
    private RelativeLayout rl;
    private ImageView rocket, main_title;
    private CountDownTimer timer;
    private Boolean blue = true;
    private ImageButton main_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        main_title = findViewById(R.id.main_title);
        main_play = findViewById(R.id.main_play);
        main_play.setOnClickListener(this);
        main_play.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    main_play.setImageResource(R.drawable.play2);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    main_play.setImageResource(R.drawable.play);
                }
                return false;
            }
        });

        rocket = findViewById(R.id.rocket);
        rocket.setRotation(-50);
        rocketMove();

        rl = findViewById(R.id.rl);
        rl.setOnTouchListener(this);




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
                950, Animation.ABSOLUTE - 200);

        a.setDuration(10000);

        a.setRepeatCount(Animation.INFINITE);

        a.setRepeatMode(Animation.RESTART);

        rocket.startAnimation(a);
    }
}