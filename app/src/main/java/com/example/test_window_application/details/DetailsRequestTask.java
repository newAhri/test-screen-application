package com.example.test_window_application.details;

import android.os.AsyncTask;

import com.example.test_window_application.utils.HTTPMethods;
import com.example.test_window_application.utils.HTTPRequest;
import com.example.test_window_application.utils.UrlLinks;
import com.example.test_window_application.utils.Utils;

import java.util.Map;

class DetailsRequestTask extends AsyncTask<Void, Void, Map<String, String>> {
    public RequestAsyncTaskCallback callback;
    private int id;

    public DetailsRequestTask(RequestAsyncTaskCallback callback, int id) {
        this.callback = callback;
        this.id = id;
    }

    @Override
    protected Map<String, String> doInBackground(Void... voids) {

        HTTPRequest request = new HTTPRequest(UrlLinks.SPORT_DETAILS, HTTPMethods.GET);
        request.setUrlParameter(id+1);
        String response = request.sendRequest();
        return Utils.getListOfDetails(response);
    }


    @Override
    public void onPostExecute(Map<String, String> details) {
        if (callback != null) {
            callback.onPostExecute(details);
        }
        super.onPostExecute(details);
    }
}
