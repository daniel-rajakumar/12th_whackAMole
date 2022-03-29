package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        GlobalVariables.makeStatusBarTransparent(this);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MenuScreen.class));
//            startActivity(new Intent(this, GameActivity.class));
            finish();
        }, 2000);
    }
}