package com.example.admin.weatherapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.admin.weatherapp.MyDBHelper;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.db.WeatherCity;
import com.example.admin.weatherapp.service.QsbkService;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/11.
 */

public class WelcomeActivity extends BaseActivity {
    private ImageView dvWelcome;
    private WeatherService weatherService;
    private DbManager dbManager;

    private ImageOptions imageOptions = new ImageOptions.Builder()
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            // 默认自动适应大小
            // .setSize(...)
            .setIgnoreGif(false)
            // 如果使用本地文件url, 添加这个设置可以在本地文件更新后刷新立即生效.
            .setUseMemCache(false)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP).build();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        //隐藏标题栏以及状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        dvWelcome= (ImageView) findViewById(R.id.iv_welcome);



        x.image().bind(dvWelcome, "assets://welcome.gif",imageOptions);

        //天气相关
        weatherService = new WeatherService();

       // initial();
        //数据库相关
        initialDB();
        handler.sendEmptyMessageDelayed(0,2000);

//        MyDBHelper myDBHelper = new MyDBHelper(this);
//        myDBHelper.getReadableDatabase();
//        myDBHelper.close();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }


    private void initialDB(){
        dbManager = x.getDb(daoConfig);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dbManager.findAll(WeatherCity.class).size();
                }catch (Exception e){
                    WeatherCity weatherCity = new WeatherCity();
                    weatherCity.setTmp("666");
                    try {
                        dbManager.save(weatherCity);
                    } catch (DbException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        WeatherCity weatherCity1 = dbManager.findFirst(WeatherCity.class);
                        dbManager.delete(weatherCity1);
                    } catch (DbException e1) {
                        e1.printStackTrace();
                    }
                }

                try {
                    List<WeatherCity> weatherCities = dbManager.findAll(WeatherCity.class);
                    for (WeatherCity weatherCity :  weatherCities){
                        Weather weather = weatherService.getSyncWeatherFromHeFeng(weatherCity.getCity());
                        if (weather != null){
                            weatherCity.setTmp(weather.now.tmp);
                            weatherCity.setHum(weather.now.hum);
                            weatherCity.setAirQuality(weather.aqi.city.qlty);
                            weatherCity.setCondCode(Integer.parseInt(weather.now.cond.code));
                            weatherCity.setWindDir(weather.now.wind.dir);
                            weatherCity.setTmpMax(weather.daily_forecast.get(0).tmp.max);
                            weatherCity.setTmpMin(weather.daily_forecast.get(0).tmp.min);
                            dbManager.update(weatherCity);
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
