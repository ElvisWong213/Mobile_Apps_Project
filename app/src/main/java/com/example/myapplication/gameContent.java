package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class gameContent extends AppCompatActivity implements View.OnClickListener{
    String chinese = "";
    String checkpointAns;
    Handler handler = new Handler();
    TextView userinput, emoji, level;
    Button submit, clear, next;
    Button textBtn1,textBtn2,textBtn3,textBtn4,textBtn5,textBtn6,textBtn7,textBtn8,textBtn9,textBtn10,textBtn11,textBtn12,textBtn13,textBtn14;
    ImageButton btn_gamecontent_setting;
    MediaPlayer mediaPlayer,mediaPlayer3;
    ImageView rocket2;
    Button btn_hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);
        rocket2 = findViewById(R.id.rocket2);
        btn_hint = findViewById(R.id.btn_hint);
        btn_hint.setOnClickListener(this);
        rocket2.setRotation(310);
        Animation a = new TranslateAnimation(1000, Animation.ABSOLUTE - 2000,
                950, Animation.ABSOLUTE - 300);
        a.setDuration(10000);
        a.setRepeatCount(Animation.INFINITE);
        a.setRepeatMode(Animation.RESTART);
        rocket2.startAnimation(a);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.effect);
        mediaPlayer3 = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
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
        level = findViewById(R.id.level);
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


        switch (LoadingPage.checkpoint) {
            case 1:
                level.setText("關卡1");
                emoji.setText("◀️▶️\uD83D\uDE23");
                textBtn2.setText("左");
                textBtn3.setText("的");
                textBtn4.setText("上");
                textBtn5.setText("我");
                textBtn6.setText("為");
                textBtn9.setText("他");
                textBtn10.setText("難");
                textBtn11.setText("易");
                textBtn12.setText("右");
                textBtn13.setText("下");
                break;
            case 2:
                level.setText("關卡2");
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
                level.setText("關卡3");
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
                level.setText("關卡4");
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
            case 5:
                level.setText("關卡5");
                emoji.setText("✋\uD83C\uDF87\uD83D\uDE4C\uD83C\uDFA8");
                textBtn2.setText("步");
                textBtn3.setText("光");
                textBtn4.setText("色");
                textBtn5.setText("不");
                textBtn6.setText("十");
                textBtn9.setText("跑");
                textBtn10.setText("歡");
                textBtn11.setText("五");
                textBtn12.setText("提");
                textBtn13.setText("找");
                break;
            case 6:
                level.setText("關卡6");
                emoji.setText("®️\uD83D\uDC99®️\uD83C\uDDEE\uD83C\uDDF9");
                textBtn2.setText("複");
                textBtn3.setText("真");
                textBtn4.setText("色");
                textBtn5.setText("心");
                textBtn6.setText("印");
                textBtn9.setText("製");
                textBtn10.setText("真");
                textBtn11.setText("五");
                textBtn12.setText("粉");
                textBtn13.setText("意");
                break;
            case 7:
                level.setText("關卡7");
                emoji.setText("\uD83C\uDF8E️➡️\uD83D\uDC80");
                textBtn2.setText("以");
                textBtn3.setText("頭");
                textBtn4.setText("待");
                textBtn5.setText("心");
                textBtn6.setText("斃");
                textBtn9.setText("骨");
                textBtn10.setText("真");
                textBtn11.setText("坐");
                textBtn12.setText("人");
                textBtn13.setText("去");
                break;
            case 8:
                level.setText("關卡8");
                emoji.setText("\uD83D\uDC44✅\uD83D\uDC99✈️");
                textBtn2.setText("機");
                textBtn3.setText("是");
                textBtn4.setText("確");
                textBtn5.setText("心");
                textBtn6.setText("的");
                textBtn9.setText("口");
                textBtn10.setText("定");
                textBtn11.setText("坐");
                textBtn12.setText("唇");
                textBtn13.setText("非");
                break;
            case 9:
                level.setText("關卡9");
                emoji.setText("\uD83D\uDC37\uD83D\uDC6D\uD83D\uDC36\uD83D\uDC6C");
                textBtn2.setText("狗");
                textBtn3.setText("是");
                textBtn4.setText("物");
                textBtn5.setText("豬");
                textBtn6.setText("的");
                textBtn9.setText("友");
                textBtn10.setText("定");
                textBtn11.setText("朋");
                textBtn12.setText("動");
                textBtn13.setText("人");
                break;
            case 10:
                level.setText("關卡10");
                emoji.setText("\uD83D\uDC49\uD83C\uDF70\uD83C\uDF88\uD83D\uDC11");
                textBtn2.setText("手");
                textBtn3.setText("趾");
                textBtn4.setText("物");
                textBtn5.setText("羊");
                textBtn6.setText("揚");
                textBtn9.setText("蛋");
                textBtn10.setText("高");
                textBtn11.setText("朋");
                textBtn12.setText("動");
                textBtn13.setText("飛");
                break;
            case 11:
                level.setText("關卡11");
                emoji.setText("\uD83D\uDEC0\uD83D\uDE2D❌\uD83D\uDCA6");
                textBtn2.setText("洗");
                textBtn3.setText("欲");
                textBtn4.setText("淚");
                textBtn5.setText("羊");
                textBtn6.setText("水");
                textBtn9.setText("蛋");
                textBtn10.setText("哭");
                textBtn11.setText("朋");
                textBtn12.setText("叉");
                textBtn13.setText("無");
                break;
            case 12:
                level.setText("關卡12");
                emoji.setText("\uD83D\uDE97\uD83D\uDCA7\uD83D\uDC34\uD83D\uDC09");
                textBtn2.setText("水");
                textBtn3.setText("欲");
                textBtn4.setText("淚");
                textBtn5.setText("馬");
                textBtn6.setText("水");
                textBtn9.setText("蛋");
                textBtn10.setText("哭");
                textBtn11.setText("車");
                textBtn12.setText("龍");
                textBtn13.setText("飛");
                break;
            case 13:
                level.setText("關卡13");
                emoji.setText("\uD83D\uDC48\uD83D\uDC49✌️\uD83D\uDEA7");
                textBtn2.setText("交");
                textBtn3.setText("右");
                textBtn4.setText("手");
                textBtn5.setText("通");
                textBtn6.setText("左");
                textBtn9.setText("阻");
                textBtn10.setText("哭");
                textBtn11.setText("難");
                textBtn12.setText("指");
                textBtn13.setText("兩");
                break;
        }




    }

    public void onClick(View v){
        if (DialogSetting.effectsoundcontrol(getApplicationContext())){
            mediaPlayer.start();
        }
        switch (v.getId()) {
            case R.id.textBtn1:
                chinese+= textBtn1.getText();
                userinput.setText(chinese);
                textBtn1.setEnabled(false);
                break;
            case R.id.textBtn2:
                chinese+= textBtn2.getText();
                userinput.setText(chinese);
                textBtn2.setEnabled(false);
                break;
            case R.id.textBtn3:
                chinese+= textBtn3.getText();
                userinput.setText(chinese);
                textBtn3.setEnabled(false);
                break;
            case R.id.textBtn4:
                chinese+= textBtn4.getText();
                userinput.setText(chinese);
                textBtn4.setEnabled(false);
                break;
            case R.id.textBtn5:
                chinese+= textBtn5.getText();
                userinput.setText(chinese);
                textBtn5.setEnabled(false);
                break;
            case R.id.textBtn6:
                chinese+= textBtn6.getText();
                userinput.setText(chinese);
                textBtn6.setEnabled(false);
                break;
            case R.id.textBtn7:
                chinese+= textBtn7.getText();
                userinput.setText(chinese);
                textBtn7.setEnabled(false);
                break;
            case R.id.textBtn8:
                chinese+= textBtn8.getText();
                userinput.setText(chinese);
                textBtn8.setEnabled(false);
                break;
            case R.id.textBtn9:
                chinese+= textBtn9.getText();
                userinput.setText(chinese);
                textBtn9.setEnabled(false);
                break;
            case R.id.textBtn10:
                chinese+= textBtn10.getText();
                userinput.setText(chinese);
                textBtn10.setEnabled(false);
                break;
            case R.id.textBtn11:
                chinese+= textBtn11.getText();
                userinput.setText(chinese);
                textBtn11.setEnabled(false);
                break;
            case R.id.textBtn12:
                chinese+= textBtn12.getText();
                userinput.setText(chinese);
                textBtn12.setEnabled(false);
                break;
            case R.id.textBtn13:
                chinese+= textBtn13.getText();
                userinput.setText(chinese);
                textBtn13.setEnabled(false);
                break;
            case R.id.textBtn14:
                chinese+= textBtn14.getText();
                userinput.setText(chinese);
                textBtn14.setEnabled(false);
                break;
            case R.id.submit:
                textBtn1.setEnabled(true);
                textBtn2.setEnabled(true);
                textBtn3.setEnabled(true);
                textBtn4.setEnabled(true);
                textBtn5.setEnabled(true);
                textBtn6.setEnabled(true);
                textBtn7.setEnabled(true);
                textBtn8.setEnabled(true);
                textBtn9.setEnabled(true);
                textBtn10.setEnabled(true);
                textBtn11.setEnabled(true);
                textBtn12.setEnabled(true);
                textBtn13.setEnabled(true);
                textBtn14.setEnabled(true);
                switch(LoadingPage.checkpoint) {

                    case 1: checkpointAns = "左右為難"; break;
                    case 2: checkpointAns= "打草驚蛇"; break;
                    case 3: checkpointAns="心口不一"; break;
                    case 4: checkpointAns="走馬看花"; break;
                    case 5: checkpointAns="五光十色"; break;
                    case 6: checkpointAns="真心真意"; break;
                    case 7: checkpointAns="坐以待斃"; break;
                    case 8: checkpointAns="口是心非"; break;
                    case 9: checkpointAns="豬朋狗友"; break;
                    case 10: checkpointAns="趾高氣揚"; break;
                    case 11: checkpointAns="欲哭無淚"; break;
                    case 12: checkpointAns="車水馬龍"; break;
                    case 13: checkpointAns="左右兩難"; break;
                    default:
                        checkpointAns = null; break;
                }
                if(checkpointAns.equals(chinese)){

                chinese="";
                userinput.setText(chinese);
                DialogSetting.win_DialogManager(gameContent.this);
                LoadingPage.checkpoint++;
                levelText(LoadingPage.checkpoint);

                }
                else{
                shakeanimationandwrongeffect(emoji,-10,10);
                chinese="";
                userinput.setText(chinese);
                }

            case R.id.clear:
                textBtn1.setEnabled(true);
                textBtn2.setEnabled(true);
                textBtn3.setEnabled(true);
                textBtn4.setEnabled(true);
                textBtn5.setEnabled(true);
                textBtn6.setEnabled(true);
                textBtn7.setEnabled(true);
                textBtn8.setEnabled(true);
                textBtn9.setEnabled(true);
                textBtn10.setEnabled(true);
                textBtn11.setEnabled(true);
                textBtn12.setEnabled(true);
                textBtn13.setEnabled(true);
                textBtn14.setEnabled(true);
                chinese="";
                userinput.setText("");
                chinese="";
                break;
            case R.id.btn_hint:
                DialogSetting.hint_DialogManager(gameContent.this);
                if(LoadingPage.checkpoint==1)
                    DialogSetting.hint.setText("左");
                if(LoadingPage.checkpoint==2)
                    DialogSetting.hint.setText("打");
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
        switch (LoadingPage.checkpoint){
            case 1:
                level.setText("關卡1");
                emoji.setText("◀️▶️\uD83D\uDE23");
                textBtn2.setText("左");
                textBtn3.setText("的");
                textBtn4.setText("上");
                textBtn5.setText("我");
                textBtn6.setText("為");
                textBtn9.setText("他");
                textBtn10.setText("難");
                textBtn11.setText("易");
                textBtn12.setText("右");
                textBtn13.setText("下");
                break;
            case 2:
                level.setText("關卡2");
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
                level.setText("關卡3");
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
                level.setText("關卡4");
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
            case 5:
                level.setText("關卡5");
                emoji.setText("✋\uD83C\uDF87\uD83D\uDE4C\uD83C\uDFA8");
                textBtn2.setText("步");
                textBtn3.setText("光");
                textBtn4.setText("色");
                textBtn5.setText("不");
                textBtn6.setText("十");
                textBtn9.setText("跑");
                textBtn10.setText("歡");
                textBtn11.setText("五");
                textBtn12.setText("提");
                textBtn13.setText("找");
                break;
            case 6:
                level.setText("關卡6");
                emoji.setText("®️\uD83D\uDC99®️\uD83C\uDDEE\uD83C\uDDF9");
                textBtn2.setText("複");
                textBtn3.setText("真");
                textBtn4.setText("色");
                textBtn5.setText("心");
                textBtn6.setText("印");
                textBtn9.setText("製");
                textBtn10.setText("真");
                textBtn11.setText("五");
                textBtn12.setText("粉");
                textBtn13.setText("意");
                break;
            case 7:
                level.setText("關卡7");
                emoji.setText("\uD83C\uDF8E️➡️\uD83D\uDC80");
                textBtn2.setText("以");
                textBtn3.setText("頭");
                textBtn4.setText("待");
                textBtn5.setText("心");
                textBtn6.setText("斃");
                textBtn9.setText("骨");
                textBtn10.setText("真");
                textBtn11.setText("坐");
                textBtn12.setText("人");
                textBtn13.setText("去");
                break;
            case 8:
                level.setText("關卡8");
                emoji.setText("\uD83D\uDC44✅\uD83D\uDC99✈️");
                textBtn2.setText("機");
                textBtn3.setText("是");
                textBtn4.setText("確");
                textBtn5.setText("心");
                textBtn6.setText("的");
                textBtn9.setText("口");
                textBtn10.setText("定");
                textBtn11.setText("坐");
                textBtn12.setText("唇");
                textBtn13.setText("非");
                break;
            case 9:
                level.setText("關卡9");
                emoji.setText("\uD83D\uDC37\uD83D\uDC6D\uD83D\uDC36\uD83D\uDC6C");
                textBtn2.setText("狗");
                textBtn3.setText("是");
                textBtn4.setText("物");
                textBtn5.setText("豬");
                textBtn6.setText("的");
                textBtn9.setText("友");
                textBtn10.setText("定");
                textBtn11.setText("朋");
                textBtn12.setText("動");
                textBtn13.setText("人");
                break;
            case 10:
                level.setText("關卡10");
                emoji.setText("\uD83D\uDC49\uD83C\uDF70\uD83C\uDF88\uD83D\uDC11");
                textBtn2.setText("手");
                textBtn3.setText("趾");
                textBtn4.setText("物");
                textBtn5.setText("羊");
                textBtn6.setText("揚");
                textBtn9.setText("蛋");
                textBtn10.setText("高");
                textBtn11.setText("朋");
                textBtn12.setText("動");
                textBtn13.setText("飛");
                break;
            case 11:
                level.setText("關卡11");
                emoji.setText("\uD83D\uDEC0\uD83D\uDE2D❌\uD83D\uDCA6");
                textBtn2.setText("洗");
                textBtn3.setText("欲");
                textBtn4.setText("淚");
                textBtn5.setText("羊");
                textBtn6.setText("水");
                textBtn9.setText("蛋");
                textBtn10.setText("哭");
                textBtn11.setText("朋");
                textBtn12.setText("叉");
                textBtn13.setText("無");
                break;
            case 12:
                level.setText("關卡12");
                emoji.setText("\uD83D\uDE97\uD83D\uDCA7\uD83D\uDC34\uD83D\uDC09");
                textBtn2.setText("水");
                textBtn3.setText("欲");
                textBtn4.setText("淚");
                textBtn5.setText("馬");
                textBtn6.setText("水");
                textBtn9.setText("蛋");
                textBtn10.setText("哭");
                textBtn11.setText("車");
                textBtn12.setText("龍");
                textBtn13.setText("飛");
                break;
            case 13:
                level.setText("關卡13");
                emoji.setText("\uD83D\uDC48\uD83D\uDC49✌️\uD83D\uDEA7");
                textBtn2.setText("交");
                textBtn3.setText("右");
                textBtn4.setText("手");
                textBtn5.setText("通");
                textBtn6.setText("左");
                textBtn9.setText("阻");
                textBtn10.setText("哭");
                textBtn11.setText("難");
                textBtn12.setText("指");
                textBtn13.setText("兩");
                break;
        }
    }

    public void shakeanimationandwrongeffect(TextView view ,int x1,int x2){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Animation a = new TranslateAnimation(x1,x2,0,0);
                        a.setDuration(10);
                        a.setRepeatMode(Animation.REVERSE);
                        a.setRepeatCount(5);
                        view.startAnimation(a);
                        if (DialogSetting.effectsoundcontrol(getApplicationContext())){

                        mediaPlayer3.start();
                       ;}
                    }
                });

            }
        }).start();

    }
}

