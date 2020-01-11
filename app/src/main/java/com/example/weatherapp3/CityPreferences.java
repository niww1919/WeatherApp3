package com.example.weatherapp3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CityPreferences {

    public static final String CITY_TAG = "City";
    private SharedPreferences appPreferences;
    private String city = "Orenburg";
    public static final String LATITUDE_TAG = "Latitude";
    public static final String LONGITUDE_TAG = "Longitude";
    public static final String ACCURACY_TAG = "Accuracy";
    String latitude = "0";  // Широта
    String longitude = "0";// Долгота
    String accuracy = "100" ; //точность

    public CityPreferences(Activity activity) {
        appPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public String getCity() {
        return appPreferences.getString(CITY_TAG,city);
    }

    public void setCity(String city) {
        appPreferences.edit().putString(CITY_TAG,city).apply();
    }

    public String getLatitude() {
        return appPreferences.getString(LATITUDE_TAG,latitude);
    }

    public void setLatitude(String latitude) {
        appPreferences.edit().putString(LATITUDE_TAG,latitude).apply();
    }

    public String getLongitude() {
        return appPreferences.getString(LONGITUDE_TAG,longitude);
    }

    public void setLongitude(String longitude) {
        appPreferences.edit().putString(LONGITUDE_TAG,longitude).apply();
    }

    public String getAccuracy() {
        return appPreferences.getString(ACCURACY_TAG,accuracy);
    }

    public void setAccuracy(String accuracy) {
        appPreferences.edit().putString(ACCURACY_TAG,accuracy).apply();

    }
}
