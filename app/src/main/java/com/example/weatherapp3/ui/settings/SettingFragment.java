package com.example.weatherapp3.ui.settings;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp3.CityPreferences;
import com.example.weatherapp3.R;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import static android.content.Context.LOCATION_SERVICE;

public class SettingFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback {
    CityPreferences cityPreferences;
    EditText editText;
    public static final int PERMISSION_REQUEST_CODE = 10;
    private LocationManager locationManager;
    private String provider;

    public static final String LINK_TO_GITHUB = "https://github.com/niww1919/WeatherApp3";

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        cityPreferences = new CityPreferences(getActivity());
        editText = root.findViewById(R.id.etCurrentCity);
        if (editText.getText() == null) {
            editText.setText("Orenburg");
        } else {
            editText.setText(cityPreferences.getCity());

        }


        root.findViewById(R.id.ivGit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(LINK_TO_GITHUB);
                Intent intent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intent);
            }
        });
        Picasso.get()
                .load("https://square.github.io/picasso/static/icon-github.png")
                .into((ImageView) root.findViewById(R.id.ivGit));

        root.findViewById(R.id.buttonToGatLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(
                        getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(
                        getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    requestLocation();
                    ((TextView) root.findViewById(R.id.tvLocation))
                            .setText(cityPreferences.getLatitude() + " " + cityPreferences.getLongitude());


                } else {
                    requestLocationPermissions();

                }

            }
        });


        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
            cityPreferences.setCity(String.valueOf(editText.getText()));

    }


    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        provider = locationManager.getBestProvider(criteria, true);
        if (provider != null) {
            locationManager.requestLocationUpdates(provider, 10000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    cityPreferences.setLatitude(Double.toString(location.getLatitude()).substring(0, 7));
                    cityPreferences.setLongitude(Double.toString(location.getLongitude()).substring(0, 7));
                    cityPreferences.setAccuracy(Float.toString(location.getAccuracy()));
                    Log.i("location", Double.toString(location.getLongitude()));
                    Log.i("location", Double.toString(location.getLatitude()));
                    Log.i("location", Double.toString(location.getAccuracy()));


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        }

    }

    private void requestLocationPermissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
            // Запросим эти две пермиссии у пользователя
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {   // Это та самая пермиссия, что мы запрашивали?
            if (grantResults.length == 2 &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                // Все препоны пройдены и пермиссия дана
                requestLocation();
            }
        }
    }
}