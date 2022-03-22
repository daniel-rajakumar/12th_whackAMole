package com.example.whackamole;

import android.app.Application;
import android.widget.ImageView;

public class GlobalVariables extends Application {

    static MainActivity mainActivity;
    private ImageView imageView_background;

    public void setValues(MainActivity mainActivity){
        GlobalVariables.mainActivity = mainActivity;
        imageView_background = mainActivity.findViewById(R.id.imageView_background);
    }

    public ImageView getImageView_background() {
        return imageView_background;
    }
}
