package com.example.crypto_bsc_widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpinnerAdapter extends ArrayAdapter<Crypto> {
    LayoutInflater layoutInflater;


    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Crypto> coins) {

        super(context, resource, coins);

        System.out.println(coins);
        layoutInflater = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View entryView = layoutInflater.inflate(R.layout.coin_spinner_view, null, true);

        Crypto crypto = getItem(position);
        TextView textView = (TextView) entryView.findViewById(R.id.coinText);
        ImageView imageView = (ImageView) entryView.findViewById(R.id.coinImage);

        imageView.setImageResource(crypto.getLogoCoinId());
        textView.setText(crypto.getSymbol());

        return entryView;
    }



    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.coin_spinner_view, parent, false);



        Crypto crypto = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.coinText);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.coinImage);

        imageView.setImageResource(crypto.getLogoCoinId());
        textView.setText(crypto.getSymbol());

        return convertView;
    }


}
