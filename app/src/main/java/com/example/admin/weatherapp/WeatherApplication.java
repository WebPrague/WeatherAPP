package com.example.admin.weatherapp;

import android.app.Application;

import org.xutils.x;


/**
 * Created by admin on 2017/8/18.
 */

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能

    }
}
