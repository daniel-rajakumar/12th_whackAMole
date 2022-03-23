package com.example.whackamole;

import android.app.Application;
import android.widget.Button;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GlobalVariables extends Application {

    static MainActivity mainActivity;
    private ImageView imageView_background;
    static ConstraintLayout constraintLayout;
    private Button button_client, button_server;

    public void setValues(MainActivity mainActivity){
        GlobalVariables.mainActivity = mainActivity;
        imageView_background = mainActivity.findViewById(R.id.imageView_background);
        constraintLayout = mainActivity.findViewById(R.id.mainLayout);
        button_client = mainActivity.findViewById(R.id.button_client);
        button_server = mainActivity.findViewById(R.id.button_server);
    }

    public ImageView getImageView_background() {
        return imageView_background;
    }

    public Button getButton_client() {
        return button_client;
    }

    public Button getButton_server() {
        return button_server;
    }
}
