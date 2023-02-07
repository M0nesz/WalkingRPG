package com.example.walkinggame;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PlayerSaveReader {
    private String userName;
    private String selectedOption;

    public PlayerSaveReader() {
        JSONObject json = readPlayerSave();
        try {
            userName = json.getString("userName");
            selectedOption = json.getString("selectedOption");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject readPlayerSave() {
        JSONObject json = null;
        try {
            FileInputStream inputStream = new FileInputStream("player_save.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, StandardCharsets.UTF_8);
            json = new JSONObject(jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public String getUserName() {
        return userName;
    }

    public String getSelectedOption() {
        return selectedOption;
    }
}