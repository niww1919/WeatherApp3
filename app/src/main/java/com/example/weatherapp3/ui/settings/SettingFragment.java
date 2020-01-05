package com.example.weatherapp3.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp3.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SettingFragment extends Fragment {
    public static final String LINK_TO_GITHUB = "https://github.com/niww1919/WeatherApp3";
    public static final String URL = "https://hh.ru";

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


        root.findViewById(R.id.buttonLinkToGitHub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(LINK_TO_GITHUB);
                Intent intent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intent);
            }
        });

        final WebView webView = root.findViewById(R.id.webView);

        new Thread(new Runnable() {
            HttpsURLConnection httpsURLConnection = null;
            Handler handler = new Handler();

            @Override
            public void run() {

                try {
                    URL uri = new URL(URL);
                    httpsURLConnection = (HttpsURLConnection) uri.openConnection();
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.setConnectTimeout(10000);

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    final StringBuilder rwData = new StringBuilder(1024);
                    String temp;
                    while ((temp = bufferedReader.readLine()) != null) {
                        rwData.append(temp).append("\n");
                    }
                    bufferedReader.close();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadData(rwData.toString(), "text/html; charset=utf-8", "itf-8");

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();

        return root;
    }


}