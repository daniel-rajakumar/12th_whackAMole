package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingScreen extends AppCompatActivity {
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        activity = this;



        ImageView imageView = findViewById(R.id.imageView_logo);


        GlobalVariables.makeStatusBarTransparent(this);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(4000);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setAlpha(0f);
                startActivity(new Intent(activity, MenuScreen.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        imageView.setAnimation(alphaAnimation);



    }
}