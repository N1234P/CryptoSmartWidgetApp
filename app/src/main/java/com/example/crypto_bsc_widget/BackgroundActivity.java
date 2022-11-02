package com.example.crypto_bsc_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class BackgroundActivity extends AppCompatActivity {
    ImageView image;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_background);

        SharedPreferences sharedPrefTheme = getSharedPreferences("theme", 0);
        String theme = sharedPrefTheme.getString("themeInfo", "");
        ThemeModifier.setBackgroundRelative(findViewById(R.id.background), theme);

        image = findViewById(R.id.vicecity);
        image2 = findViewById(R.id.kingyna);
        image3 = findViewById(R.id.calmdarya);
        image4 = findViewById(R.id.foreverlost);
        image5 = findViewById(R.id.deepspace);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBackground("vice");
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBackground("king");
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBackground("darya");
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBackground("lost");
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBackground("space");
            }
        });

        setIcons();
    }

    public void setIcons() {
        ImageView icon1 = findViewById(R.id.c1);
        ImageView icon2 = findViewById(R.id.c2);
        ImageView icon3 = findViewById(R.id.c3);
        ImageView icon4 = findViewById(R.id.c4);
        ImageView icon5 = findViewById(R.id.c5);

        icon1.setVisibility(View.GONE);
        icon2.setVisibility(View.GONE);
        icon3.setVisibility(View.GONE);
        icon4.setVisibility(View.GONE);
        icon5.setVisibility(View.GONE);

        SharedPreferences sharedPref = getSharedPreferences("background", 0);
        String s = sharedPref.getString("background", "");

        switch(s) {
            case "king":
                icon2.setVisibility(View.VISIBLE);
                break;

            case "darya":
                icon3.setVisibility(View.VISIBLE);
                break;

            case "lost":
                icon4.setVisibility(View.VISIBLE);
                break;
            case "vice":
                icon1.setVisibility(View.VISIBLE);
                break;

            default:
                icon5.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void saveBackground(String s) {
        SharedPreferences sharedPref = getSharedPreferences("background", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("background", s);
        editor.apply();
        setIcons();

        Toast.makeText(this, "Saving Background...", Toast.LENGTH_SHORT).show();

    }

}