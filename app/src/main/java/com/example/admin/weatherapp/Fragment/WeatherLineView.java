package com.example.admin.weatherapp.Fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.ImageView;

import com.example.admin.weatherapp.BaseActivity;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.WeatherForecast;
import com.example.admin.weatherapp.WeatherLineViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/13.
 */

public class WeatherLineView  extends BaseActivity{
    private ImageView btn_delete;

    private List<WeatherForecast> weatherForecastList = new ArrayList<WeatherForecast>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherline_main);
        initWeatherForecast();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_weatherline_main);
        //竖向的recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        WeatherLineViewAdapter adapter = new WeatherLineViewAdapter(weatherForecastList);
        recyclerView.setAdapter(adapter);

        btn_delete = (ImageView)findViewById(R.id.iv_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initWeatherForecast(){
        WeatherForecast day01 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day01);
        WeatherForecast day02 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day02);
        WeatherForecast day03 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day03);
        WeatherForecast day04 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day04);
        WeatherForecast day05 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day05);
        WeatherForecast day06 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day06);
        WeatherForecast day07 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day07);
        WeatherForecast day08 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day08);
        WeatherForecast day09 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day09);
        WeatherForecast day10 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day10);
        WeatherForecast day11 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day11);
        WeatherForecast day12 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day12);
        WeatherForecast day13 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day13);
        WeatherForecast day14 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day14);
        WeatherForecast day15 = new WeatherForecast("今天","8","15",R.drawable.qing,"晴",R.drawable.leizhenyu,"雷阵雨","东北风","1级",R.drawable.kongqizhiliang_liang);
        weatherForecastList.add(day15);
    }




}
