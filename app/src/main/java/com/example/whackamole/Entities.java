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
    private static final String TAG = "test";
    static float gridSize;
    float objectFloatingDepth = 0.60f;
    boolean isAnimating = false, isSelected;
    GlobalVariables globalVariables;
    static int score;
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
        Log.d(TAG, "onClickSetUp: " + imageView.getTag() + " == " + R.drawable.nemo + " = " + ((int) imageView.getTag() == R.drawable.nemo));
        if ((int) imageView.getTag() == R.drawable.plastic){


//            Button button = new Button(GlobalVariables.mainActivity);
//
//            globalVariables.get


//            globalVariables.getTextView_score().setText(++score + "");
            /*

            ConstraintLayout.LayoutParams lp =
            new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(GlobalVariables.constraintLayout_score);

//            GlobalVariables.constraintLayout_score.addView(imageView);
//            _imageView.requestLayout();

//            _imageView.getLayoutParams().width = 10;
            score++;

//            constraintSet.clone(GlobalVariables.constraintLayout_score);

            Button button = new Button(GlobalVariables.mainActivity);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            button.setLayoutParams(params);
            button.setText("hello");
            button.setId(View.generateViewId());
            GlobalVariables.constraintLayout_score.addView(button);

            button.setLayoutParams(lp);
            constraintSet.clone(GlobalVariables.constraintLayout_score);
            constraintSet.connect(button.getId(), ConstraintSet.TOP   , ConstraintSet.PARENT_ID, ConstraintSet.TOP   , 0);
            constraintSet.connect(button.getId(), ConstraintSet.LEFT  , ConstraintSet.PARENT_ID, ConstraintSet.LEFT  , 0);
            constraintSet.connect(button.getId(), ConstraintSet.RIGHT , ConstraintSet.PARENT_ID, ConstraintSet.RIGHT , 0);
            constraintSet.connect(button.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
//            constraintSet.constrainDefaultWidth(button.getId(), ConstraintSet.MATCH_CONSTRAINT_WRAP);
            constraintSet.setHorizontalBias(button.getId(), random(0, 100)/100f);

//            constraintSet.constrainHeight(button.getId(), ConstraintSet.MATCH_CONSTRAINT_WRAP);
//            constraintSet.constrainWidth(button.getId(), ConstraintSet.MATCH_CONSTRAINT_WRAP);

            constraintSet.applyTo(GlobalVariables.constraintLayout_score);

//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            button.setLayoutParams(params);
            */



            score++;




//            globalVariables.getLinearLayout_score().removeViews(0, globalVariables.getLinearLayout_score().getChildCount() - 1);
            globalVariables.getLinearLayout_score().removeAllViews();
//            ConstraintSet constraintSet = new ConstraintSet();

//            LinearLayout.LayoutParams lp =
//                    new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.WRAP_CONTENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT);


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0, 1f);
            lp.setMargins(8,8,8,8);


//            constraintSet.clone(GlobalVariables.constraintLayout_score);
            for (int i = 0; i < score; i++){
                ImageView _imageView = new ImageView(GlobalVariables.mainActivity);
                _imageView.setId(View.generateViewId());
                _imageView.setImageResource(R.drawable.plastic);
//                _imageView.getLayoutParams().width = 32;
//                _imageView.getLayoutParams().height = 32;
//                _imageView.setMinimumWidth(32);
//                _imageView.setMaxWidth(32);
//                _imageView.setScaleX(0.2f);
//                _imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                _imageView.setLayoutParams(lp);
                globalVariables.getLinearLayout_score().addView( _imageView, 0);

                Log.d(TAG, MessageFormat.format(
                        "amount: {0} | score: {1}| bias: {2} "
                        , globalVariables.getLinearLayout_score().getChildCount(), score, 0));







//                Button _imageView = new Button(GlobalVariables.mainActivity);
//                _imageView.setId(View.generateViewId());
//                _imageView.setText("hello wrold");
//                _imageView.setLayoutParams(lp);
//                GlobalVariables.constraintLayout_score.addView(_imageView);



//            constraintSet.setVerticalBias  (imageView.getId(), y/(gridSize - 1));
//                float bias = 0.5f * i;
//
//                constraintSet.clone(GlobalVariables.constraintLayout_score);
//                constraintSet.setHorizontalBias(_imageView.getId(), 0);
//                constraintSet.connect(_imageView.getId(), ConstraintSet.TOP   , ConstraintSet.PARENT_ID, ConstraintSet.TOP   , 0);
//                constraintSet.connect(_imageView.getId(), ConstraintSet.LEFT  , ConstraintSet.PARENT_ID, ConstraintSet.LEFT  , 0);
//                constraintSet.connect(_imageView.getId(), ConstraintSet.RIGHT , ConstraintSet.PARENT_ID, ConstraintSet.RIGHT , 0);
//                constraintSet.connect(_imageView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
//
//                constraintSet.applyTo(GlobalVariables.constraintLayout_score);


//                ConstraintLayout.LayoutParams textParam = new ConstraintLayout.LayoutParams
//                        (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);


//                LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams
//                        (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

//                _imageView.setLayoutParams(textParam);
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
        switch (random(0, 1)){
            case 0: return R.drawable.nemo;
            case 1: return R.drawable.plastic;
            default: return -1;
        }
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
