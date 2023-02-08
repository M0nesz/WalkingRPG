package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;


public class CharacterActivity extends AppCompatActivity {
    String selectedOption = "";
    private void saveDataToJsonFile(JSONObject data) {
        try {
            FileOutputStream fos = openFileOutput("player_save.json", MODE_PRIVATE);
            fos.write(data.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_view_activity);

        EditText userName = findViewById(R.id.userName);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setTextOn("Warrior");
        toggleButton.setTextOff("Mage");
        Button playButton = findViewById(R.id.play_button);
        TextView characterLimitText =  findViewById(R.id.character_limit);
        characterLimitText.setVisibility(View.INVISIBLE);

                toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedOption = "Warrior";
            } else {
                selectedOption = "Mage";
            }
        });

        playButton.setOnClickListener(view -> {
            int userNameLenght = userName.length();
            if (userNameLenght <= 30){
                String userNameSave = userName.getText().toString();
                final JSONObject data = new JSONObject();
                try {
                    data.put("userName", userNameSave);
                    data.put("selectedOption", selectedOption);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                saveDataToJsonFile(data);
                Intent intent = new Intent(CharacterActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else{
                characterLimitText.setVisibility(View.VISIBLE);
            }

        });
    }
}