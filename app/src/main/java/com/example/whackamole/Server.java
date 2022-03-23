package com.example.whackamole;

import android.widget.Toast;

public class Server {
    GlobalVariables globalVariables = (GlobalVariables) GlobalVariables.mainActivity.getApplication();

    Server(){
        globalVariables.getButton_server().setOnClickListener(v -> {
            Toast.makeText(GlobalVariables.mainActivity, "Server", Toast.LENGTH_SHORT).show();
        });
    }
}
