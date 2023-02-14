package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CharacterActivity extends AppCompatActivity {

    // Declare a variable to store the user's selected option
    String player_class = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_view_activity);

        // Get references to all the views in the layout
        EditText username = findViewById(R.id.userName);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        Button playButton = findViewById(R.id.play_button);
        TextView characterLimitText = findViewById(R.id.character_limit);
        TextView cancelConfirmationTextview = findViewById(R.id.confirmation_textview);
        Button cancelYes = findViewById(R.id.confirmation_yes);
        Button cancelNo = findViewById(R.id.confirmation_no);
        Button cancelButton = findViewById(R.id.cancel_button);
        TextView saved = findViewById(R.id.saved_textview);
        TextView error = findViewById(R.id.error);

        // Hide the cancel confirmation views and the saved textview initially
        cancelConfirmationTextview.setVisibility(View.INVISIBLE);
        cancelNo.setVisibility(View.INVISIBLE);
        cancelYes.setVisibility(View.INVISIBLE);
        saved.setVisibility(View.INVISIBLE);
        // Hide the character limit textview and error textview initially
        characterLimitText.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);

        // Set the toggle button text to show "Warrior" and "Mage"
        toggleButton.setTextOn("Warrior");
        toggleButton.setTextOff("Mage");

        // Add a listener to the toggle button to store the user's selection
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                player_class = "Warrior";
            } else {
                player_class = "Mage";
            }
        });

        playButton.setOnClickListener(view -> {
            // Get the string value of the user's entered username
            String userNameString = username.getText().toString();
            // Check if the username is less than or equal to 30 characters
            if (userNameString.length() <= 30) {
                // Insert user into the database
                DatabaseHelper dbHelper = new DatabaseHelper(this);
                boolean success = dbHelper.insertUser(userNameString, player_class);
                dbHelper.close();
            } else {
                // Display an error message if the username is too long
                characterLimitText.setVisibility(View.VISIBLE);
            }
        });

        cancelButton.setOnClickListener(View -> {
            // Display the cancel confirmation text and buttons
            cancelConfirmationTextview.setVisibility(android.view.View.VISIBLE);
            cancelNo.setVisibility(android.view.View.VISIBLE);
            cancelYes.setVisibility(android.view.View.VISIBLE);

            // Set a click listener for the 'yes' button
            cancelYes.setOnClickListener(View2 -> {
                // Create an Intent to return to the MainActivity
                Intent intent = new Intent(CharacterActivity.this, MainActivity.class);
                startActivity(intent);
            });

            // Set a click listener for the 'no' button
            cancelNo.setOnClickListener(View3 -> {
                // Hide the cancel confirmation text and buttons
                cancelConfirmationTextview.setVisibility(android.view.View.INVISIBLE);
                cancelNo.setVisibility(android.view.View.INVISIBLE);
                cancelYes.setVisibility(android.view.View.INVISIBLE);
            });
        });
    }
}