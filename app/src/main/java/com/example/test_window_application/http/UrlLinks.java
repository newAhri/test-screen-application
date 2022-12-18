package com.example.test_window_application.http;

public final class UrlLinks {
    private UrlLinks(){}

    public static final String BASE_URL = "https://evafm-qa-api.azurewebsites.net/api/practice";
    public static final String LOGIN = BASE_URL + "/login";
    public static final String LIST_SPORTS = BASE_URL + "/getSports";
    public static final String SPORT_DETAILS = BASE_URL + "/getSportDetails";
}
