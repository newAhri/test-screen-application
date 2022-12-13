package com.example.test_window_application.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static String getImageName(String sportName) { // В отдельный класс SportsResponse
        String result = null;
        switch (sportName) {
            case "American Football":
                result = "ic_baseline_sports_football_24";
                break;
            case "Basketball":
                result = "ic_baseline_sports_basketball_24";
                break;
            case "Cricket":
                result = "ic_baseline_sports_cricket_24";
                break;
            case "Mixed Martial Arts":
                result = "ic_baseline_sports_mma_24";
                break;
            case "Rugby League":
                result = "ic_baseline_sports_rugby_24";
                break;
            case "No details":
                result = "ic_baseline_error_outline_24";
                break;
        }
        return result;
    }

    public static ArrayList<String> getListOfSports(String stringToJsonArray) { // В отдельный класс SportsResponse
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

    public static Map<String, String> getListOfDetails(String stringToJsonObject) { // парсим сразу в обьект класса DetailsResponse см. DetailsFragment и эту функицю туда же

        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        Map<String,String> details = new HashMap<>();
        try {
            String jsonBody = parser.parse(stringToJsonObject).toString();
            jsonObject = new JSONObject(jsonBody);
            if (jsonObject.has("name")) {
                details.put("name", jsonObject.getString("name"));
            }
            if (jsonObject.has("address")) {
                details.put("address", jsonObject.getString("address"));
            }
            if (jsonObject.has("phone")) {
                details.put("phone", jsonObject.getString("phone"));
            }
            if (jsonObject.has("price")) {
                details.put("price", jsonObject.getString("price"));
            }
            if (jsonObject.has("currency")) {
                details.put("currency", jsonObject.getString("currency"));
            }
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
        }

        return details;
    }

    public static boolean getLoginSuccess (String stringToJsonObject){ // в отдельный класс LoginResponse
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        String success = "";
        try {
            String jsonBody = parser.parse(stringToJsonObject).toString();
            jsonObject = new JSONObject(jsonBody);
            if (jsonObject.has("success")) {
                success = jsonObject.getString("success");
            }
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
        }
        return success.equals("true");

    }


    public static String convertMapToJson(String username, String password) { // в отдельный класс LoginRequest
        Map<String, String> elements = new HashMap();
        elements.put("username", username);
        elements.put("password", password);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(elements);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
