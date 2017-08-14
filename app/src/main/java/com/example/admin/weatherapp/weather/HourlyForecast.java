package com.example.admin.weatherapp.weather;

/**
 * Created by admin on 2017/8/14.
 */
public class HourlyForecast {
    public String date;
    public String hum;
    public String pop;
    public String pres;
    public String tmp;
    public Wind wind;
    public class Wind
    {
        public String deg;
        public String dir;
        public String sc;
        public String spd;
    }
}