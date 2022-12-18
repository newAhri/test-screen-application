package com.example.test_window_application.login;

import android.os.AsyncTask;

import com.example.test_window_application.http.HTTPMethods;
import com.example.test_window_application.http.HTTPRequest;
import com.example.test_window_application.http.UrlLinks;

public class LoginRequestTask extends AsyncTask<Void, Void, LoginResponse> {
    public LoginAsyncTaskCallback callback;
    private final LoginHeaders loginHeaders;


    public LoginRequestTask(LoginAsyncTaskCallback callback,
                            LoginHeaders loginHeaders) {
        this.callback = callback;
        this.loginHeaders = loginHeaders;
    }

    @Override
    protected LoginResponse doInBackground(Void... voids) {
        HTTPRequest request = new HTTPRequest.HTTPRequestBuilder(UrlLinks.LOGIN, HTTPMethods.POST)
                .setHeaders(loginHeaders)
                .build();
        String response = request.sendRequest();
        return new LoginResponse(response);
    }

    @Override
    protected void onPostExecute(LoginResponse loginResponse) {
        if (callback != null) {
            callback.onPostExecute(loginResponse);
        }
        super.onPostExecute(loginResponse);
    }
}
