package com.example.crypto_bsc_widget;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Calendar;

public class ThemeModifier {

    public static String theme = "dark";


    public static void setTheme() {
        if(theme.equals("dark")) {
            theme = "light";
        }
        else if (theme.equals("light")) {
            theme = "time";
        }

        else {
            theme = "dark";
        }
    }


    public static void setBackgroundRelative(RelativeLayout layout, String preference) {
        theme = preference;
        if(theme.equals("dark")) {
            layout.setBackgroundResource(R.drawable.dark_background);
            return;
        }

        if(theme.equals("light")) {
            layout.setBackgroundResource(R.drawable.light_background);
            return;
        }



        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if(hour > 16 || hour < 5) {
            layout.setBackgroundResource(R.drawable.night_background);


        }
        else {
            layout.setBackgroundResource(R.drawable.morning_background);

        }
    }

    public static void setBackgroundLinear(LinearLayout layout, String preference) {
        if(theme.equals("dark")) {
            layout.setBackgroundResource(R.drawable.dark_background);
            return;
        }

        if(theme.equals("light")) {
            layout.setBackgroundResource(R.drawable.light_background);
            return;
        }

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if(hour > 16 || hour < 5) {
            layout.setBackgroundResource(R.drawable.night_background);

        }
        else {
            layout.setBackgroundResource(R.drawable.morning_background);
        }
    }







}

