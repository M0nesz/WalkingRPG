package com.example.walkinggame;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void characterActivity(){
        Intent intent = new Intent(MainActivity.this, CharacterActivity.class);
        startActivity(intent);
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button character_button = findViewById(R.id.character_button);
        Button action_button = findViewById(R.id.action_button);
        Button map_button = findViewById(R.id.map_button);
        Button inventory_button = findViewById(R.id.inventory_button);
        Button log_button = findViewById(R.id.log_button);
        TextView kecske = findViewById(R.id.name);
            // Create an instance of the DatabaseHelper class
        DatabaseHelper dbHelper = new DatabaseHelper(this);

            // Get the user data from the database
        Cursor userDataCursor = dbHelper.getData(1);


            // Check if the Cursor object contains any data
        if (userDataCursor != null && userDataCursor.moveToFirst()) {
                // Extract the username and player_class values from the Cursor object
            int usernameColumnIndex = userDataCursor.getColumnIndex("username");
            int playerClassColumnIndex = userDataCursor.getColumnIndex("player_class");

            if (usernameColumnIndex >= 0 && playerClassColumnIndex >= 0) {
                String username = userDataCursor.getString(usernameColumnIndex);
                String playerClass = userDataCursor.getString(playerClassColumnIndex);

                    // Update the TextView with the retrieved data
                kecske.setText( username + "\n" + playerClass);
            } else {
                    // If the Cursor does not contain the necessary columns, display an error message
                kecske.setText("Error: database schema does not match expected schema");
                Log.e("MainActivity", "Error: database schema does not match expected schema");

            }
        } else {
                // If the Cursor is empty, display an error message
            kecske.setText("Error: no data found in database");
            Log.e("MainActivity", "Error: no data found in database");
            characterActivity();
        }

        // Close the Cursor and the database connection
        assert userDataCursor != null;
        userDataCursor.close();
        dbHelper.close();

        action_button.setOnClickListener(view -> {

        });
        character_button.setOnClickListener(view -> {
            characterActivity();
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