package com.example.crypto_bsc_widget;
import static com.example.crypto_bsc_widget.WidgetProvider.ACTION_REFRESH;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;


import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class AppWidgetConfig extends AppCompatActivity  {




    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private EditText editTextButton, editTextButton2, editTextButton3;
    private Drawable plusIcon;


    private static final String FILE_NAME = "profile1.txt";
    private static final String FILE_NAME2 = "profile2.txt";
    private static final String FILE_NAME3= "profile3.txt";


    private AutoCompleteTextView autoCompleteTxt;
    private ArrayAdapter<String> adapter;
    private int currentSelect = -1;


    private Spinner spinner;

    private SeekBar decimalBar;
    private TextView decimalNumber;

    private SeekBar refreshBar;
    private TextView refreshNumber;

    private Switch percentChange;

    private Dialog contractDialog;
    private TextView contractCloseIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_widget_config);

        SharedPreferences sharedPrefTheme = getSharedPreferences("theme", 0);
        String theme = sharedPrefTheme.getString("themeInfo", "");
        ThemeModifier.setBackgroundLinear(findViewById(R.id.configuration), theme);


        Intent configIntent = getIntent();
        Bundle extras = configIntent.getExtras();
        if(extras != null) {
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_CANCELED, resultValue);

        if(appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }


        initializeControls();

    }

    public void initializeControls() {





        editTextButton = findViewById(R.id.edit_text_button);
        editTextButton2 = findViewById(R.id.edit_text_button2);
        editTextButton3 = findViewById(R.id.edit_text_button3);




        autoCompleteTxt = findViewById(R.id.auto_complete);
        String[] profiles = {"Profile 1", "Profile 2", "Profile 3"};
        adapter = new ArrayAdapter<String>(this, R.layout.profile_spinner_view, profiles);
        autoCompleteTxt.setAdapter(adapter);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentSelect = position;
                load(view, parent);
            }
        });


        spinner = (Spinner) findViewById(R.id.coinSpinner);
        CoinInitializer coinInit = new CoinInitializer();
        SpinnerAdapter coinSpinner = new SpinnerAdapter(this, R.layout.coin_spinner_view, coinInit.getCoins());
        spinner.setAdapter(coinSpinner);

        decimalBar = (SeekBar) findViewById(R.id.decimalbar);
        decimalBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        decimalBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

        decimalNumber = (TextView) findViewById(R.id.decimal);
        decimalBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                decimalNumber.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        percentChange = (Switch) findViewById(R.id.percent_switch);
        percentChange.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

        refreshBar = (SeekBar) findViewById(R.id.refreshbar);
        refreshBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        refreshBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

        refreshNumber = (TextView) findViewById(R.id.refresh);
        refreshBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String text = String.valueOf(progress * 30) + "s";
                refreshNumber.setText(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void showContractPopup(View v) {
        contractDialog = new Dialog(this);
        contractDialog.setContentView(R.layout.contract_address_popup);




        contractDialog.show();

    }

    public void closeContractPopup(View v) {

        contractCloseIcon = (TextView) findViewById(R.id.exit_popup);
        contractDialog.dismiss();
    }



    public void confirmConfiguration(View v) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        Intent serviceIntent = new Intent(this, WidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));


        Intent clickIntent = new Intent(this, WidgetProvider.class);
        clickIntent.setAction(ACTION_REFRESH);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(this, 0, clickIntent, PendingIntent.FLAG_MUTABLE);

        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.widget);

        views.setRemoteAdapter(R.id.widget_stack_view, serviceIntent);
        views.setEmptyView(R.id.widget_stack_view, R.id.widget_empty_view);
        views.setPendingIntentTemplate(R.id.widget_stack_view, clickPendingIntent);


        appWidgetManager.updateAppWidget(appWidgetId, views);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);


        savePreferences();

        finish();
    }




    public void savePreferences() {
        Crypto crypto = (Crypto) spinner.getSelectedItem();

        SharedPreferences sharedPrefCoin = getSharedPreferences("existing" + appWidgetId, MODE_PRIVATE);
        SharedPreferences.Editor editorCoin = sharedPrefCoin.edit();
        String valueCoin = crypto != null ? crypto.getSymbol() : "";
        editorCoin.putString("coin", valueCoin);
        editorCoin.apply();


        SharedPreferences sharedPrefSettings = getSharedPreferences("settings" + appWidgetId, MODE_PRIVATE);
        SharedPreferences.Editor editorSettings = sharedPrefSettings.edit();
        editorSettings.putBoolean("percent", percentChange.isChecked());
        editorSettings.putString("decimals", decimalNumber.getText().toString());
        editorSettings.putString("refresh", refreshNumber.getText().toString());
        editorSettings.apply();


        SharedPreferences sharedPrefContract = getSharedPreferences("contractaddr" + appWidgetId, 0);
        SharedPreferences.Editor editor = sharedPrefContract.edit();
        editor.putString("CA1", editTextButton.getText().toString());
        editor.putString("CA2", editTextButton2.getText().toString());
        editor.putString("CA3", editTextButton3.getText().toString());
        editor.apply();
    }



    public void save(View v) {

        String text = editTextButton.getText().toString() + "~";
        String text2 = editTextButton2.getText().toString() + "~";
        String text3 = editTextButton3.getText().toString() + "~";
        FileOutputStream fos = null;

        try {
            if(currentSelect < 0) {
                Toast.makeText(this, "Selecting a profile to save to is required!", Toast.LENGTH_SHORT).show();
                return;
            }


            switch (currentSelect) {
                case 0:
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    break;
                case 1:
                    fos = openFileOutput(FILE_NAME2, MODE_PRIVATE);
                    break;

                default:
                    fos = openFileOutput(FILE_NAME3, MODE_PRIVATE);
                    break;
                }

                int profileNumber = currentSelect + 1;
                Toast.makeText(this, "Saving Config to Profile " + profileNumber, Toast.LENGTH_SHORT).show();

            fos.write(text.getBytes());
            fos.write(text2.getBytes());
            fos.write(text3.getBytes());
            editTextButton.getText().clear();
            editTextButton2.getText().clear();
            editTextButton3.getText().clear();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View v, AdapterView<?> parent) {
        if(currentSelect < 0)
            return;

        editTextButton.getText().clear();
        editTextButton2.getText().clear();
        editTextButton3.getText().clear();

        FileInputStream fis = null;


        try {
            if (currentSelect == 0) {
                fis = openFileInput(FILE_NAME);
            } else if (currentSelect == 1) {
                fis = openFileInput(FILE_NAME2);
            } else {
                fis = openFileInput(FILE_NAME3);
            }
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text);
            }
            displayConfig(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayConfig(String out) {
        if(out.contains("~")) {
            editTextButton.setText(out.substring(0, out.indexOf("~")));
            out = out.substring(out.indexOf("~") + 1);
        }
        if(out.contains("~")) {
            editTextButton2.setText(out.substring(0, out.indexOf("~")));
            out = out.substring(out.indexOf("~") + 1);
        }
        if(out.contains("~")) {
            editTextButton3.setText(out.substring(0, out.indexOf("~")));
        }
    }


}