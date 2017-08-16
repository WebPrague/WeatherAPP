package com.example.admin.weatherapp;

/**
 * Created by admin on 2017/8/16.
 */

public class CityWeather {
    private String detail_city;
    private String province;
    private int weather_imageid;
    private String city_weather_tmp;
    private String city_qulity;
    private String city_shidu;
    private String city_wind;
    private String city_max_tmp;
    private String city_min_tmp;

    public CityWeather(String detail_city, String province, int weather_imageid, String city_weather_tmp, String city_qulity, String city_shidu, String city_wind, String city_max_tmp, String city_min_tmp) {
        this.detail_city = detail_city;
        this.province = province;
        this.weather_imageid = weather_imageid;
        this.city_weather_tmp = city_weather_tmp;
        this.city_qulity = city_qulity;
        this.city_shidu = city_shidu;
        this.city_wind = city_wind;
        this.city_max_tmp = city_max_tmp;
        this.city_min_tmp = city_min_tmp;
    }

    public String getDetail_city() {
        return detail_city;
    }

    public void setDetail_city(String detail_city) {
        this.detail_city = detail_city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getWeather_imageid() {
        return weather_imageid;
    }

    public void setWeather_imageid(int weather_imageid) {
        this.weather_imageid = weather_imageid;
    }

    public String getCity_weather_tmp() {
        return city_weather_tmp;
    }

    public void setCity_weather_tmp(String city_weather_tmp) {
        this.city_weather_tmp = city_weather_tmp;
    }

    public String getCity_qulity() {
        return city_qulity;
    }

    public void setCity_qulity(String city_qulity) {
        this.city_qulity = city_qulity;
    }

    public String getCity_shidu() {
        return city_shidu;
    }

    public void setCity_shidu(String city_shidu) {
        this.city_shidu = city_shidu;
    }

    public String getCity_wind() {
        return city_wind;
    }

    public void setCity_wind(String city_wind) {
        this.city_wind = city_wind;
    }

    public String getCity_max_tmp() {
        return city_max_tmp;
    }

    public void setCity_max_tmp(String city_max_tmp) {
        this.city_max_tmp = city_max_tmp;
    }

    public String getCity_min_tmp() {
        return city_min_tmp;
    }

    public void setCity_min_tmp(String city_min_tmp) {
        this.city_min_tmp = city_min_tmp;
    }
}
