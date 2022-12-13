package com.example.test_window_application.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest {
    boolean methodIsPost;
    String username,
            password,
            urlLink,
            urlParameter = "";
    HttpURLConnection client = null;


    public HTTPRequest(String urlLink, HTTPMethods method) {
        this.methodIsPost = method == HTTPMethods.POST;
        this.urlLink = urlLink;
    }

    public void setCredentials (String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUrlParameter (int urlParameter){
        this.urlParameter = String.valueOf(urlParameter);
    }

    public String sendRequest() {

        try {
            URL url = new URL(urlLink.concat(urlParameter));
            client = (HttpURLConnection) url.openConnection();
            if (methodIsPost){
                client.setRequestMethod("POST");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        client.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (methodIsPost){
            client.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            client.setDoOutput(true);

            try (OutputStream out = new BufferedOutputStream(client.getOutputStream())) {
                IOUtils.writeStream(out,
                        username,
                        password);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String response = null;

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()))) {

            response = IOUtils.readStream(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
