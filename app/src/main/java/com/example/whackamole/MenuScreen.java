package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MenuScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        GlobalVariables.makeStatusBarTransparent(this);
        Glide.with(this).load(R.drawable.background_water).into((ImageView) findViewById(R.id.imageView_background));

        Button button = findViewById(R.id.button_play);

        button.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(this, GameActivity.class));
        });
    }
}