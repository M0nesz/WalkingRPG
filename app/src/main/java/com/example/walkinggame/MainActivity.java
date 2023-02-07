package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button character_button = findViewById(R.id.character_button);
        Button action_button = findViewById(R.id.action_button);
        Button map_button = findViewById(R.id.map_button);
        Button inventory_button = findViewById(R.id.inventory_button);
        Button log_button = findViewById(R.id.log_button);


        action_button.setOnClickListener(view -> {

        });
        character_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CharacterActivity.class);
            startActivity(intent);
        });
        map_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        });
        inventory_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, InventoryViewActivity.class);
            startActivity(intent);
        });
        log_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LogActivity.class);
            startActivity(intent);
        });
    }
}