package com.example.test_window_application.details;

import android.os.AsyncTask;

import com.example.test_window_application.http.HTTPMethods;
import com.example.test_window_application.http.HTTPRequest;
import com.example.test_window_application.http.UrlLinks;
import com.example.test_window_application.http.UrlParams;

class DetailsRequestTask extends AsyncTask<Void, Void, DetailsResponse> {
    public RequestAsyncTaskCallback callback;
    private int id;

    public DetailsRequestTask(RequestAsyncTaskCallback callback, int id) {
        this.callback = callback;
        this.id = id;
    }

    @Override
    protected DetailsResponse doInBackground(Void... voids) {
        HTTPRequest request = new HTTPRequest.HTTPRequestBuilder(UrlLinks.SPORT_DETAILS, HTTPMethods.GET)
                .setUrlParameter(UrlParams.SPORT_ID + (id + 1))
                .build();
        String response = request.sendRequest();
        return new DetailsResponse(response);
    }

    @Override
    public void onPostExecute(DetailsResponse detailsResponse) {
        if (callback != null) {
            callback.onPostExecute(detailsResponse);
        }
        super.onPostExecute(detailsResponse);
    }
}
