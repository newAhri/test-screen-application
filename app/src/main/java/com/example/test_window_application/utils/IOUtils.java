package com.example.test_window_application.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

public class IOUtils {

    public static String readStream(BufferedReader in) throws IOException {
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }

    public static void writeStream(OutputStream out, String username, String password) {
        String output = Utils.convertMapToJson(username,password);
        try {
            out.write(output.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
