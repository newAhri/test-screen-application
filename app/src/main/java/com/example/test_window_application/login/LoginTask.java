package com.example.test_window_application.login;

import android.os.AsyncTask;

import com.example.test_window_application.IOUtils;

public class LoginTask extends AsyncTask<Void, Void, String> {
    public LoginAsyncTaskCallback callback;
    private final String username;
    private final String password;

    public LoginTask(LoginAsyncTaskCallback callback,
                     String username,
                     String password) {
        this.callback = callback;
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String response = IOUtils.sendPostRequest("https://engine.free.beeceptor.com/api/login", // https://engine.free.beeceptor.com/api/ в отдельный класс с константами или в IOUtils
                username,
                password);
        return response;
    }

    @Override
    protected void onPostExecute(String aString) {
        if (callback != null) {
            callback.onPostExecute(aString);
        }
        super.onPostExecute(aString);
    }
}
