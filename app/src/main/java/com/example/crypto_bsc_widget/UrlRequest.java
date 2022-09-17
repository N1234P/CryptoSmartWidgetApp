package com.example.crypto_bsc_widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class UrlRequest {

    public static String apiCall(String req) {

        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(req);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            for(String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }

            reader.close();
            return result.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("API_FAIL");
            return "";

        }
    }


    public static Bitmap getLogo(String link) {
        try {
            URL url = new URL(link);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
