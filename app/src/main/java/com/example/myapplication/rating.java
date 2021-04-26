package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class rating extends AppCompatActivity {
    RatingBar ratingBar;
    ImageView btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar = findViewById(R.id.rating_bar);
        btSubmit = findViewById(R.id.ratebtnswitch);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), "已送出評分", Toast.LENGTH_SHORT).show();
                Intent it = new Intent();
                it.setClass(rating.this,gamePage.class);
                startActivity(it);
            }
        });
    }
}