package com.example.test_window_application.login;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginResponse {
    private boolean success; // почему храним только success ? а где message ? :)
    // суть LoginResponse = хранить все поля которые нужны и которые приходят с серва

    public LoginResponse(String HTTPResponse) {
        this.success = getLoginSuccess(HTTPResponse);
    }

    public static boolean getLoginSuccess (String stringToJsonObject){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        String success = "";
        try {
            String jsonBody = parser.parse(stringToJsonObject).toString();
            jsonObject = new JSONObject(jsonBody);
            if (jsonObject.has("message")) {
                success = jsonObject.getString("message");
            }
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
        }
        return success.equals("Nice to see you back"); // проверяй request status code а не строку, если будет много языков устанешь писать проверку
    }

    public boolean getSuccess() {
        return success;
    }
}
