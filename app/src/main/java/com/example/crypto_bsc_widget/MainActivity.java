package com.example.crypto_bsc_widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static com.example.crypto_bsc_widget.ThemeModifier.nextTheme;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    CardView card, card2;
    ImageView notificationImage, themeImage;
    boolean first = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





            SharedPreferences sharedPrefTheme = getSharedPreferences("theme", 0);
            String theme = sharedPrefTheme.getString("themeInfo", "");
            ThemeModifier.setBackgroundRelative(findViewById(R.id.home), theme);



        card = findViewById(R.id.manual_id);
        card2 = findViewById(R.id.background_id);
        notificationImage = findViewById(R.id.notification);
        themeImage = findViewById(R.id.theme);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BackgroundActivity.class);
                startActivity(intent);
            }
        });

        notificationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForegroundActivity.class);
                startActivity(intent);
            }
        });


        themeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences themePref = getSharedPreferences("theme", 0);
                SharedPreferences.Editor editor = themePref.edit();
                editor.putString("themeInfo", nextTheme);
                editor.apply();

                if(nextTheme.equals("day"))
                    Toast.makeText(MainActivity.this, "Day", Toast.LENGTH_SHORT).show();
                else if(nextTheme.equals("night"))
                    Toast.makeText(MainActivity.this, "Night", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(MainActivity.this, "Theme based on time", Toast.LENGTH_SHORT).show();

                ThemeModifier.setBackgroundRelative(findViewById(R.id.home), nextTheme);

            }
        });




    }









}