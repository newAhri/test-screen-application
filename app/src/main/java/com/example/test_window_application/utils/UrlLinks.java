package com.example.test_window_application.utils;

public final class UrlLinks {
    private UrlLinks(){}

    // baseURL ("https://engine.free.beeceptor.com/api") - повторяющуюся часть приянто выносить в отдельную пременную и комбенировать baseURL + method ("/login")
    public static final String LOGIN = "https://engine.free.beeceptor.com/api/login"; // должен быть новый линк (см. mail -> task.docx)
    public static final String LIST_SPORTS = "https://evafm-qa-api.azurewebsites.net/api/practice/getSports";
    public static final String SPORT_DETAILS = "https://engine.free.beeceptor.com/api/getSportDetails?sportId="; // должен быть новый линк (см. mail -> task.docx)
}
