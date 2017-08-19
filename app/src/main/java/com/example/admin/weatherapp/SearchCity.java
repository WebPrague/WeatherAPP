package com.example.admin.weatherapp;

/**
 * Created by admin on 2017/8/19.
 */

public class SearchCity {
    private String cityName;

    public SearchCity(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
