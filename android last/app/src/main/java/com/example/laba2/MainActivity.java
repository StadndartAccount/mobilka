package com.example.laba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button enterBtn;
    TextView email_textview;
    TextView password_textview;

    String[] logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterBtn = findViewById(R.id.enter_btn);
        email_textview = findViewById(R.id.email_field);
        password_textview = findViewById(R.id.password_field);
        logs = getResources().getStringArray(R.array.users_logs);

        email_textview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                email_textview.setTextColor(getColor(R.color.customBlack));
            }
        });

        password_textview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                password_textview.setTextColor(getColor(R.color.customBlack));
            }
        });

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String log: logs) {
                    String[] email_pass = log.split(" ");

                    if(email_textview.getText().toString().trim().equals(email_pass[0]) && password_textview.getText().toString().equals(email_pass[1])) {
                        Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(myIntent);
                        return;
                    }
                }

                email_textview.setTextColor(getColor(R.color.customRed));
                password_textview.setTextColor(getColor(R.color.customRed));
            }
        });

    }
}