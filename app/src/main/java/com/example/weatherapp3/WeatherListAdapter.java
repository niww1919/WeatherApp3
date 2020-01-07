package com.example.weatherapp3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp3.weatherApi.WeatherApi;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private WeatherApi weather;

    public WeatherListAdapter(Context context, WeatherApi weather) {
        this.inflater = LayoutInflater.from(context);
        this.weather = weather;
    }

    @NonNull
    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.weather_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherListAdapter.ViewHolder holder, int position) {
        holder.setData(position);

    }

    @Override
//    public int getItemCount() {
//        return 6;//fixme
//    }
    public int getItemCount() {
//        return weather;//fixme or may be set 5
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvListDay;
        TextView tvListIcon;
        TextView tvListTemp;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvListDay = itemView.findViewById(R.id.tvListDay);
            tvListIcon = itemView.findViewById(R.id.tvListIcon);
            tvListTemp = itemView.findViewById(R.id.tvListTemp);

            //fixme
            tvListDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(itemView,"Item one", Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(itemView.getContext(),"Good",Toast.LENGTH_SHORT).show();
                }
            });
        }

//        void setData(String day, String icon, int temp) {
        void setData(int p) {
            tvListDay.setText(weather.getList().get(p).getDtTxt());
//            ((TextView)getActivity().findViewById(R.id.tvData)).setText(String.valueOf(weatherApi.getList().get(0).getDtTxt()));

//            tvListIcon.setText(icon);
//            tvListTemp.setText(String.valueOf(temp));
        }
    }
}
