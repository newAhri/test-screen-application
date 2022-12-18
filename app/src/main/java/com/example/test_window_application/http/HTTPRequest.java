package com.example.test_window_application.http;

import com.example.test_window_application.login.LoginHeaders;
import com.example.test_window_application.utils.IOUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest {
    private HTTPMethods method;
    private String urlLink,
            requestContent,
            urlParameter = "";
    HttpURLConnection client = null;
    private LoginHeaders loginHeaders;

    private HTTPRequest(HTTPRequestBuilder builder) {
        this.urlLink = builder.urlLink;
        this.method = builder.method;
        this.requestContent = builder.requestContent;
        this.urlParameter = builder.urlParameter;
        this.loginHeaders = builder.loginHeaders;
    }

    public String sendRequest() {
        String response = null;
        try {
            URL url = new URL(urlLink.concat(urlParameter));
            client = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        client.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (method == HTTPMethods.POST) {
            client.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            client.setDoOutput(true);
            if (loginHeaders != null) {
                client.setRequestProperty("username", loginHeaders.getUsername());
                client.setRequestProperty("password", loginHeaders.getPassword());
            }
            try {
                client.setRequestMethod("POST");
                if (requestContent != null) {
                    OutputStream out = new BufferedOutputStream(client.getOutputStream());
                    IOUtils.writeStream(out,
                            requestContent);
                    out.flush();
                } else {
                    client.getOutputStream();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()))) {
            response = IOUtils.readStream(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    public static class HTTPRequestBuilder {
        private HTTPMethods method;
        private String requestContent,
                urlLink,
                urlParameter = "";
        private LoginHeaders loginHeaders;


        public HTTPRequestBuilder(String urlLink, HTTPMethods method) {
            this.urlLink = urlLink;
            this.method = method;
        }

        public HTTPRequestBuilder setRequestContent(String requestContent) {
            this.requestContent = requestContent;
            return this;
        }

        public HTTPRequestBuilder setUrlParameter(String urlParameter) {
            this.urlParameter = urlParameter;
            return this;
        }

        public HTTPRequestBuilder setNextUrlParameter(String urlParameter) {
            this.urlParameter = this.urlParameter.concat("&").concat(urlParameter);
            return this;
        }

        public HTTPRequestBuilder setHeaders(LoginHeaders loginHeaders) {
            this.loginHeaders = loginHeaders;
            return this;
        }

        public HTTPRequest build() {
            return new HTTPRequest(this);
        }
    }
}
