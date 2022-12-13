package com.example.test_window_application.menu;

import android.os.AsyncTask;

import com.example.test_window_application.utils.HTTPMethods;
import com.example.test_window_application.utils.HTTPRequest;
import com.example.test_window_application.utils.UrlLinks;
import com.example.test_window_application.utils.Utils;

import java.util.ArrayList;

class SportListRequestTask extends AsyncTask<Void, Void, ArrayList<String>> {
    public RequestAsyncTaskCallback callback;

    public SportListRequestTask(RequestAsyncTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {

        HTTPRequest request = new HTTPRequest(UrlLinks.LIST_SPORTS, HTTPMethods.GET);
        String response = request.sendRequest();
        return Utils.getListOfSports(response);
    }

    @Override
    public void onPostExecute(ArrayList<String> sports) {
        if (callback != null) {
            callback.onPostExecute(sports);
        }
        super.onPostExecute(sports);
    }
}
