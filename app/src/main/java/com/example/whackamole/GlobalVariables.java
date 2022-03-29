package com.example.whackamole;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

public class GlobalVariables extends Application {

    static GameActivity mainActivity;
    private ImageView imageView_background;
    static ConstraintLayout constraintLayout, constraintLayout_score;
    private TextView textView_timer;
    private LinearLayout linearLayout_score;

    static MediaPlayer mediaPlayer_background, mediaPlayer_victory, mediaPlayer_menu;

    public void setValues(GameActivity mainActivity){
        GlobalVariables.mainActivity = mainActivity;
        imageView_background = mainActivity.findViewById(R.id.imageView_background);
        constraintLayout = mainActivity.findViewById(R.id.mainLayout);
        textView_timer = mainActivity.findViewById(R.id.textView_timer);
        linearLayout_score = mainActivity.findViewById(R.id.linearLayout_score);
        constraintLayout_score = mainActivity.findViewById(R.id.constrainLayout_score);
    }


    public TextView getTextView_timer() {
        return textView_timer;
    }


    public LinearLayout getLinearLayout_score() {
        return linearLayout_score;
    }


    public static void makeStatusBarTransparent(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        WindowManager.LayoutParams winParams = activity.getWindow().getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        activity.getWindow().setAttributes(winParams);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }


}
