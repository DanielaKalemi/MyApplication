package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;


public class activity_main extends AppCompatActivity implements View.OnClickListener {

    private CardView medicinePic, emergencyPic, gamePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining pics

        medicinePic = findViewById(R.id.medicine_pic);
        emergencyPic = findViewById(R.id.call_pic);
        gamePic = findViewById(R.id.game_pic);

        //add click listener to the pics

        medicinePic.setOnClickListener(this);
        emergencyPic.setOnClickListener(this);
        gamePic.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {

            case R.id.medicine_pic:
                i = new Intent(this, activity_medicine.class);
                startActivity(i);
                break;
            case R.id.call_pic:
                i = new Intent(this, activity_emergency.class);
                startActivity(i);
                break;
            case R.id.game_pic:
                i = new Intent(this, activity_game.class);
                startActivity(i);
                break;
            default:
                break;

        }
    }
}

