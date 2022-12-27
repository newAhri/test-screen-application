package com.example.test_window_application.utils;

public class Utils {
    public static String getImageName(String sportName) {       // в данном случае да, тут ок. обычно картинки приходят ссылкой с сервера и потом в апке ты их грузишь
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
}
