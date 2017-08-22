package com.example.admin.weatherapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.db.WeatherCity;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/11.
 */

public class WelcomeActivity extends BaseActivity {

    private WeatherService weatherService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏以及状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(0,3000);
    }

    /**
     * 初始化
     * */
    private void initial(){
        weatherService = new WeatherService();
        handler.sendEmptyMessageDelayed(0x00, 2000);
        initialDB();
        initialCityWeather();
    }

    private void initialDB(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DbManager db = x.getDb(daoConfig);
                try {
                    db.findAll(WeatherCity.class).size();
                }catch (Exception e){
                    List<WeatherCity> weatherCityList = new ArrayList<WeatherCity>();
                    WeatherCity weathercity01 = new WeatherCity();
                    //weathercity.setTmp("666");
                    weathercity01.setCity("沈阳市");
                    weathercity01.setProvince("辽宁省");
                    weatherCityList.add(weathercity01);

                    WeatherCity weathercity02 = new WeatherCity();
                    weathercity02.setCity("大连市");
                    weathercity02.setProvince("辽宁省");
                    weatherCityList.add(weathercity02);

                    WeatherCity weathercity03 = new WeatherCity();
                    weathercity03.setCity("鞍山市");
                    weathercity03.setProvince("辽宁省");
                    weatherCityList.add(weathercity03);

                    WeatherCity weathercity04 = new WeatherCity();
                    weathercity04.setCity("抚顺市");
                    weathercity04.setProvince("辽宁省");
                    weatherCityList.add(weathercity04);

                    WeatherCity weathercity05 = new WeatherCity();
                    weathercity05.setCity("本溪市");
                    weathercity05.setProvince("辽宁省");
                    weatherCityList.add(weathercity05);

                    WeatherCity weathercity06 = new WeatherCity();
                    weathercity06.setCity("丹东市");
                    weathercity06.setProvince("辽宁省");
                    weatherCityList.add(weathercity06);

                    WeatherCity weathercity07 = new WeatherCity();
                    weathercity07.setCity("锦州市");
                    weathercity07.setProvince("辽宁省");
                    weatherCityList.add(weathercity07);

                    WeatherCity weathercity08 = new WeatherCity();
                    weathercity08.setCity("营口市");
                    weathercity08.setProvince("辽宁省");
                    weatherCityList.add(weathercity08);

                    WeatherCity weathercity09 = new WeatherCity();
                    weathercity09.setCity("大连市");
                    weathercity09.setProvince("辽宁省");
                    weatherCityList.add(weathercity09);

                    WeatherCity weathercity10 = new WeatherCity();
                    weathercity10.setCity("大连市");
                    weathercity10.setProvince("辽宁省");
                    weatherCityList.add(weathercity10);

                    WeatherCity weathercity11 = new WeatherCity();
                    weathercity11.setCity("大连市");
                    weathercity11.setProvince("辽宁省");
                    weatherCityList.add(weathercity11);

                    WeatherCity weathercity12 = new WeatherCity();
                    weathercity12.setCity("大连市");
                    weathercity12.setProvince("辽宁省");
                    weatherCityList.add(weathercity12);

                    try {
                        db.save(weathercity01);
                        db.save(weathercity02);
                        db.save(weathercity03);
                        db.save(weathercity04);
                        db.save(weathercity05);
                        db.save(weathercity06);
                        db.save(weathercity07);
                        db.save(weathercity08);
                        db.save(weathercity09);
                        db.save(weathercity10);
                        db.save(weathercity11);
                        db.save(weathercity12);
                    } catch (DbException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initialCityWeather(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                DbManager db = x.getDb(daoConfig);

                try {
                    List<WeatherCity>cities = db.findAll(WeatherCity.class);
                    if (cities == null){
                        return;
                    }
                    for (WeatherCity city: cities){
                        try {
                            Weather weather = weatherService.getSyncWeatherFromHeFeng(city.getCity());
                            city.setTmp(weather.now.tmp);
                            city.setTmpMaxMin(weather.daily_forecast.get(0).tmp.max + "°/" + weather.daily_forecast.get(0).tmp.min + "°");
                            if (weather.aqi.city.qlty == null){
                                weather.aqi.city.qlty = "优";
                            }
                            if (weather.aqi.city.qlty.equals("优") || (weather.aqi.city.qlty.equals("良"))){
                                city.setAirQuality("空气" + (weather.aqi.city.qlty));
                            }else{
                                city.setAirQuality(weather.aqi.city.qlty);
                            }
                            city.setCondCode(Integer.parseInt(weather.now.cond.code));
                            city.setHum("湿度" + weather.now.hum + "%");
                            city.setWindDir(weather.now.wind.dir + "风");

                        }catch (Exception e){

                        }
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }




    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome(){
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
