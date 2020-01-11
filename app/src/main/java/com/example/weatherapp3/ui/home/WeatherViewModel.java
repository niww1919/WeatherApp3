package com.example.weatherapp3.ui.home;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp3.CityPreferences;

public class WeatherViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public WeatherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Weather");
    }

    public LiveData<String> getText() {
        return mText;
    }
}