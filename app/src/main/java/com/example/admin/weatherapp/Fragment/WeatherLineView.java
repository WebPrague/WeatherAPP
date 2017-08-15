package com.example.admin.weatherapp.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.weatherapp.BaseActivity;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.WeatherLineViewAdapter;
import com.example.admin.weatherapp.weather.DailyForecast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/13.
 */

public class WeatherLineView  extends BaseActivity{

    private List<DailyForecast> dailyForecastList = new ArrayList<DailyForecast>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherline_main);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_weatherline_main);
        //竖向的recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        WeatherLineViewAdapter adapter = new WeatherLineViewAdapter(dailyForecastList);
        recyclerView.setAdapter(adapter);
    }
}
