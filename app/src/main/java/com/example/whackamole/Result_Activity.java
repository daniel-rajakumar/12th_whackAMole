package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


        Button  button_playAgain = findViewById(R.id.button_playAgain),
                button_exit = findViewById(R.id.button_exit),
                button_menu = findViewById(R.id.button_menu);


        button_playAgain.setOnClickListener(v -> {
            finish();
            GlobalVariables.mediaPlayer_victory.stop();
            startActivity(new Intent(this, GameActivity.class));
        });

        button_menu.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(this, MenuScreen.class));
        });

        button_exit.setOnClickListener(v -> {
            finishAndRemoveTask();
        });


        textView.setText("Score: " + GameActivity.score);






        GlobalVariables.mediaPlayer_background.stop();

        GlobalVariables.mediaPlayer_victory = MediaPlayer.create(this, R.raw.victory);
        GlobalVariables.mediaPlayer_victory.start();
        GlobalVariables.muteMediaPlayer(GlobalVariables.mediaPlayer_victory, GlobalVariables.mediaMute);
    }

}