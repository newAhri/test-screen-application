package com.example.test_window_application.utils;

public class Utils {
    public static String getImageName(String sportName) {       // Решил оставить "mapper" здесь - нецелезообразно что-ли, если он будет
        String result = null;                                   // в SportsResponse и потом протаскивать объект через методы фрагмента, чтобы только
        switch (sportName) {                                    // вызвать один этот метод.
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
