package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import  java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.bumptech.glide.Glide;

public class GameActivity extends AppCompatActivity {

    GlobalVariables globalVariables;
    static int time_second;
    static AtomicInteger score = new AtomicInteger();
    Timer timer_pop;


    void run(){
        time_second = 30;

        globalVariables = (GlobalVariables) getApplication();
        globalVariables.setValues(this);

        // start music
        GlobalVariables.mediaPlayer_background = MediaPlayer.create(this, R.raw.background);
        GlobalVariables.mediaPlayer_background.start();
        GlobalVariables.muteMediaPlayer(GlobalVariables.mediaPlayer_background, GlobalVariables.mediaMute);

        GlobalVariables.makeStatusBarTransparent(this);

        // add gif in imageView
        Glide.with(this).load(R.drawable.background_water).into((ImageView) findViewById(R.id.imageView_background));

        Entities.gridSize = 3;

        Entities.isAvailable = new boolean[(int) Entities.gridSize][(int) Entities.gridSize];

        popEntitiesAtRandomPlaces();
        startTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        run();
    }

    static void decreesGameTime(){
        time_second -= 5;
    }

    void popEntitiesAtRandomPlaces(){
        timer_pop = new Timer();
        timer_pop.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        GlobalVariables.mainActivity.runOnUiThread(() -> {
                            for (int i = 0; i < random(1, 1); i++){
                                if (Entities.isSpaceAvailable()){
                                    int x, y;
                                    do {
                                        x = random(0, 2);
                                        y = random(0, 2);
                                    } while (Entities.isAvailable(x, y));

                                    new Entities(x, y, random(500, 2500));
                                }

                            }
                        });
                    }

                }, 0, 2000);
    }

    void startTimer(){
        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                globalVariables.getTextView_timer().setText("Timer: " + --time_second);
                if (time_second <= 0){
                    timer_pop.cancel();
                    finish();
                    startActivity(new Intent(GlobalVariables.mainActivity, Result_Activity.class));
                    cancel();
                }
            }
        },0, 1000);
    }


    public int random(int from, int to){
        return (int)(Math.random() * (to - from + 1)) + from;
    }



}