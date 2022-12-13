package com.example.test_window_application.details;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_window_application.R;

import java.util.Map;

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
        getActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true){
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
    public void onPostExecute(Map<String, String> details) { // сюда пердаем обьект класса DetailsResponse
        name = getView().findViewById(R.id.name);
        address = getView().findViewById(R.id.address);
        phone = getView().findViewById(R.id.phone);
        price = getView().findViewById(R.id.price);

        if (details.containsKey("name")){
            name.setText(details.get("name"));   // должно быть details.name -> this.name = details.get("name") в отдельном классе DetailsResponse
        }
        if (details.containsKey("address")){
            address.setText(details.get("address")); // должно быть details.address -> this.address = details.get("address") в отдельном классе DetailsResponse
        }
        if (details.containsKey("phone")){
            phone.setText(details.get("phone")); // должно быть details.phone -> this.phone = details.get("phone") в отдельном классе DetailsResponse
        }
        if (details.containsKey("price") && details.containsKey("currency")){
            String priceText = details.get("price").concat(" ").concat(details.get("currency")); // строку можно тоже формировать в классе DetailsResponse
            price.setText(priceText); // тут будет ..setText(details.getPriceString())
        }
    }
}

interface RequestAsyncTaskCallback {
    void onPostExecute(Map<String, String> list);
}


