package com.example.whackamole;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String TAG = "network";
    GlobalVariables globalVariables = (GlobalVariables) GlobalVariables.mainActivity.getApplication();

    Client() throws IOException {
        globalVariables.getButton_client().setOnClickListener(v -> {
            Toast.makeText(GlobalVariables.mainActivity, "You are client. ", Toast.LENGTH_SHORT).show();

            try {
                Log.d(TAG, "Client: Looking for host...");
                Socket socket = new Socket("10.0.0.126", 8080);
                Log.d(TAG, "Client: Connected");

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println("is it working");
                printWriter.flush();

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String str = bufferedReader.readLine();

                Toast.makeText(GlobalVariables.mainActivity, "Server: " + str, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }
}
