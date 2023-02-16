package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        Button backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(View ->{
            Intent intent = new Intent(MapActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}