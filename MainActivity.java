package com.vishnu.walletv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String text = "0";
    private String text2 ;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    TextView display;
    EditText input;
    Button submit, buy;


    private int value = 0;
    private int value2 = 0;
    private String op = "Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.displayamt);
        input = findViewById(R.id.enternum);
        submit = findViewById(R.id.submitbtn);
        buy = findViewById(R.id.buy);
        text2 = "0";
        buy.setVisibility(View.INVISIBLE);
        display.setText(text2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc();

                saveData();

            }
        });


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buyfunc();
                saveData();
            }
        });

        loadData();
        updateViews();
    }
/*
    private void buyfunc() {
        String text2 = display.getText().toString();
        value = Integer.parseInt(text2.replaceAll("[\\D]",""));
        String text1 = input.getText().toString();
        if(TextUtils.isEmpty(text2)){
            value = 0;
            display.setText(value);
        }
        if(value > 0){
            int res = value - 10;
            text = Integer.toString(res);
            display.setText(text);

        }
        else{
            int checkText = R.string.initval;
            text = Integer.toString(checkText);
        }

    }
*/
    private void calc() {
        progressBar.setVisibility(View.VISIBLE);
        text = input.getText().toString();
        text2 = display.getText().toString();
        int checkText = R.string.initval;

        if(TextUtils.isEmpty(text2)){
            text2 = "0";
            display.setText("0");
            buy.setVisibility(View.INVISIBLE);

            return;
        }
        if(TextUtils.isEmpty(text)){
            input.setError("Field empty");
            buy.setVisibility(View.INVISIBLE);

            return;
        }

        else{
            text = input.getText().toString();
            text2 = display.getText().toString();
            value = Integer.parseInt(text.replaceAll("[\\D]",""));
            value2 = Integer.parseInt(text2.replaceAll("[\\D]",""));
            int res = value + value2;
            text = Integer.toString(res);

            display.setText(text);

        }

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, display.getText().toString());
        editor.apply();

        //Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews() {
        display.setText(text);
    }
}
