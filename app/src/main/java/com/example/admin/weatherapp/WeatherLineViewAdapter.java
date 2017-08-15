package com.example.admin.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.weatherapp.weather.DailyForecast;

import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */

public class WeatherLineViewAdapter extends RecyclerView.Adapter {

    private List<DailyForecast> mdailyForecastList;

    public WeatherLineViewAdapter(List<DailyForecast> mdailyForecastList) {
        this.mdailyForecastList = mdailyForecastList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_weatherline_item,parent,false);
        //初始化ViewHolder
        final ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //数据


    }


    @Override
    public int getItemCount() {
        return mdailyForecastList.size();
    }



    //防止不断初始化对象
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {


            super(itemView);
        }
    }


}
