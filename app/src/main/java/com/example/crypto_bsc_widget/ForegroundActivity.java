package com.example.crypto_bsc_widget;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ForegroundActivity extends AppCompatActivity {

    private EditText editTextInput;
    private ImageView refresh;

    private boolean serviceOn; // every time this variable gets changed, modify sharedprefs



    @Override
    protected void onCreate(Bundle savedInstanceState) {





        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);


        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPrefRecent = getSharedPreferences("recentForeground", 0);

                String contract = sharedPrefRecent.getString("contract", "");
                editTextInput.setText(contract);
            }
        });

        SharedPreferences sharedPrefTheme = getSharedPreferences("theme", 0);
        String theme = sharedPrefTheme.getString("themeInfo", "");
        ThemeModifier.setBackgroundLinear(findViewById(R.id.foreground), theme);

        editTextInput = findViewById(R.id.addressTrack);



        SharedPreferences sharedPref = getSharedPreferences("recentForeground", 0);
        serviceOn = sharedPref.getBoolean("status", false);

        Button button = findViewById(R.id.startTrack);
        button.setText(sharedPref.getString("button", "START TRACKING"));




    }






    @SuppressLint("SetTextI18n")
    public void startService(View v) {
        if(serviceOn)
            return;
        serviceOn = true;
        String input = editTextInput.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("recentForeground", 0);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("contract", input);
        edit.putBoolean("status", serviceOn);
        edit.putString("button", "TRACKING...");
        edit.apply();


        Button button = findViewById(R.id.startTrack);
        button.setText("TRACKING...");


        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", input);

        startService(serviceIntent);
    }

    @SuppressLint("SetTextI18n")
    public void stopService(View v) {

        serviceOn = false;

        SharedPreferences sharedPref = getSharedPreferences("recentForeground", 0);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putBoolean("status", serviceOn);
        edit.putString("button", "Start Tracking");
        edit.apply();

        Button button = findViewById(R.id.startTrack);

        button.setText("Start Tracking");
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }
}