package com.example.admin.weatherapp;

/**
 * Created by admin on 2017/8/22.
 */

public class Configuration {
    private static String HOST = "123.206.21.155";
    //    private static String HOST = "10.25.44.122";
    private static String PORT = "8080";
    private static String APP = "WeatherWeb/v1";

    public static String BASE_HTTP_URL = "http://" + HOST + ":" + PORT +  (APP.equals("")?"/":"/" + APP + "/");
    public static String BASE_WS_URL = "ws://" + HOST + ":" + PORT + (APP.equals("")?"/":"/" + APP + "/") ;

    //和风天气相关配置
    public static final String HEFENG_URL = "https://free-api.heweather.com/v5/weather";
    public static final String HEFENG_NOW_URL = "https://free-api.heweather.com/v5/now";
    public static final String HEFENG_KEY = "91952d504c9d4f3eb621786153f094cd";
}
