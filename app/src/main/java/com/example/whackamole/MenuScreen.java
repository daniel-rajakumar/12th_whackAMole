package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
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

        Button  button_play = findViewById(R.id.button_play),
                button_exit = findViewById(R.id.button_exit),
                button_mute = findViewById(R.id.button_mute);


        button_play.setOnClickListener(v -> {
            GlobalVariables.mediaPlayer_menu.stop();
            finish();
            startActivity(new Intent(this, GameActivity.class));
        });

        button_mute.setOnClickListener(v -> {
            Button button = (Button) v;
            if (button.getText().toString().equalsIgnoreCase("MUTE")){
                muteMedias(true);
                button.setText("UNMUTE");
            } else if (button.getText().toString().equalsIgnoreCase("UNMUTE")) {
                muteMedias(false);
                button.setText("MUTE");
            }

            GlobalVariables.muteMediaPlayer(GlobalVariables.mediaPlayer_menu, GlobalVariables.mediaMute);
        });

        setUpMusics();

        GlobalVariables.mediaPlayer_menu.start();
    }

    void setUpMusics(){
        GlobalVariables.mediaPlayer_menu = MediaPlayer.create(this, R.raw.menu);
        GlobalVariables.mediaPlayer_victory = MediaPlayer.create(this, R.raw.victory);
        GlobalVariables.mediaPlayer_background = MediaPlayer.create(this, R.raw.background);
    }

    void muteMedias(boolean mute){
        GlobalVariables.mediaMute = mute;
    }
}