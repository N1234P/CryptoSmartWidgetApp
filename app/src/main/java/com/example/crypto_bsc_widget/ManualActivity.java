package com.example.crypto_bsc_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        SharedPreferences sharedPrefTheme = getSharedPreferences("theme", 0);
        String theme = sharedPrefTheme.getString("themeInfo", "");
        ThemeModifier.setBackgroundRelative(findViewById(R.id.manual), theme);
    }
}