package com.example.weatherapp3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp3.weatherApi.WeatherApi;
import com.squareup.picasso.Picasso;


public class MainWeatherFragment extends Fragment implements WeatherProviderListener{

    CityPreferences cityPreferences;

    public MainWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cityPreferences = new CityPreferences(getActivity());
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
        WeatherProvider.getInstance().addListener(this, cityPreferences);
    }

    @Override
    public void upDateWeather(WeatherApi weatherApi) {
        //todo update data

        ((TextView)getActivity().findViewById(R.id.tvCityName)).setText(weatherApi.getCity().getName());
        ((TextView)getActivity().findViewById(R.id.tvData)).setText(weatherApi.getList().get(0).getDtTxt().substring(5,10));
        ((TextView)getActivity().findViewById(R.id.tvTemp)).setText(String.valueOf(weatherApi.getList().get(0).getMain().getTemp()-273).substring(0,4));

        Picasso.get()
                .load("http://openweathermap.org/img/wn/"+weatherApi.getList().get(0).getWeather().get(0).getIcon() +"@2x.png")
                .resize(200,200)
                .into((ImageView)getActivity().findViewById(R.id.ivIcon));

        ((TextView)getActivity().findViewById(R.id.tvClouds)).setText("Cloudiness, % "+ weatherApi.getList().get(0).getClouds().getAll());
        ((TextView)getActivity().findViewById(R.id.tvWind)).setText("Wind speed, m/s "+ weatherApi.getList().get(0).getWind().getSpeed());
//        ((TextView)getActivity().findViewById(R.id.tvRain)).setText("Rain, mm "+ weatherApi.getList().get(0).getRain().get3h());//fixme is null
//        ((TextView)getActivity().findViewById(R.id.tvSnow)).setText("Snow "+ weatherApi.getList().get(0).getSnow().get3h());
    }


    @Override
    public void onPause() {
        super.onPause();
        WeatherProvider.getInstance().removeListener(this); //fixme
    }
}
