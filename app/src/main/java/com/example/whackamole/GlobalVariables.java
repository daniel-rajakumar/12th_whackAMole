package com.example.whackamole;

import android.app.Application;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GlobalVariables extends Application {

    static MainActivity mainActivity;
    private ImageView imageView_background;
    static ConstraintLayout constraintLayout;
    private TextView textView_timer, textView_score;

    public void setValues(MainActivity mainActivity){
        GlobalVariables.mainActivity = mainActivity;
        imageView_background = mainActivity.findViewById(R.id.imageView_background);
        constraintLayout = mainActivity.findViewById(R.id.mainLayout);
        textView_timer = mainActivity.findViewById(R.id.textView_timer);
        textView_score = mainActivity.findViewById(R.id.textView_score);
    }


    public ImageView getImageView_background() {
        return imageView_background;
    }

    public TextView getTextView_timer() {
        return textView_timer;
    }

    public TextView getTextView_score() {
        return textView_score;
    }
}
