package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class gameHint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_hint);

        String hint;
        TextView textview_hint;
        Button btn_close;

        textview_hint = findViewById(R.id.textview_hint);
        btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);

        switch(level){
            case 1: hint = "左"; break;
            case 2: hint = "打"; break;
            case 3: hint = "心"; break;
            case 4: hint = "走"; break;
            default: hint = "";
        }

        textview_hint.setText(hint);

    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_close:
                break;
        }
    }



}