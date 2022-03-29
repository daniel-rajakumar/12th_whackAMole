package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import  java.util.*;

import com.bumptech.glide.Glide;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "networking";
    GlobalVariables globalVariables;
    static int time_second = 60;


    void run(){
        globalVariables = (GlobalVariables) getApplication();
        globalVariables.setValues(this);

        GlobalVariables.makeStatusBarTransparent(this);
        setUpBackground();
        Entities.gridSize = 3;

        Entities.isAvailable = new boolean[(int) Entities.gridSize][(int) Entities.gridSize];

        startTimer();

        new Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        GlobalVariables.mainActivity.runOnUiThread(() -> {
                            if (Entities.isSpaceAvailable()){
                                int x, y;
                                do {
                                    x = random(0, 2);
                                    y = random(0, 2);
                                } while (Entities.isAvailable(x, y));

                                new Entities(x, y, 2000);
                            }
                        });
                    }

                }, 0, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

//        startActivity(new Intent(this, LoadingScreen.class));
        run();
    }

    void setUpBackground(){
        Glide.with(this).load(R.drawable.background_water).into(globalVariables.getImageView_background());
    }

    static void decreesGameTime(){
        time_second -= 5;
    }


    void startTimer(){
        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                globalVariables.getTextView_timer().setText("Timer: " + --time_second);
            }
        },0, 1000);
    }


    public int random(int from, int to){
        return (int)(Math.random() * (to - from + 1)) + from;
    }



}