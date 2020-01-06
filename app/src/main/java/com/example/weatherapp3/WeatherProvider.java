package com.example.weatherapp3;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherProvider {
    Timer timer;
    Handler handler = new Handler();

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

    public void addListener(WeatherProviderListener listener) {
        //todo add listener
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
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
                //todo load data

                handler.post(new Runnable() {//new treads
                    @Override
                    public void run() {

                        for (WeatherProviderListener listener : listeners) {
                            listener.upDateWeather("London", 23);
                        }

                    }
                });


            }
        }, 2000, 10000);
    }

    void stopLoadData() {
        if (timer == null) {
            timer.cancel();
        }
        listeners.clear();
    }
}
