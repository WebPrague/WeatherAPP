package com.example.admin.weatherapp.Fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.ImageView;

import com.example.admin.weatherapp.UI.BaseActivity;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.WeatherForecast;
import com.example.admin.weatherapp.WeatherLineViewAdapter;
import com.example.admin.weatherapp.weather.DailyForecast;
import com.example.admin.weatherapp.weather.Weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
        Weather weather = WeatherFragment.weather;
        for (DailyForecast dailyForecast : weather.daily_forecast){
            WeatherForecast weatherForecast = new WeatherForecast(getFormatDateWeek(dailyForecast.date), getFormatDate(dailyForecast.date), Integer.parseInt(dailyForecast.cond.code_d), dailyForecast.cond.txt_d,Integer.parseInt(dailyForecast.cond.code_n),dailyForecast.cond.txt_n,dailyForecast.wind.dir+"风",dailyForecast.wind.spd + "km/h",new Random().nextInt(2)==0?R.drawable.kongqizhiliang_liang:R.drawable.kongqizhiliang_you);

            weatherForecastList.add(weatherForecast);
        }

    }


    public String getFormatDateWeek(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date(System.currentTimeMillis())));
            Date inputDate = simpleDateFormat.parse(date);
            if ((inputDate.getTime() - today.getTime()) == 0l){
                return "今天";
            }
            if ((inputDate.getTime() - today.getTime()) == 24l * 60 * 60 * 1000l){
                return "明天";
            }
            return new SimpleDateFormat("EEEE").format(inputDate).replace("星期", "周");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getFormatDate(String date){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inputDate = simpleDateFormat.parse(date);
            return new SimpleDateFormat("M月d日").format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
