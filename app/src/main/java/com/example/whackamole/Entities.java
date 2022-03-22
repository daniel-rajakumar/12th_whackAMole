package com.example.whackamole;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class Entities {
    static float gridSize;

    Entities(int x, int y){
//        guideline_spawnArea.top = findViewById(R.id.guideline_spawnArea_top);
        Button button = new Button(GlobalVariables.mainActivity);
        button.setId(View.generateViewId());
        button.setText(String.format("(%s, %s)", x, y));
        ConstraintSet constraintSet = new ConstraintSet();
//        ConstraintLayout viewGroup = findViewById(R.id.mainLayout);
        GlobalVariables.constraintLayout.addView(button);

        constraintSet.clone(GlobalVariables.constraintLayout);

        constraintSet.setVerticalBias  (button.getId(), y/(gridSize - 1));
        constraintSet.setHorizontalBias(button.getId(), x/(gridSize - 1));

        constraintSet.connect(button.getId(), ConstraintSet.TOP   , MainActivity.guideline_spawnArea.TOP.id   , ConstraintSet.TOP   );
        constraintSet.connect(button.getId(), ConstraintSet.LEFT  , MainActivity.guideline_spawnArea.LEFT.id  , ConstraintSet.LEFT  );
        constraintSet.connect(button.getId(), ConstraintSet.RIGHT , MainActivity.guideline_spawnArea.RIGHT.id , ConstraintSet.RIGHT );
        constraintSet.connect(button.getId(), ConstraintSet.BOTTOM, MainActivity.guideline_spawnArea.BOTTOM.id, ConstraintSet.BOTTOM);

        constraintSet.applyTo(GlobalVariables.constraintLayout);




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
