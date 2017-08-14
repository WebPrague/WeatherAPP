package com.example.admin.weatherapp.weather;

/**
 * Created by admin on 2017/8/14.
 */
public class DailyForecast {
    public Astro astro;
    public class Astro
    {
        public String sr;
        public String ss;
    }
    public Cond cond;
    public class Cond
    {
        public String code_d;
        public String code_n;
        public String txt_d;
        public String txt_n;
    }
    public String date;
    public String hum;
    public String pcpn;
    public String pop;
    public String pres;
    public Tmp tmp;
    public class Tmp
    {
        public String max;
        public String min;
    }
    public String vis;
    public Wind wind;
    public class Wind
    {
        public String deg;
        public String dir;
        public String sc;
        public String spd;
    }
}