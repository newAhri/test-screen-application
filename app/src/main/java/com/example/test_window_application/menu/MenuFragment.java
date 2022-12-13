package com.example.test_window_application.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_window_application.R;
import com.example.test_window_application.utils.Utils;
import com.example.test_window_application.details.DetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment implements RequestAsyncTaskCallback {
    SportListRequestTask requestTask;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    private Context context;
    ArrayList<String> sportsToSave;

    public MenuFragment(){
        super(R.layout.menu_screen);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestTask = new SportListRequestTask((RequestAsyncTaskCallback) this);
        requestTask.execute();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("sports", sportsToSave);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState!=null) {
            sportsToSave = savedInstanceState.getStringArrayList("sports");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sportsToSave != null) {
            initRecycleView();
        }
    }

    @Override
    public void onPostExecute(ArrayList<String> sports) {
        sportsToSave = sports;
        initRecycleView();
    }

    public void initRecycleView(){
        recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        itemAdapter = new ItemAdapter(sportsToSave, context);
        recyclerView.setAdapter(itemAdapter);
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
        private List<String> sports;
        private Context context;

        public ItemAdapter(List<String> sports, Context context) {
            this.sports = sports;
            this.context = context;
        }

        @NonNull
        @Override
        public ItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.sport_option, parent, false);
            return new ItemAdapter.ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemAdapter.ItemHolder holder, int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);

            context = context.getApplicationContext();
            final String mSport = sports.get(position);
            holder.sportTitle.setText(mSport);
            holder.imageView.setImageResource(getImageId(context, mSport));
            holder.cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container_view, DetailsFragment.class, bundle)
                            .addToBackStack("menu")
                            .commit();
                }
            });

        }

        public int getImageId(Context context, String sportName) {
            return context
                    .getResources()
                    .getIdentifier("drawable/" + Utils.getImageName(sportName),
                            null,
                            context.getPackageName());
        }

        @Override
        public int getItemCount() {
            return sports.size();
        }


        public class ItemHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            TextView sportTitle;
            ImageView imageView;
            RelativeLayout relativeLayout;

            public ItemHolder(View itemView) {
                super(itemView);
                cardView = itemView.findViewById(R.id.card);
                sportTitle = itemView.findViewById(R.id.sport);
                imageView = itemView.findViewById(R.id.sportImage);
                relativeLayout = itemView.findViewById(R.id.layout);
            }
        }
    }
}
interface RequestAsyncTaskCallback {
    void onPostExecute(ArrayList<String> list);
}
