package com.example.weatherapp3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp3.CityPreferences;
import com.example.weatherapp3.R;
import com.example.weatherapp3.WeatherListAdapter;
import com.example.weatherapp3.WeatherProvider;
import com.example.weatherapp3.WeatherProviderListener;
import com.example.weatherapp3.weatherApi.WeatherApi;

public class WeatherFragment extends Fragment implements WeatherProviderListener{

    private WeatherViewModel weatherViewModel;
    WeatherApi weather;
    CityPreferences cityPreferences;
    WeatherListAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        weatherViewModel =
                ViewModelProviders.of(this).get(WeatherViewModel.class);
        View root = inflater.inflate(R.layout.fragment_weather, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        weatherViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        cityPreferences = new CityPreferences(getActivity());
//        WeatherProvider.getInstance().addListener(this,cityPreferences);


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
//        WeatherProvider.getInstance().addListener(this,cityPreferences);

    }

    @Override
    public void onResume() {

        super.onResume();
        WeatherProvider.getInstance().addListener(this,cityPreferences);
        //fixme  E/RecyclerView: No adapter attached; skipping layout
//        WeatherProvider.getInstance().addListener(this);
    }


    @Override
    public void upDateWeather(WeatherApi weatherApi) {

        RecyclerView recyclerView = getActivity().findViewById(R.id.rvWeatherList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        adapter = new WeatherListAdapter(getContext(), weatherApi);

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onPause() {
        super.onPause();
        WeatherProvider.getInstance().removeListener(this); //fixme

    }

}