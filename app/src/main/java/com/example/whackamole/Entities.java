package com.example.whackamole;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

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

    Entities(int x, int y){
//        guideline_spawnArea.top = findViewById(R.id.guideline_spawnArea_top);
        ImageView button = new ImageView(GlobalVariables.mainActivity);
        button.setId(View.generateViewId());
//        button.setRotationX(random(0, 360));
//        button.setRotationY(random(0, 360));
        button.setRotation(-45);
//        button.setRotationX(45f);
//        button.setRotationY(45f);
//        button.setText(String.format("(%s, %s)", x, y));
        button.setImageResource(R.drawable.nemo);
//        button.setAlpha(0.4f);
//        button.setScaleType(ImageView.ScaleType.FIT_XY);

        button.setAlpha(objectFloatingDepth);



        button.startAnimation(getFloatAnimation());


        button.setOnClickListener(e -> {
            button.setAlpha(1f);
//            Toast.makeText(GlobalVariables.mainActivity, String.format("(%s)", e.getAlpha()), Toast.LENGTH_LONG).show();
            AnimationSet animationSet = new AnimationSet(false);
            animationSet.setDuration(1000);
            animationSet.addAnimation(new TranslateAnimation(0f, 0f, 0f, -90f));
            animationSet.addAnimation(new AlphaAnimation(objectFloatingDepth, 1f));
            animationSet.addAnimation(new RotateAnimation(0.0f, -20f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));

//            button.postDelayed(() -> {
//                Toast.makeText(GlobalVariables.mainActivity, "hello", Toast.LENGTH_LONG).show();
//            }, 2000);
//            button.startAnimation(getFloatAnimation());




//            translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) {
//                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) button.getLayoutParams();
////                    params.topMargin -= 30f;
//                    params.leftMargin -= 0;
//                    button.setLayoutParams(params);
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) button.getLayoutParams();
////                    params.topMargin -= 0.001f;
////                    params.leftMargin -= 0;
//                    button.setLayoutParams(params);
//                }
//            });

            animationSet.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        GlobalVariables.constraintLayout.removeView(e);

                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        GlobalVariables.mainActivity.runOnUiThread(() -> {
                                            GlobalVariables.constraintLayout.addView(e);
                                            e.setAlpha(objectFloatingDepth);

                                            AnimationSet animationSet1 = new AnimationSet(false);
                                            animationSet1.setDuration(2000);

                                            animationSet1.addAnimation(new TranslateAnimation(-100f, 0f, 90f, -0f));
                                            animationSet1.addAnimation(new AlphaAnimation(0f, objectFloatingDepth));

                                            animationSet1.setAnimationListener(new Animation.AnimationListener() {
                                                @Override
                                                public void onAnimationStart(Animation animation1) { }

                                                @Override
                                                public void onAnimationEnd(Animation animation1) {
                                                    button.setAnimation(getFloatAnimation());
                                                }

                                                @Override
                                                public void onAnimationRepeat(Animation animation1) { }
                                            });

                                            e.startAnimation(animationSet1);


                                        });
                                    }

                                }, random(500, 3000));
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            button.startAnimation(animationSet);


        });

        ConstraintSet constraintSet = new ConstraintSet();
//        ConstraintLayout viewGroup = findViewById(R.id.mainLayout);

        GlobalVariables.constraintLayout.addView(button);

        constraintSet.clone(GlobalVariables.constraintLayout);

//        constraintSet.constrainPercentHeight(button.getId(), 0.2f);
//        constraintSet.constrainPercentWidth(button.getId(), 0.2f);


        constraintSet.setVerticalBias  (button.getId(), y/(gridSize - 1));
        constraintSet.setHorizontalBias(button.getId(), x/(gridSize - 1));

        constraintSet.connect(button.getId(), ConstraintSet.TOP   , guideline_spawnArea.TOP.id   , ConstraintSet.TOP   );
        constraintSet.connect(button.getId(), ConstraintSet.LEFT  , guideline_spawnArea.LEFT.id  , ConstraintSet.LEFT  );
        constraintSet.connect(button.getId(), ConstraintSet.RIGHT , guideline_spawnArea.RIGHT.id , ConstraintSet.RIGHT );
        constraintSet.connect(button.getId(), ConstraintSet.BOTTOM, guideline_spawnArea.BOTTOM.id, ConstraintSet.BOTTOM);

//        constraintSet.constrainDefaultHeight(button.getId(), 35);

        constraintSet.applyTo(GlobalVariables.constraintLayout);



        button.getLayoutParams().height = (int) (getScreenWidth(GlobalVariables.mainActivity) * 0.15);
        button.getLayoutParams().width = (int) (getScreenWidth(GlobalVariables.mainActivity) * 0.15); ;


//        new Thread(() -> {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            GlobalVariables.mainActivity.runOnUiThread(() -> linearLayout.removeView(button));
//        }).start();


    }

    TranslateAnimation getFloatAnimation(){
        TranslateAnimation translateAnimation = new TranslateAnimation(random(-15, -5), random(5, 15), 0f, 0f);
        translateAnimation.setDuration(750);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.REVERSE);
//        animationSet1.addAnimation(translateAnimation);
        return translateAnimation;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }


    public int random(int from, int to){
        return (int)(Math.random() * (to - from + 1)) + from;
    }
}
