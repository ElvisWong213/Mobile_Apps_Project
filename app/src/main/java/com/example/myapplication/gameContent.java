package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class gameContent extends AppCompatActivity implements View.OnClickListener{
    int checkpoint = 1;
    Button textBtn1,textBtn2,textBtn3,textBtn4,textBtn5,textBtn6,textBtn7,textBtn8,textBtn9,textBtn10,textBtn11,textBtn12,textBtn13,textBtn14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);


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










    }

    public void onClick(View v){
     if(checkpoint==1){
         
     }

    }
}