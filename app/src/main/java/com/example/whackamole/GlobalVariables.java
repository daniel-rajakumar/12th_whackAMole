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

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

public class GlobalVariables extends Application {

    static GameActivity mainActivity;
    static ConstraintLayout constraintLayout, constraintLayout_score;
    private TextView textView_timer;

    static MediaPlayer mediaPlayer_background, mediaPlayer_victory, mediaPlayer_menu;

    static boolean mediaMute = false;

    public void setValues(GameActivity mainActivity){
        GlobalVariables.mainActivity = mainActivity;
        constraintLayout = mainActivity.findViewById(R.id.mainLayout);
        textView_timer = mainActivity.findViewById(R.id.textView_timer);
        constraintLayout_score = mainActivity.findViewById(R.id.constrainLayout_score);
    }


    public TextView getTextView_timer() {
        return textView_timer;
    }


    public static void makeStatusBarTransparent(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        WindowManager.LayoutParams winParams = activity.getWindow().getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        activity.getWindow().setAttributes(winParams);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    public static void muteMediaPlayer(MediaPlayer player, boolean mute){
        if (mute){
            player.setVolume(0f, 0f);
        } else {
            player.setVolume(1f, 1f);
        }
    }


}
