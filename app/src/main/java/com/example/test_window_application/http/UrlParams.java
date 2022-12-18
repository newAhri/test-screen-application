package com.example.test_window_application.http;

public final class UrlParams {
    private UrlParams(){}

    public static final String SPORT_ID = "?sportId="; // Наверное, хорошая идея и параметры держать в константах. Думаю, было бы лучше
}                                                      // размещать их как-то под ссылками, с которыми эти параметры используются, чтобы
                                                       // этот класс не раздувался со временем, только не знаю как.
