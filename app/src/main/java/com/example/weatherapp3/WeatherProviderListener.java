package com.example.weatherapp3;

import com.example.weatherapp3.weatherApi.WeatherApi;

public interface WeatherProviderListener {
    void upDateWeather(WeatherApi weatherApi);
}
