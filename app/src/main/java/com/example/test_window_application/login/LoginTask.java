package com.example.test_window_application.login;

import android.os.AsyncTask;

import com.example.test_window_application.utils.HTTPMethods;
import com.example.test_window_application.utils.HTTPRequest;
import com.example.test_window_application.utils.UrlLinks;

public class LoginTask extends AsyncTask<Void, Void, String> {
    public LoginAsyncTaskCallback callback;
    private final String username; // в отдельный класс LoginRequest
    private final String password; // в отдельный класс LoginRequest

    public LoginTask(LoginAsyncTaskCallback callback,
                     String username,
                     String password) {
        this.callback = callback;
        this.username = username;
        this.password = password; // храним обьект класса а не отдельные поля
    }

    @Override
    protected String doInBackground(Void... voids) {

        HTTPRequest request = new HTTPRequest(UrlLinks.LOGIN, HTTPMethods.POST);
        request.setCredentials(username, password);  // передаем не отдельные поля, а обьект класса
        return request.sendRequest();
    }

    @Override
    protected void onPostExecute(String aString) {
        if (callback != null) {
            callback.onPostExecute(aString);
        }
        super.onPostExecute(aString);
    }
}
