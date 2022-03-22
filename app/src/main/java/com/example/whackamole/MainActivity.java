package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    GlobalVariables globalVariables;

    enum guideline_spawnArea {
        TOP(R.id.guideline_spawnArea_top),
        BOTTOM(R.id.guideline_spawnArea_bottom),
        LEFT(R.id.guideline_spawnArea_left),
        RIGHT(R.id.guideline_spawnArea_right);

        final int id;
        guideline_spawnArea(int id){ this.id = id; }
    }


    void run(){
        globalVariables = (GlobalVariables) getApplication();
        globalVariables.setValues(this);
        makeStatusBarTransparent();
        setUpBackground();
        Entities.gridSize = 3;

        for (int x = 0; x < Entities.gridSize; x++){
            for (int y = 0; y < Entities.gridSize; y++) {
                new Entities(x, y);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}