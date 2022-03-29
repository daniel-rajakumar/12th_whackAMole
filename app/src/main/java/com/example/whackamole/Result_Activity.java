package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Result_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        GlobalVariables.makeStatusBarTransparent(this);
        Glide.with(this).load(R.drawable.background_water).into((ImageView) findViewById(R.id.imageView_background));

        TextView textView = findViewById(R.id.textView_score);
        textView.setText("Score: " + GameActivity.score);

        GlobalVariables.mediaPlayer_victory = MediaPlayer.create(this, R.raw.victory);
        GlobalVariables.mediaPlayer_background.stop();
        GlobalVariables.mediaPlayer_victory.start();
    }

}