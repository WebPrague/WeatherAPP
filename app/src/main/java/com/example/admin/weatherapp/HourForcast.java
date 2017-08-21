package com.example.admin.weatherapp;

/**
 * Created by admin on 2017/8/21.
 */

public class HourForcast {
    private int hourWeatherImageId;
    private String hourWind;
    private String hourWeatherQulity;
    private String hourTime;

    public HourForcast(int hourWeatherImageId, String hourWind, String hourWeatherQulity, String hourTime) {
        this.hourWeatherImageId = hourWeatherImageId;
        this.hourWind = hourWind;
        this.hourWeatherQulity = hourWeatherQulity;
        this.hourTime = hourTime;
    }

    public int getHourWeatherImageId() {
        return hourWeatherImageId;
    }

    public void setHourWeatherImageId(int hourWeatherImageId) {
        this.hourWeatherImageId = hourWeatherImageId;
    }

    public String getHourWind() {
        return hourWind;
    }

    public void setHourWind(String hourWind) {
        this.hourWind = hourWind;
    }

    public String getHourWeatherQulity() {
        return hourWeatherQulity;
    }

    public void setHourWeatherQulity(String hourWeatherQulity) {
        this.hourWeatherQulity = hourWeatherQulity;
    }

    public String getHourTime() {
        return hourTime;
    }

    public void setHourTime(String hourTime) {
        this.hourTime = hourTime;
    }
}
