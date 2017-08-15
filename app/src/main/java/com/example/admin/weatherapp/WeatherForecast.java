package com.example.admin.weatherapp;

/**
 * Created by admin on 2017/8/15.
 */

public class WeatherForecast {
    private String date_title;
    private String date_detail_month;
    private String date_detail_date;
    private int max_imageid;
    private String max_weather;
    private int min_imageid;
    private String min_weather;
    private String wind;
    private String wind_speed;
    private int image_air_quality;

    public WeatherForecast(String date_title, String date_detail_month, String date_detail_date, int max_imageid, String max_weather, int min_imageid, String min_weather, String wind, String wind_speed, int image_air_quality) {
        this.date_title = date_title;
        this.date_detail_month = date_detail_month;
        this.date_detail_date = date_detail_date;
        this.max_imageid = max_imageid;
        this.max_weather = max_weather;
        this.min_imageid = min_imageid;
        this.min_weather = min_weather;
        this.wind = wind;
        this.wind_speed = wind_speed;
        this.image_air_quality = image_air_quality;
    }

    public String getDate_title() {
        return date_title;
    }

    public void setDate_title(String date_title) {
        this.date_title = date_title;
    }

    public String getDate_detail_month() {
        return date_detail_month;
    }

    public void setDate_detail_month(String date_detail_month) {
        this.date_detail_month = date_detail_month;
    }

    public String getDate_detail_date() {
        return date_detail_date;
    }

    public void setDate_detail_date(String date_detail_date) {
        this.date_detail_date = date_detail_date;
    }

    public int getMax_imageid() {
        return max_imageid;
    }

    public void setMax_imageid(int max_imageid) {
        this.max_imageid = max_imageid;
    }

    public String getMax_weather() {
        return max_weather;
    }

    public void setMax_weather(String max_weather) {
        this.max_weather = max_weather;
    }

    public int getMin_imageid() {
        return min_imageid;
    }

    public void setMin_imageid(int min_imageid) {
        this.min_imageid = min_imageid;
    }

    public String getMin_weather() {
        return min_weather;
    }

    public void setMin_weather(String min_weather) {
        this.min_weather = min_weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getImage_air_quality() {
        return image_air_quality;
    }

    public void setImage_air_quality(int image_air_quality) {
        this.image_air_quality = image_air_quality;
    }
}
