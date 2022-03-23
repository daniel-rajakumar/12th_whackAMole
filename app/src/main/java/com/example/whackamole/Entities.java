package com.example.whackamole;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

    Entities(int x, int y){
//        guideline_spawnArea.top = findViewById(R.id.guideline_spawnArea_top);
        ImageView button = new ImageView(GlobalVariables.mainActivity);
        button.setId(View.generateViewId());
//        button.setText(String.format("(%s, %s)", x, y));
        button.setImageResource(R.drawable.nemo);
//        button.setScaleType(ImageView.ScaleType.FIT_XY);

        button.setOnClickListener(e -> {
            GlobalVariables.constraintLayout.removeView(e);

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            // your code here
                            GlobalVariables.mainActivity.runOnUiThread(() -> {
                                GlobalVariables.constraintLayout.addView(e);
                            });
                        }
                    },
                    5000
            );
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



        button.requestLayout();
        button.getLayoutParams().height = 300;
        button.getLayoutParams().width = 300;


//        new Thread(() -> {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            GlobalVariables.mainActivity.runOnUiThread(() -> linearLayout.removeView(button));
//        }).start();


    }
}
