package com.example.admin.weatherapp.weather;

/**
 * Created by admin on 2017/8/14.
 */

public class Basic {
    public String city;
    private String cnty;
    public String id;
    public String lat;
    public String lon;

    public Update update;
    public class Update
    {
        public String loc;
        public String utc;
    }
}