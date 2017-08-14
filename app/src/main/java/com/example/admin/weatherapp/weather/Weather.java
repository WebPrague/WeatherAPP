package com.example.admin.weatherapp.weather;

import com.example.admin.weatherapp.Suggestion;

import java.util.List;

/**
 * Created by admin on 2017/8/14.
 */

public class Weather {
    public Aqi aqi;
    public Basic basic;
    public List<DailyForecast> daily_forecast;
    public List<HourlyForecast> hourly_forecast;
    public Now now;
    public String status;
    public Suggestion suggestion;
}

