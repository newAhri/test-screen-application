package com.example.test_window_application.menu;

import android.os.AsyncTask;

import com.example.test_window_application.IOUtils;
import com.example.test_window_application.Utils;

import java.util.ArrayList;

class SportListRequestTask extends AsyncTask<Void, Void, ArrayList<String>> {
    public RequestAsyncTaskCallback callback;

    public SportListRequestTask(RequestAsyncTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {

        String response = IOUtils.sendGetRequest("https://engine.free.beeceptor.com/api/getServices");
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
