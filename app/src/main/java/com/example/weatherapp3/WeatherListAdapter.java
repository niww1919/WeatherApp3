package com.example.weatherapp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    @NonNull
    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.weather_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherListAdapter.ViewHolder holder, int position) {
        holder.setData("1","Sun",2);

    }

    @Override
    public int getItemCount() {
        return 6;//fixme
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvListDay;
        TextView tvListIcon;
        TextView tvListTemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListDay = itemView.findViewById(R.id.tvListDay);
            tvListIcon = itemView.findViewById(R.id.tvListIcon);
            tvListTemp = itemView.findViewById(R.id.tvListTemp);
        }

        void setData(String day, String icon, int temp) {
            tvListDay.setText(day);
            tvListIcon.setText(icon);
            tvListTemp.setText(String.valueOf(temp));
        }
    }
}
