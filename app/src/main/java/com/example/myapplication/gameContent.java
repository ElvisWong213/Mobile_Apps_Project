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

public class gameContent extends AppCompatActivity implements View.OnClickListener{
    int checkpoint = 1;
    String chinese = "";
    TextView userinput, emoji;
    Button submit, clear,next;
    Button textBtn1,textBtn2,textBtn3,textBtn4,textBtn5,textBtn6,textBtn7,textBtn8,textBtn9,textBtn10,textBtn11,textBtn12,textBtn13,textBtn14;
    ImageButton btn_gamecontent_setting;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.effect);
        btn_gamecontent_setting = findViewById(R.id.btn_gamecontent_setting);
        btn_gamecontent_setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (DialogSetting.effectsoundcontrol(getApplicationContext())) {
                        mediaPlayer.start();
                    }
                    btn_gamecontent_setting.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_gamecontent_setting.clearColorFilter();
                    DialogSetting.DialogManager(gameContent.this);
                }
                return false;
            }
        });

        userinput = findViewById(R.id.userInput);
        emoji = findViewById(R.id.emoji);
        textBtn1 = findViewById(R.id.textBtn1);
        textBtn2 = findViewById(R.id.textBtn2);
        textBtn3 = findViewById(R.id.textBtn3);
        textBtn4 = findViewById(R.id.textBtn4);
        textBtn5 = findViewById(R.id.textBtn5);
        textBtn6 = findViewById(R.id.textBtn6);
        textBtn7 = findViewById(R.id.textBtn7);
        textBtn8 = findViewById(R.id.textBtn8);
        textBtn9 = findViewById(R.id.textBtn9);
        textBtn10 = findViewById(R.id.textBtn10);
        textBtn11 = findViewById(R.id.textBtn11);
        textBtn12 = findViewById(R.id.textBtn12);
        textBtn13 = findViewById(R.id.textBtn13);
        textBtn14 = findViewById(R.id.textBtn14);
        textBtn1.setOnClickListener(this);
        textBtn2.setOnClickListener(this);
        textBtn3.setOnClickListener(this);
        textBtn4.setOnClickListener(this);
        textBtn5.setOnClickListener(this);
        textBtn6.setOnClickListener(this);
        textBtn7.setOnClickListener(this);
        textBtn8.setOnClickListener(this);
        textBtn9.setOnClickListener(this);
        textBtn10.setOnClickListener(this);
        textBtn11.setOnClickListener(this);
        textBtn12.setOnClickListener(this);
        textBtn13.setOnClickListener(this);
        textBtn14.setOnClickListener(this);
        textBtn1.setVisibility(View.INVISIBLE);
        textBtn7.setVisibility(View.INVISIBLE);
        textBtn8.setVisibility(View.INVISIBLE);
        textBtn14.setVisibility(View.INVISIBLE);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(this);

        clear=findViewById(R.id.clear);
        clear.setOnClickListener(this);







    }

    public void onClick(View v){
        if (DialogSetting.effectsoundcontrol(getApplicationContext())){
            mediaPlayer.start();
        }
        switch (v.getId()) {
            case R.id.textBtn1:
                chinese+= textBtn1.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn2:
                chinese+= textBtn2.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn3:
                chinese+= textBtn3.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn4:
                chinese+= textBtn4.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn5:
                chinese+= textBtn5.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn6:
                chinese+= textBtn6.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn7:
                chinese+= textBtn7.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn8:
                chinese+= textBtn8.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn9:
                chinese+= textBtn9.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn10:
                chinese+= textBtn10.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn11:
                chinese+= textBtn11.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn12:
                chinese+= textBtn12.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn13:
                chinese+= textBtn13.getText();
                userinput.setText(chinese);
                break;
            case R.id.textBtn14:
                chinese+= textBtn14.getText();
                userinput.setText(chinese);
                break;
            case R.id.submit:
                if(checkpoint==1){
                    String checkpoint1Ans="左右為難";

                    if(checkpoint1Ans.equals(chinese)){

                        chinese="";
                        userinput.setText(chinese);
                        DialogSetting.win_DialogManager(gameContent.this);
                        checkpoint++;
                        levelText(checkpoint);

                    }
                    else{
                        chinese="";
                        userinput.setText(chinese);}
                }
                if(checkpoint==2){
                    String checkpoint2Ans="打草驚蛇";

                    if(checkpoint2Ans.equals(chinese)){

                        chinese="";
                        userinput.setText(chinese);
                        DialogSetting.win_DialogManager(gameContent.this);
                        checkpoint++;
                        levelText(checkpoint);

                    }
                    else{
                        chinese="";
                        userinput.setText(chinese);}
                }
                if(checkpoint==3){
                    String checkpoint2Ans="心口不一";

                    if(checkpoint2Ans.equals(chinese)){

                        chinese="";
                        userinput.setText(chinese);
                        DialogSetting.win_DialogManager(gameContent.this);
                        checkpoint++;
                        levelText(checkpoint);

                    }
                    else{
                        chinese="";
                        userinput.setText(chinese);}
                }
                if(checkpoint==4){
                    String checkpoint2Ans="走馬看花";

                    if(checkpoint2Ans.equals(chinese)){

                        chinese="";
                        userinput.setText(chinese);
                        DialogSetting.win_DialogManager(gameContent.this);
                        checkpoint++;
                        levelText(checkpoint);

                    }
                    else{
                        chinese="";
                        userinput.setText(chinese);}
                }
                break;
            case R.id.clear:
                chinese="";
                userinput.setText("");
                chinese="";
                break;

        }
    }

    ////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        Intent i = new Intent(gameContent.this, MainActivity.class);
        startActivity(i);
        CustomIntent.customType(gameContent.this, "bottom-to-up");
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

    public void levelText(int checkpoint){
        switch (checkpoint){
            case 2:
                emoji.setText("\uD83D\uDC4A\uD83C\uDF40\uD83D\uDE28\uD83D\uDC0D");
                textBtn2.setText("有");
                textBtn3.setText("打");
                textBtn4.setText("葉");
                textBtn5.setText("花");
                textBtn6.setText("驚");
                textBtn9.setText("拳");
                textBtn10.setText("蛇");
                textBtn11.setText("手");
                textBtn12.setText("草");
                textBtn13.setText("怕");
                break;
            case 3:
                emoji.setText("\uD83D\uDC99\uD83D\uDC44\uD83D\uDE45\u200D\uD83D\uDC46");
                textBtn2.setText("不");
                textBtn3.setText("打");
                textBtn4.setText("人");
                textBtn5.setText("藍");
                textBtn6.setText("一");
                textBtn9.setText("心");
                textBtn10.setText("上");
                textBtn11.setText("指");
                textBtn12.setText("口");
                textBtn13.setText("嘴");
                break;
            case 4:
                emoji.setText("\uD83C\uDFC3\u200D\uD83D\uDC34\uD83D\uDC40\uD83C\uDF38");
                textBtn2.setText("步");
                textBtn3.setText("眼");
                textBtn4.setText("走");
                textBtn5.setText("物");
                textBtn6.setText("看");
                textBtn9.setText("跑");
                textBtn10.setText("粉");
                textBtn11.setText("馬");
                textBtn12.setText("動");
                textBtn13.setText("花");
                break;
        }
    }
}

