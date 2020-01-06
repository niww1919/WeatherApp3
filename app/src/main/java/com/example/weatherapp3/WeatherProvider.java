package com.example.weatherapp3;

import android.os.Handler;
import android.util.Log;

import com.example.weatherapp3.weatherApi.WeatherApi;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    private WeatherApi getWeather(String city) {
        WeatherApi weatherApi = null; //fixme i need init weathet api??

        try {
//            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + ",ru&appid=e83d0265c9865659af525e50e89b8edd");
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q=" + city + ",ru&appid=e83d0265c9865659af525e50e89b8edd");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder rwData = new StringBuilder(1024);
            String temp;
            while ((temp = reader.readLine()) != null) {
                rwData.append(temp).append("\n");
            }
            reader.close();
            Log.i("rwData", rwData.toString());
            Gson gson = new Gson();
            weatherApi = gson.fromJson(rwData.toString(), WeatherApi.class);
            Log.i("api", weatherApi.getCity().getName());
            return weatherApi;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static WeatherApi getWeatherByOkHttp(String city) {
//        WeatherApi weatherApi = null;
        try {
//            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+",ru&appid=e83d0265c9865659af525e50e89b8edd");
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q="+city+",ru&appid=e83d0265c9865659af525e50e89b8edd");
//            https://samples.openweathermap.org/data/2.5/forecast?q=+city+,ru&appid=b6907d289e10d714a6e88b30761fae22
//            https://api.openweathermap.org/data/2.5/forecast?q=Moscow,ru&appid=e83d0265c9865659af525e50e89b8edd
            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String rwData = response.body().string();


            Log.i("rwData", rwData.toString());
//            JSONObject jsonObject = new JSONObject(rwData.toString());

            Gson gson = new Gson();
//            WeatherApi weatherApi = gson.fromJson(rwData.toString(), WeatherApi.class);
            //use toJson???
            WeatherApi weatherApi = gson.fromJson(rwData, WeatherApi.class);
            Log.i("api", weatherApi.getCity().getName());



//            return jsonObject;
            return weatherApi;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


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


                final WeatherApi weatherApi = getWeather("Moscow");
//                final WeatherApi weatherApi = getWeatherByOkHttp("Moscow");
                Log.i("loadData", weatherApi.getCity().getName());
                if (weatherApi == null) {
                    return;
                }
                handler.post(new Runnable() {//new treads
                    @Override
                    public void run() {
                        //todo load data

                        for (WeatherProviderListener listener : listeners) {
                            listener.upDateWeather(weatherApi);
                        }

                    }
                });


            }
        }, 2000, 100000);
    }

    void stopLoadData() {
        if (timer == null) {
            timer.cancel();
        }
        listeners.clear();
    }
}
