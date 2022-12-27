package com.example.test_window_application.http;

public final class UrlParams {
    private UrlParams(){}

    public static final String SPORT_ID = "?sportId="; // в принципе можно и не выносить в отдельный класс, хранить сразу в UrlLinks.SPORT_DETAILS
                                                       // ибо SportDetails без ?sportId не имеют смысла
                                                       // c другой стороны есть ?sportId будет еще где то использоваться (в других методах), то есть смысл его вынести как ты это сделал
}