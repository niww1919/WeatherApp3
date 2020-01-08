package com.example.weatherapp3.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp3.CityPreferences;
import com.example.weatherapp3.R;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class SettingFragment extends Fragment {
    CityPreferences cityPreferences;
    EditText editText;

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
        editText.setText(cityPreferences.getCity());


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


            }
        });


        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        cityPreferences.setCity(String.valueOf(editText.getText()));
    }
}