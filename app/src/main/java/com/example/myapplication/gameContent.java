package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class gameContent extends AppCompatActivity implements View.OnClickListener{
    int checkpoint = 1;
    String chinese = "";
    TextView userinput;
    Button submit, clear,next;
    Button textBtn1,textBtn2,textBtn3,textBtn4,textBtn5,textBtn6,textBtn7,textBtn8,textBtn9,textBtn10,textBtn11,textBtn12,textBtn13,textBtn14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);

        userinput = findViewById(R.id.userInput);
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
                   String checkpoint1Ans="你你你";

                   if(checkpoint1Ans.equals(chinese)){
                       userinput.setText("good");
                       chinese="";
                       DialogSetting.win_DialogManager(gameContent.this);
                       checkpoint++;

                   }
                   else{
                       chinese="";
                       userinput.setText(chinese);}
               }
               break;
           case R.id.clear:
               userinput.setText("");
               break;

       }
     }

    }
