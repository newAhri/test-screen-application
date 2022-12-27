package com.example.test_window_application.menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SportsResponse {
    private ArrayList<String> sports; // тут мы храним name ; id а не лист строк. лист создаем в функции getListOfSports
                                      // или создаем отдельный класс Sports где будет name ; id
                                      // и в SportsResponse храним ArrayList<Sports> sports;

    // суть SportsResponse = хранить все поля которые нужны и которые приходят с серва

    public SportsResponse(String HTTPResponse) {
        this.sports = getListOfSports(HTTPResponse);
    }

    public static ArrayList<String> getListOfSports(String stringToJsonArray) {
        JSONArray jsonArray;
        JSONObject jsonObject;
        ArrayList<String> sports = new ArrayList<>();
        try {
            jsonArray = new JSONArray(stringToJsonArray);
            for (int i = 0; i < jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                sports.add(jsonObject.getString("name"));
                // куда id делось ?
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sports;
    }

    public ArrayList<String> getSports() {
        return sports;
    }
}
