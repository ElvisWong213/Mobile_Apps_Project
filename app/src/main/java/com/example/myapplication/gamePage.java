package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class gamePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(gamePage.this, MainActivity.class);
        startActivity(i);
    }
}