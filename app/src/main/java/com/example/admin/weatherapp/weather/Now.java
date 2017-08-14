package com.example.admin.weatherapp.weather;

/**
 * Created by admin on 2017/8/14.
 */

public class Now {
    public Cond cond;
    public class Cond
    {
        public String code;
        public String txt;
    }
    public String fl;
    public String hum;
    public String pcpn;
    public String pres;
    public String tmp;
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
