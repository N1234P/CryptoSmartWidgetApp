package com.example.crypto_bsc_widget;

import static com.example.crypto_bsc_widget.ForegroundService.status;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForegroundActivity extends AppCompatActivity {

    private EditText editTextInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);

        SharedPreferences sharedPrefTheme = getSharedPreferences("theme", 0);
        String theme = sharedPrefTheme.getString("themeInfo", "");
        ThemeModifier.setBackgroundLinear(findViewById(R.id.foreground), theme);

        editTextInput = findViewById(R.id.addressTrack);

    }


    @SuppressLint("SetTextI18n")
    public void startService(View v) {
        String input = editTextInput.getText().toString();
        Button button = findViewById(R.id.startTrack);
        button.setText("TRACKING...");

        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", input);

        startService(serviceIntent);
    }

    @SuppressLint("SetTextI18n")
    public void stopService(View v) {

        Button button = findViewById(R.id.startTrack);
        button.setText("Start Tracking");
        status = false;
        Intent serviceIntent = new Intent(this, ForegroundService.class);

        stopService(serviceIntent);
    }
}