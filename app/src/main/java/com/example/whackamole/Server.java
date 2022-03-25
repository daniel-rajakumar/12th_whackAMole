package com.example.whackamole;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final String TAG = "network";
    GlobalVariables globalVariables = (GlobalVariables) GlobalVariables.mainActivity.getApplication();

    public static void main(String[] args) {

    }

    Server() throws IOException {
        globalVariables.getButton_server().setOnClickListener(v -> {
            Toast.makeText(GlobalVariables.mainActivity, "You are server. ", Toast.LENGTH_SHORT).show();

            try {
                ServerSocket serverSocket = new ServerSocket(8080);

                Log.d(TAG, "Server: Connected");
                Log.d(TAG, "Server: waiting for a client...");
                Socket socket = serverSocket.accept();
                Log.d(TAG, "Server: Client accepted!");

                Toast.makeText(GlobalVariables.mainActivity, "Client connected", Toast.LENGTH_SHORT).show();

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                String str = bufferedReader.readLine();
                Toast.makeText(GlobalVariables.mainActivity, "Client: " + str, Toast.LENGTH_SHORT).show();

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println("Daniel");
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
