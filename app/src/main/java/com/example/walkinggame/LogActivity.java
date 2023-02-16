package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class LogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_activity);

        Button backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(View ->{
            Intent intent = new Intent(LogActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}