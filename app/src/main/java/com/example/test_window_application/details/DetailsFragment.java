package com.example.test_window_application.details;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_window_application.R;

import java.util.ArrayList;

public class DetailsFragment extends Fragment implements RequestAsyncTaskCallback {
    DetailsRequestTask detailsTask;
    TextView name,
            address,
            phone,
            price;

    public DetailsFragment(){
        super(R.layout.details_screen);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = requireArguments().getInt("position");
        detailsTask = new DetailsRequestTask((RequestAsyncTaskCallback) this, id);
        detailsTask.execute();
    }

    @Override
    public void onPostExecute(ArrayList<String> details) {
        name = getView().findViewById(R.id.name);
        address = getView().findViewById(R.id.address);
        phone = getView().findViewById(R.id.phone);
        price = getView().findViewById(R.id.price);

        name.setText(details.get(0));
        address.setText(details.get(1));
        phone.setText(details.get(2));
        String priceText = details.get(3) + " " + details.get(4);
        price.setText(priceText);
    }
}

interface RequestAsyncTaskCallback {
    void onPostExecute(ArrayList<String> list);
}


