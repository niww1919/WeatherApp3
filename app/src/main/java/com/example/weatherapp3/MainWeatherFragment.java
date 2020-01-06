package com.example.weatherapp3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp3.weatherApi.WeatherApi;


public class MainWeatherFragment extends Fragment implements WeatherProviderListener{

    public MainWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_weather, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        WeatherProvider.getInstance().addListener(this);
    }

    @Override
    public void upDateWeather(WeatherApi weatherApi) {
        //todo update data

        ((TextView)getActivity().findViewById(R.id.tvCityName)).setText(weatherApi.getCity().getName());
        ((TextView)getActivity().findViewById(R.id.tvData)).setText(String.valueOf(weatherApi.getList().get(0).getDtTxt()));
//        ((TextView)getActivity().findViewById(R.id.tvTemp)).setText(String.valueOf(weatherApi.getList().indexOf(0)));
        ((TextView)getActivity().findViewById(R.id.tvTemp)).setText(String.valueOf(weatherApi.getList().get(0).getMain().getTemp()));
        ((TextView)getActivity().findViewById(R.id.tvIcon)).setText(weatherApi.getList().get(0).getWeather().get(0).getIcon());
        ((TextView)getActivity().findViewById(R.id.tvClouds)).setText("Cloudiness, % "+ weatherApi.getList().get(0).getClouds().getAll());
        ((TextView)getActivity().findViewById(R.id.tvWind)).setText("Wind speed, m/s "+ weatherApi.getList().get(0).getWind().getSpeed());
//        ((TextView)getActivity().findViewById(R.id.tvRain)).setText("Rain, mm "+ weatherApi.getList().get(0).getRain().get3h());//fixme is null
//        ((TextView)getActivity().findViewById(R.id.tvSnow)).setText("Snow "+ weatherApi.getList().get(0).getSnow().get3h());
    }

    @Override
    public void onPause() {
        super.onPause();
        WeatherProvider.getInstance().removeListener(this);
    }
}
