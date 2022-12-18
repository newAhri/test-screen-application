package com.example.test_window_application.details;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DetailsResponse {
    private String name = "",
            address = "",
            phone = "",
            price = "";

    public DetailsResponse(String stringToJsonString) {
        if (stringToJsonString != null) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject;
            try {
                String jsonString = parser.parse(stringToJsonString).toString();
                jsonObject = new JSONObject(jsonString);
                if (jsonObject.has("name")) {
                    name = jsonObject.getString("name");
                }
                if (jsonObject.has("address")) {
                    address = jsonObject.getString("address");
                }
                if (jsonObject.has("phone")) {
                    phone = jsonObject.getString("phone");
                }
                if (jsonObject.has("price") && jsonObject.has("currency")) {
                    price = jsonObject.getString("price")
                            .concat(" ")
                            .concat(jsonObject.getString("currency"));
                }
            } catch (ParseException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrice() {
        return price;
    }
}
