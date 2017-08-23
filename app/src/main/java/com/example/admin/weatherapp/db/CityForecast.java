package com.example.admin.weatherapp.db;

/**
 * Created by admin on 2017/8/23.
 */

public class CityForecast {
    //表名
    public static final String TABLE = "CityForecast";

    //表的各个域名
    public static final String KEY_cityID="id";
    public static final String KEY_cityname="name";
    public static final String KEY_provincename ="provincename";
    public static final String KEY_airquality = "airquality";
    public static final String KEY_airhum = "airhum";
    public static final String KEY_winddir = "winddir";
    public static final String KEY_tmp = "tmp";
    public static final String KEY_tmpMaxMin = "tmpmaxmin";
    public static final String KEY_condcode = "condcode";

    //属性
    public int cityID;
    public String cityName;
    public String provinceName;
    public String airQuality;
    public String airhum;
    public String windDir;
    public String tmp;
    public String tmpMaxMin;
    public int condcode;


}
