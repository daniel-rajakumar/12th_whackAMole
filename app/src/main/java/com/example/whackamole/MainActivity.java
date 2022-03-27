package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "networking";
    GlobalVariables globalVariables;


    void run(){
        globalVariables = (GlobalVariables) getApplication();
        globalVariables.setValues(this);



        makeStatusBarTransparent();
        setUpBackground();
        Entities.gridSize = 3;

//        for (int x = 0; x < Entities.gridSize; x++){
//            for (int y = 0; y < Entities.gridSize; y++) {
//                new Entities(x, y);
//            }
//        }

        Entities.isAvailable = new boolean[(int) Entities.gridSize][(int) Entities.gridSize];

        new java.util.Timer().schedule(
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

                                new Entities(x, y);
                            }
                        });
                    }

                }, 0, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        run();
    }

    void setUpBackground(){
        Glide.with(this).load(R.drawable.background_water).into(globalVariables.getImageView_background());
    }

    public void makeStatusBarTransparent() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        WindowManager.LayoutParams winParams = getWindow().getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        getWindow().setAttributes(winParams);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }


    public int random(int from, int to){
        return (int)(Math.random() * (to - from + 1)) + from;
    }


}