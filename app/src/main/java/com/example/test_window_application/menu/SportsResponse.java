package com.example.test_window_application.menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SportsResponse {
    private ArrayList<String> sports;

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
