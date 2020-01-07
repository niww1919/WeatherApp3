package com.example.weatherapp3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp3.R;
import com.example.weatherapp3.WeatherListAdapter;
import com.example.weatherapp3.WeatherProvider;
import com.example.weatherapp3.WeatherProviderListener;
import com.example.weatherapp3.weatherApi.WeatherApi;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    WeatherApi weather;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //fixme how get weatherApi
        List<WeatherApi> list;
        list = new ArrayList<>();


        RecyclerView recyclerView = root.findViewById(R.id.rvWeatherList);
        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        WeatherListAdapter adapter = new WeatherListAdapter(getContext(), weather);

//        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //fixme decoration to cardview
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL);
        decoration.setDrawable(getActivity().getDrawable(R.drawable.weather_day_separator));
        recyclerView.addItemDecoration(decoration);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
//        Snackbar.make(getView(),"Good", Snackbar.LENGTH_SHORT).show();
        Toast.makeText(getContext(),"Good",Toast.LENGTH_SHORT).show();


    }

}