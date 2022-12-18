package com.example.test_window_application.details;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_window_application.R;

public class DetailsFragment extends Fragment implements RequestAsyncTaskCallback {
    DetailsRequestTask detailsTask;
    TextView name,
            address,
            phone,
            price;

    public DetailsFragment() {
        super(R.layout.details_screen);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity()
                        .getSupportFragmentManager()
                        .popBackStack();
            }
        });

        int id = requireArguments().getInt("position");
        detailsTask = new DetailsRequestTask((RequestAsyncTaskCallback) this, id);
        detailsTask.execute();
    }

    @Override
    public void onPostExecute(DetailsResponse detailsResponse) {
        name = getView().findViewById(R.id.name);
        address = getView().findViewById(R.id.address);
        phone = getView().findViewById(R.id.phone);
        price = getView().findViewById(R.id.price);

        name.setText(detailsResponse.getName());
        address.setText(detailsResponse.getAddress());
        phone.setText(detailsResponse.getPhone());
        price.setText(detailsResponse.getPrice());
    }
}

interface RequestAsyncTaskCallback {
    void onPostExecute(DetailsResponse detailsResponse);
}


