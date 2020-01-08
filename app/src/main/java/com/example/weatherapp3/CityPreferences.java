package com.example.weatherapp3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CityPreferences {

    public static final String CITY_TAG = "City";
    private SharedPreferences appPreferences;
    private String city = "Orenburg";

    public CityPreferences(Activity activity) {
        appPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public String getCity() {
        return appPreferences.getString(CITY_TAG,city);
    }

    public void setCity(String city) {
        appPreferences.edit().putString(CITY_TAG,city).apply();
    }
}
