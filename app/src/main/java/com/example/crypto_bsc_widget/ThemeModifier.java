package com.example.crypto_bsc_widget;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Calendar;

public class ThemeModifier {

    public static String nextTheme;
    private static int count = 0;

    public static void setBackgroundRelative(RelativeLayout layout, String preference) {






        if (preference.equals("night")) {
            layout.setBackgroundResource(R.drawable.night_background);
            nextTheme = "day";
            count++;
            if(count % 2 == 0 && count != 0) {
                nextTheme = "";
            }
            return;

        } else if (preference.equals("day")) {
            layout.setBackgroundResource(R.drawable.morning_background);
            nextTheme = "night";
            count++;
            if(count % 2 == 0 && count != 0) {
                nextTheme = "";
            }
            return;
        }


        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if(hour > 16 || hour < 5) {
            layout.setBackgroundResource(R.drawable.night_background);
            nextTheme = "day";
        }
        else {
            layout.setBackgroundResource(R.drawable.morning_background);
            nextTheme = "night";
        }
    }

    public static void setBackgroundLinear(LinearLayout layout, String preference) {
        if (preference.equals("night")) {
            layout.setBackgroundResource(R.drawable.night_background);
            nextTheme = "day";
            count++;
            if(count % 2 == 0 && count != 0) {
                nextTheme = "";
            }
            return;

        } else if (preference.equals("day")) {
            layout.setBackgroundResource(R.drawable.morning_background);
            nextTheme = "night";
            count++;
            if(count % 2 == 0 && count != 0) {
                nextTheme = "";
            }
            return;
        }


        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if(hour > 16 || hour < 5) {
            layout.setBackgroundResource(R.drawable.night_background);
            nextTheme = "day";
        }
        else {
            layout.setBackgroundResource(R.drawable.morning_background);
            nextTheme = "night";
        }
    }







}

