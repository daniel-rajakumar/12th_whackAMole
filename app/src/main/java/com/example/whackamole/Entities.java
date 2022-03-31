package com.example.whackamole;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.TimerTask;

enum guideline_spawnArea {
    TOP(R.id.guideline_spawnArea_top),
    BOTTOM(R.id.guideline_spawnArea_bottom),
    LEFT(R.id.guideline_spawnArea_left),
    RIGHT(R.id.guideline_spawnArea_right);

    final int id;
    guideline_spawnArea(int id){ this.id = id; }
}

public class Entities {
    static float gridSize;
    float objectFloatingDepth = 0.60f;
    boolean isAnimating = false, isSelected;
    GlobalVariables globalVariables;
    ImageView imageView;

    static boolean[][] isAvailable = new boolean[(int) gridSize][(int) gridSize];
    private final int x, y;
    private final int ms;

    Entities(int x, int y, int ms){
        this.x = x;
        this.y = y;
        this.ms = ms;

        globalVariables = (GlobalVariables) GlobalVariables.mainActivity.getApplication();
        imageView = new ImageView(GlobalVariables.mainActivity);
        imageView.setId(View.generateViewId());
        imageView.setRotation(-45);
        isAvailable[x][y] = true;

        int image = getRandomImage();
        imageView.setImageResource(image);
        imageView.setTag(image);
        imageView.setAlpha(objectFloatingDepth);

        imageView.startAnimation(onAppearAnimation());

        imageView.setOnClickListener(this::onClickSetUp);

        GlobalVariables.constraintLayout.addView(imageView);


        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(GlobalVariables.constraintLayout);


        constraintSet.setVerticalBias  (imageView.getId(), y/(gridSize - 1));
        constraintSet.setHorizontalBias(imageView.getId(), x/(gridSize - 1));

        constraintSet.connect(imageView.getId(), ConstraintSet.TOP   , guideline_spawnArea.TOP.id   , ConstraintSet.TOP   );
        constraintSet.connect(imageView.getId(), ConstraintSet.LEFT  , guideline_spawnArea.LEFT.id  , ConstraintSet.LEFT  );
        constraintSet.connect(imageView.getId(), ConstraintSet.RIGHT , guideline_spawnArea.RIGHT.id , ConstraintSet.RIGHT );
        constraintSet.connect(imageView.getId(), ConstraintSet.BOTTOM, guideline_spawnArea.BOTTOM.id, ConstraintSet.BOTTOM);

        constraintSet.applyTo(GlobalVariables.constraintLayout);



        imageView.getLayoutParams().height = (int) (getScreenWidth(GlobalVariables.mainActivity) * 0.15);
        imageView.getLayoutParams().width = (int) (getScreenWidth(GlobalVariables.mainActivity) * 0.15); ;


    }

    class Hide extends TimerTask {

        @Override
        public void run() {
            GlobalVariables.mainActivity.runOnUiThread(() -> {
                if (!isSelected)
                    imageView.setAnimation(onDisappearAnimation());
            });
        }
    }


    void onClickSetUp(View view){
        ImageView imageView = (ImageView) view;
        if (imageView.getTag() == null) return;
        if (isAnimating) return;
        isSelected = true;
        isAnimating = true;
        if ((int) imageView.getTag() == R.drawable.plastic) {
            GameActivity.score.getAndIncrement();

            GlobalVariables.constraintLayout_score.removeAllViews();
//
            int n  = GameActivity.score.get();
            for (int i = 0; i < n; i++){
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(0, 150);
            ImageView _imageView = new ImageView(GlobalVariables.mainActivity);
            _imageView.setId(View.generateViewId());
            _imageView.setImageResource(R.drawable.plastic);
            _imageView.setLayoutParams(lp);

            GlobalVariables.constraintLayout_score.addView(_imageView);

            ConstraintSet constraintSet = new ConstraintSet();


            float val = ((100f / (n + 1)) * (i + 1)) / 100f;
            constraintSet.clone(GlobalVariables.constraintLayout_score);
            constraintSet.setHorizontalBias(_imageView.getId(), 0);
            constraintSet.setVerticalBias(_imageView.getId(), val);
            constraintSet.connect(_imageView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            constraintSet.connect(_imageView.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            constraintSet.connect(_imageView.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            constraintSet.connect(_imageView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);


            constraintSet.applyTo(GlobalVariables.constraintLayout_score);

        }

        } else if ((int) imageView.getTag() == R.drawable.nemo){
            GameActivity.decreesGameTime();
        }

        imageView.setAlpha(1f);
        imageView.startAnimation(clickAnimation());
    }

    AnimationSet clickAnimation(){
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setDuration(1000);
        animationSet.addAnimation(new TranslateAnimation(0f, 0f, 0f, -90f));
        animationSet.addAnimation(new AlphaAnimation(objectFloatingDepth, 1f));
        animationSet.addAnimation(new RotateAnimation(0.0f, -20f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    removeImage();
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        return animationSet;
    }

    TranslateAnimation getFloatAnimation(){
        TranslateAnimation translateAnimation = new TranslateAnimation(random(-15, -5), random(5, 15), 0f, 0f);
        translateAnimation.setDuration(750);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        return translateAnimation;
    }

    AnimationSet onAppearAnimation(){
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setDuration(ms/4);
        animationSet.addAnimation(new TranslateAnimation(-100f, 0f, 90f, -0f));
        animationSet.addAnimation(new AlphaAnimation(0f, objectFloatingDepth));

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                new java.util.Timer().schedule(new Hide(), ms);
                imageView.setAnimation(getFloatAnimation());
                isAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        return animationSet;
    }

    AnimationSet onDisappearAnimation(){
        AnimationSet animationSet = new AnimationSet(false);

        animationSet.setDuration(ms);
        animationSet.addAnimation(new TranslateAnimation(0f, -100f, 0f, 90f));
        animationSet.addAnimation(new AlphaAnimation(objectFloatingDepth, 0f));

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation1) { }

            @Override
            public void onAnimationEnd(Animation animation1) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    removeImage();
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation1) { }
        });

        return animationSet;
    }

    void removeImage(){
        GlobalVariables.constraintLayout.removeView(imageView);
        imageView.setImageDrawable(null);
        isAvailable[x][y] = false;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    int getRandomImage(){
        if (random(0, 3) == 0) {
            return R.drawable.nemo;
        }
        return R.drawable.plastic;
    }


    public static boolean isAvailable(int x, int y) {
        return isAvailable[x][y];
    }

    public static boolean isSpaceAvailable(){
        for (boolean[] i: isAvailable){
            for (boolean j: i){
                if (!j)
                    return true;
            }
        }

        return false;
    }

    public int random(int from, int to){
        return (int)(Math.random() * (to - from + 1)) + from;
    }
}
