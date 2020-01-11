package com.example.weatherapp3;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.example.weatherapp3.weatherApi.WeatherApi;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherProvider {
    private static final String BASE_URL = "https://api.openweathermap.org/";
    private static final String KEY = "e83d0265c9865659af525e50e89b8edd";
    private Timer timer;
    private Handler handler = new Handler();
    CityPreferences cityPreferences;


    public static WeatherProvider instance = null;

    List<WeatherProviderListener> listeners;

    public static WeatherProvider getInstance() {
        instance = instance == null ? new WeatherProvider() : instance;
        return instance;
    }

    public WeatherProvider() {
        listeners = new ArrayList<>();
        startLoadData();
    }

    interface Openweather {
        @GET("data/2.5/forecast")
        Call<WeatherApi> getWeather(@Query("q") String q, @Query("appid") String key);

    }

    private WeatherApi getWeatherByRetrofit(String city) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<WeatherApi> call = retrofit.create(Openweather.class)
//                .getWeather(city + ",ru", KEY);
                .getWeather(city + ",ru", KEY);


        try {
            Response<WeatherApi> response = call.execute();
            return response.body();

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("getdata", "City incorrect");//fixme

            return null;
        }
    }



    public void addListener(WeatherProviderListener listener, CityPreferences cityPreferences) {
        //todo add listener
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
        this.cityPreferences = cityPreferences;
    }

    public void removeListener(WeatherProviderListener listener) {
        //todo add listener
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    private void startLoadData() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


//                WeatherApi weatherApi = getWeatherByRetrofit("Moscow");
                if (cityPreferences.getCity() == null) {
                    cityPreferences.setCity("Orenburg");

                }
                WeatherApi weatherApi = getWeatherByRetrofit(cityPreferences.getCity());

                if (weatherApi == null) {
                    return;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //todo load data

                        for (WeatherProviderListener listener : listeners) {
                            listener.upDateWeather(weatherApi);
                        }

                    }
                });


            }
        }, 200, 10000);
    }

    void stopLoadData() {
        if (timer == null) {
            timer.cancel();
        }
        listeners.clear();
    }
}
