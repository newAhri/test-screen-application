package com.example.test_window_application.menu;

import android.os.AsyncTask;

import com.example.test_window_application.http.HTTPMethods;
import com.example.test_window_application.http.HTTPRequest;
import com.example.test_window_application.http.UrlLinks;

import java.util.ArrayList;
import java.util.List;

class SportsRequestTask extends AsyncTask<Void, Void, SportsResponse> {
    public RequestAsyncTaskCallback callback;

    public SportsRequestTask(RequestAsyncTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected SportsResponse doInBackground(Void... voids) {

        HTTPRequest request = new HTTPRequest.HTTPRequestBuilder(UrlLinks.LIST_SPORTS, HTTPMethods.GET)
                .build();
        return new SportsResponse(request.sendRequest());
    }

    @Override
    public void onPostExecute(SportsResponse sportsResponse) {
        if (callback != null) {
            callback.onPostExecute(sportsResponse);
        }
        super.onPostExecute(sportsResponse);
    }
}
