package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import maes.tech.intentanim.CustomIntent;

public class gameContent extends AppCompatActivity implements View.OnClickListener{
    String chinese = "";
    Handler handler = new Handler();
    TextView emoji,hintChance, type, currentLevel2;
    ImageButton btn_gamecontent_setting;
    MediaPlayer mediaPlayer,mediaPlayer3;
    ImageView rocket2;
    ImageView btn_hint;
    ImageView ufo2;
    public static SharedPreferences pref_hints;

    ArrayList questionArrayList = new ArrayList(), answerArrayList = new ArrayList(), typeArrayList = new ArrayList(), buttonTextArrayList = new ArrayList();
    ArrayList allTextArrayList = new ArrayList();

    Button[] buttonArray = new Button[20];
    int[] buttonID = {R.id.textBtn1, R.id.textBtn2, R.id.textBtn3, R.id.textBtn4, R.id.textBtn5, R.id.textBtn6, R.id.textBtn7, R.id.textBtn8, R.id.textBtn9, R.id.textBtn10, R.id.textBtn11, R.id.textBtn12, R.id.textBtn13, R.id.textBtn14, R.id.textBtn15, R.id.textBtn16, R.id.textBtn17, R.id.textBtn18, R.id.textBtn19, R.id.textBtn20};
    Button[] ansButtonArray = new Button[4];
    int[] ansButtonID = {R.id.userInput1, R.id.userInput2, R.id.userInput3, R.id.userInput4};
    int ansIndex = 0;
    int ansSize;
    Boolean[] hintsIndex = {false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);
        rocket2 = findViewById(R.id.rocket2);
        btn_hint = findViewById(R.id.btn_hint);
        btn_hint.setOnClickListener(this);
        rocket2.setRotation(310);
        ufo2 = findViewById(R.id.ufo2);
        currentLevel2 = findViewById(R.id.currentLevel2);
        currentLevel2.setX(680);
        currentLevel2.setY(25);
        currentLevel2.setText("第 " + Integer.toString(DialogSetting.getLevel(getApplicationContext())) + " 關");
        Animation a = new TranslateAnimation(1000, Animation.ABSOLUTE - 2000,
                950, Animation.ABSOLUTE - 300);
        a.setDuration(11000);
        a.setRepeatCount(Animation.INFINITE);
        a.setRepeatMode(Animation.RESTART);
        rocket2.startAnimation(a);
        Animation b = new TranslateAnimation(-2000, 1100,
                90,  90);
        b.setDuration(12000);
        b.setRepeatCount(Animation.INFINITE);
        b.setRepeatMode(Animation.RESTART);
        ufo2.startAnimation(b);
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

        btn_hint.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_hint.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_hint.clearColorFilter();
                }
                return false;
            }
        });

        hintChance = findViewById(R.id.hintChance);
        hintChance.setText(String.valueOf(DialogSetting.getHints(getApplicationContext())));

        emoji = findViewById(R.id.emoji);
        type = findViewById(R.id.type);

        //define ans button
        for (int i = 0; i < ansButtonArray.length; i++) {
            ansButtonArray[i] = findViewById(ansButtonID[i]);
            ansButtonArray[i].setOnClickListener(this);
            ansButtonArray[i].setEnabled(false);
        }
        //define button
        for (int i = 0; i < buttonArray.length; i++) {
            buttonArray[i] = findViewById(buttonID[i]);
            buttonArray[i].setOnClickListener(this);
        }

        //get data from JSON file
        try {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                String question = jsonObject.getString("question");
                String answer = jsonObject.getString("answer");
                String type = jsonObject.getString("type");
                JSONArray buttonText = jsonObject.getJSONArray("buttonText");

                questionArrayList.add(question);
                answerArrayList.add(answer);
                typeArrayList.add(type);
                buttonTextArrayList.add(buttonText);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        levelText();

    }

    public String readJSON() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public void randomAllText() {
        ArrayList dummyList = new ArrayList();
        ArrayList dummyList2 = new ArrayList();
        for (int i = 0; i < buttonTextArrayList.size(); i++) {
            JSONArray buffer = (JSONArray) buttonTextArrayList.get(i);
            for (int j = 0; j < buffer.length(); j++) {
                try {
                    dummyList.add(buffer.get(j).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONArray buffer = (JSONArray) buttonTextArrayList.get(DialogSetting.getLevel(getApplicationContext()) - 1);
        for (int j = 0; j < buffer.length(); j++) {
            try {
                dummyList2.add(buffer.get(j).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Random r = new Random();
        int size = 0;
        if (DialogSetting.getLevel(getApplicationContext()) >= 10 && DialogSetting.getLevel(getApplicationContext()) < 20) {
            size = 5;
        }else if (DialogSetting.getLevel(getApplicationContext()) >= 20) {
            size = 10;
        }
        for (int i = 0; i < size; i++) {
            dummyList2.add(dummyList.get(r.nextInt(dummyList.size())));
        }
        int totalSize = dummyList2.size();
        for (int i = 0; i < totalSize; i++) {
            int index = r.nextInt(dummyList2.size());
            allTextArrayList.add(dummyList2.get(index));
            dummyList2.remove(index);
        }
    }

    public void onClick(View v){
        if (DialogSetting.effectsoundcontrol(getApplicationContext())){
            mediaPlayer.start();
        }
        //text button
        for (int i = 0; i < buttonArray.length && ansIndex < ansSize; i++) {
            if (v.getId() == buttonID[i]) {
                for (int j = 0; j < ansSize; j++) {
                    if (ansButtonArray[j].getText() == "") {
                        ansIndex = j;
                        ansButtonArray[ansIndex].setText(buttonArray[i].getText());
                        ansButtonArray[ansIndex].setEnabled(true);
                        buttonArray[i].setEnabled(false);
                        for (int k = ansIndex; k < ansButtonArray.length; k++) {
                            if (ansButtonArray[k].getText() == "") {
                                ansIndex = k;
                                break;
                            }
                            if (k == ansSize - 1) {
                                ansIndex = ansSize;
                            }
                        }
                        break;
                    }
                }
                //check ans
                if (ansIndex == ansSize) {
                    checkAns();
                }
                ansIndex = 0;
                break;
            }
        }
        //answer text button
        for (int i = 0; i < ansButtonArray.length; i++) {
            if (v.getId() == ansButtonID[i]) {
                for (int j = 0; j < buttonArray.length; j++) {
                    if (ansButtonArray[i].getText() == buttonArray[j].getText() && ansButtonArray[i].isEnabled()){
                        buttonArray[j].setEnabled(true);
                        ansButtonArray[i].setEnabled(false);
                    }
                }
                ansButtonArray[i].setText("");
                if (ansIndex > i) {
                    ansIndex = i;
                }
            }
        }
        //hint button
        if (v.getId() == R.id.btn_hint) {
            hints();
        }
    }

    public void hints() {
        ArrayList index = new ArrayList();
        String dummy = (String) answerArrayList.get(DialogSetting.getLevel(getApplicationContext()) - 1);
        for (int i = 0; i < ansSize; i++) {
            if (ansButtonArray[i].getText().toString() != "" && ansButtonArray[i].getText().toString().charAt(0) == dummy.charAt(i)) {
                //do nothing
            }else{
                index.add(i);   //ansButton text is not null and it's not match with the ans, than get index
            }
        }
        if (DialogSetting.getHints(getApplicationContext()) <= 1) { //player hints = 0, the button will disable
            btn_hint.setEnabled(false);
            btn_hint.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
        }
        if (index.size() > 0 && DialogSetting.getHints(getApplicationContext()) > 0) {
            DialogSetting.useHints(getApplicationContext());
            hintChance.setText(String.valueOf(DialogSetting.getHints(getApplicationContext())));
            Random r = new Random();
            int outputIndex = (int) index.get(r.nextInt(index.size()));
            //find used text
            for (int i = 0; i < buttonArray.length; i++) {
                if (buttonArray[i].getText() == ansButtonArray[outputIndex].getText()) {
                    buttonArray[i].setEnabled(true);
                }
            }
            //find repeat text
            for (int i = 0; i < ansSize; i++) {
                if (ansButtonArray[i].getText() != "") {
                    if (ansButtonArray[i].getText().charAt(0) == dummy.charAt(outputIndex) && !hintsIndex[i]) {
                        ansButtonArray[i].setText("");
                        break;
                    }
                }
            }
            ansButtonArray[outputIndex].setText(String.valueOf(dummy.charAt(outputIndex)));
            ansButtonArray[outputIndex].setEnabled(false);
            //disable a button when one ans is given
            for (int i = 0; i < buttonArray.length; i++) {
                if (buttonArray[i].getText().charAt(0) == dummy.charAt(outputIndex)) {
                    buttonArray[i].setEnabled(false);
                    break;
                }
            }
            hintsIndex[outputIndex] = true; //mark the hints ans location
            //check the number of ansbuttons are fill
            int counter = 0;
            for (int i = 0; i < ansSize; i++) {
                if (ansButtonArray[i].getText() != "") {
                    counter++;
                }
            }
            //check answer
            if (counter == ansSize) {
                checkAns();
            }
        }
    }

    public void checkAns() {
        //get all text from ansButton
        for (int j = 0; j < ansButtonArray.length; j++) {
            chinese += ansButtonArray[j].getText();
        }
        if (chinese.equals(answerArrayList.get(DialogSetting.getLevel(getApplicationContext()) - 1))) {  //when answer is correct
            DialogSetting.win_DialogManager(gameContent.this);
            DialogSetting.win_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    DialogSetting.tempHints(getApplicationContext());
                    hintChance.setText(String.valueOf(DialogSetting.getHints(getApplicationContext())));
                    DialogSetting.addLevel(getApplicationContext());
                    //clear data of previous level
                    allTextArrayList.clear();
                    levelText();
                    chinese="";
                    ansIndex = 0;
                    for (int j = 0; j < ansButtonArray.length; j++) {
                        ansButtonArray[j].setText("");
                        ansButtonArray[j].setEnabled(false);
                    }
                    for (int j = 0; j < buttonArray.length; j++) {
                        buttonArray[j].setEnabled(true);
                    }
                    for (int j = 0; j < hintsIndex.length; j++) {
                        hintsIndex[j] = true;
                    }
                }
            });
        }else { //when answer is incorrect
            shakeanimationandwrongeffect(emoji, -10, 10);
            chinese="";
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

    public void levelText(){
        if (DialogSetting.getLevel(getApplicationContext()) <= questionArrayList.size()) { //game not finish
            ArrayList buffer2 = new ArrayList();
            for (int i = 0; i < questionArrayList.size(); i++) {
                if (DialogSetting.getLevel(getApplicationContext()) == (i + 1)) {   //match the level and question
                    //answer button number
                    ansSize = ((String) answerArrayList.get(i)).length();
                    switch (ansSize) {
                        case 2:
                            ansButtonArray[2].setVisibility(View.GONE);
                            ansButtonArray[3].setVisibility(View.GONE);
                            break;
                        case 3:
                            ansButtonArray[2].setVisibility(View.VISIBLE);
                            ansButtonArray[3].setVisibility(View.GONE);
                            break;
                        default:
                            ansButtonArray[2].setVisibility(View.VISIBLE);
                            ansButtonArray[3].setVisibility(View.VISIBLE);
                            break;
                    }
                    //show level
                    currentLevel2.setText("第 " + Integer.toString(DialogSetting.getLevel(getApplicationContext())) + " 關");
                    //hints button setting
                    if (DialogSetting.getHints(getApplicationContext()) > 0) {
                        btn_hint.setEnabled(true);
                        btn_hint.clearColorFilter();
                    } else {
                        btn_hint.setEnabled(false);
                        btn_hint.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    }
                    emoji.setText((String) questionArrayList.get(i));
                    type.setText((String) typeArrayList.get(i));
                    //json data to button text
                    randomAllText();    //make the text random
                    for (int j = 0; j < allTextArrayList.size(); j++) {
                        buttonArray[j].setText((String) allTextArrayList.get(j));
                    }
                    //set difficulty
                    if (DialogSetting.getLevel(getApplicationContext()) >= 10 && DialogSetting.getLevel(getApplicationContext()) < 20) {
                        for (int j = 10; j < 15; j++) {
                            buttonArray[j].setVisibility(View.VISIBLE);
                        }
                        for (int j = 15; j < 20; j++) {
                            buttonArray[j].setVisibility(View.GONE);
                        }
                    } else if (DialogSetting.getLevel(getApplicationContext()) >= 20 && DialogSetting.getLevel(getApplicationContext()) <= 30) {
                        for (int j = 11; j < 20; j++) {
                            buttonArray[j].setVisibility(View.VISIBLE);
                        }
                    } else {
                        for (int j = 10; j < 20; j++) {
                            buttonArray[j].setVisibility(View.GONE);
                        }
                    }
                    break;
                }
            }
        }else{  //game finish
            Intent i = new Intent();
            i.setClass(gameContent.this, FinishPage.class);
            startActivity(i);
            finish();
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

