package com.example.test_window_application;

import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
    public static String getImageName(String sportName) {
        String result = null;
        switch (sportName) {
            case "American Football":
                result = "ic_baseline_sports_football_24";
                break;
            case "Basketball":
                result = "ic_baseline_sports_basketball_24";
                break;
            case "Crciket":
                result = "ic_baseline_sports_cricket_24";
                break;
            case "Mixed Martial Arts":
                result = "ic_baseline_sports_mma_24";
                break;
            case "Rugby League":
                result = "ic_baseline_sports_rugby_24";
                break;
            case "Soccer - Other":
                result = "ic_baseline_sports_soccer_24";
                break;
        }
        return result;
    }

    public static ArrayList<String> getListOfSports(String result) {
        ArrayList<String> sports = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Arrays.stream(result.split("\t"))
                    .filter(line -> line.startsWith("\"name\""))
                    .forEach(l -> sports.add(l.substring(9, l.length() - 2)));
        }
        return sports;
    }

    public static ArrayList<String> getListOfDetails(String toString) {
        ArrayList<String> details = new ArrayList<>();
        int[] charAt = new int[]{9, 12, 10, 10, 13};
        String[] rawDetails = toString.split("\t");
        for (int i = 1; i < rawDetails.length; i++) {
            details.add(rawDetails[i].substring(charAt[i-1], rawDetails[i].length() - 2));
        }
        return details;
    }
}
