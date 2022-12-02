package com.example.test_window_application;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class IOUtils {

    public static String sendPostRequest(String urlLink,
                                                    String username,
                                                    String password) {
        HttpURLConnection client = null;

        try {
            URL url = new URL(urlLink);// повторяется 1
            client = (HttpURLConnection) url.openConnection(); // повторяется 1
            client.setRequestMethod("POST");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        client.setRequestProperty("User-Agent", "Mozilla/5.0"); // повторяется 1
        client.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        client.setDoOutput(true);

        try (OutputStream out = new BufferedOutputStream(client.getOutputStream())) {
            writeStream(out, username, password);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String response = null;

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()))) {

            response = readStream(in);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String sendGetRequest(String urlLink) {
        HttpURLConnection client = null; // повторяется 2

        try {
            URL url = new URL(urlLink); // повторяется 2
            client = (HttpURLConnection) url.openConnection(); // повторяется 2
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        client.setRequestProperty("User-Agent", "Mozilla/5.0");// повторяется 2

        String response = null;

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()))) {

            response = IOUtils.readStream(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String readStream(BufferedReader in) throws IOException {
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }

    private static void writeStream(OutputStream out, String user, String pass) {
        String output = "{\"username\": " + "\"" + user + "\","
                + "\"password\": " + pass + "}"; // user/pass в класс где myclass.username / myclass.password и этот класс парсим в JSON. Строкой устанешь описываеть и нет консистентности
        try {
            out.write(output.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
