package com.example.whackamole;

import android.app.Application;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GlobalVariables extends Application {

    static MainActivity mainActivity;
    private ImageView imageView_background;
    static ConstraintLayout constraintLayout;

    public void setValues(MainActivity mainActivity){
        GlobalVariables.mainActivity = mainActivity;
        imageView_background = mainActivity.findViewById(R.id.imageView_background);
        constraintLayout = mainActivity.findViewById(R.id.mainLayout);
    }

    public ImageView getImageView_background() {
        return imageView_background;
    }
}
