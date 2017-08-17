package com.example.admin.weatherapp.weather;

/**
 * Created by admin on 2017/8/14.
 */
public class HourlyForecast {

    public static class Cond
    {
        public String code;
        public String txt;
    }
    public Cond cond;

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