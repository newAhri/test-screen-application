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

    public String sendRequest() {                           // Не особо понимаю каким образом провести унифицирование - может
        String response = null;                             // не понял что требуется. Как я понял, нужно сделать метод более generic,
        try {                                               // чтобы собирать его как конструктор из конфигураций исходя из того,
            URL url = new URL(urlLink.concat(urlParameter));// какой у нас http метод. Прикинул, возможно, паттерн Стратегия здесь сработал бы?
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


    public static class HTTPRequestBuilder { // Подумал, фича билдера были бы кстати при множестве видов конфигурации http запроса.
        private HTTPMethods method;
        private String requestContent,
                urlLink,
                urlParameter = "";
        private LoginHeaders loginHeaders;


        public HTTPRequestBuilder(String urlLink, HTTPMethods method) {
            this.urlLink = urlLink;
            this.method = method;
        }

        public HTTPRequestBuilder setRequestContent(String requestContent) {    // Метод для определения тела http в формате json. Не используется, оставил
            this.requestContent = requestContent;                               // на случай, если надо будет отправлять запросы с телом json.
            return this;
        }

        public HTTPRequestBuilder setUrlParameter(String urlParameter) {
            this.urlParameter = urlParameter;
            return this;
        }

        public HTTPRequestBuilder setNextUrlParameter(String urlParameter) {        // Возможность добавлять >1 параметров в url ссылке. Можно преобразовать в один метод -
            this.urlParameter = this.urlParameter.concat("&").concat(urlParameter); // кол-во кода не уменьшится.
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
