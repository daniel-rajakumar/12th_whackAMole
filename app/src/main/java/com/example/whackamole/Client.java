package com.example.whackamole;

import android.widget.Toast;

public class Client {
    GlobalVariables globalVariables = (GlobalVariables) GlobalVariables.mainActivity.getApplication();

    Client(){
        globalVariables.getButton_client().setOnClickListener(v -> {
            Toast.makeText(GlobalVariables.mainActivity, "client", Toast.LENGTH_SHORT).show();
        });
    }
}
