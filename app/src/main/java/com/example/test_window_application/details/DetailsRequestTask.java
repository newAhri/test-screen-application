package com.example.test_window_application.details;

import android.os.AsyncTask;

import com.example.test_window_application.IOUtils;
import com.example.test_window_application.Utils;

import java.util.ArrayList;

class DetailsRequestTask extends AsyncTask<Void, Void, ArrayList<String>> {
    public RequestAsyncTaskCallback callback;
    private int id;

    public DetailsRequestTask(RequestAsyncTaskCallback callback, int id) {
        this.callback = callback;
        this.id = id;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {

        String response = IOUtils.sendGetRequest("https://engine.free.beeceptor.com/api/getSportDetails?sportId=" + (id+1));
        return Utils.getListOfDetails(response);
    }


    @Override
    public void onPostExecute(ArrayList<String> details) {
        if (callback != null) {
            callback.onPostExecute(details);
        }
        super.onPostExecute(details);
    }
}
