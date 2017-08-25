package com.example.admin.weatherapp.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.weatherapp.AddCityActivityAdapter;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.db.WeatherCity;
import com.example.admin.weatherapp.util.SpUtil;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;


import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by admin on 2017/8/16.
 */

public class AddCityActivity extends BaseActivity {
    private ImageView ivBackWeather;
    private ImageView ivEditCity;
    private ImageView ivAddNewCity;
    private TextView tvCity;
    private List<WeatherCity> weatherCityList = new ArrayList<WeatherCity>();
    private AddCityActivityAdapter addCityActivityAdapter;

    private WindowManager mWindowManager = null;
    private View mNightView = null;
    private WindowManager.LayoutParams mNightViewParam;
    private boolean mIsAddedView;

    private BroadcastReceiver nightModeBroadcastReceiver;


    //数据库
    private DbManager db;

    //天气相关
    private WeatherService weatherService;

    private CitySelectedBroadcastReceiver citySelectedBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcity);
        initState();

        //注册广播
        IntentFilter intentFilter_addactivity = new IntentFilter("ye_jian_guang_bo_by_hp");
        nightModeBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean b = intent.getBooleanExtra("is_checked",false);

                if (b){
                    SpUtil.getinstance(AddCityActivity.this).setReaderModeCode(1);
                    changeToNight();
                }else {
                    SpUtil.getinstance(AddCityActivity.this).setReaderModeCode(0);
                    changeToDay();
                }

            }
        };
        registerReceiver(nightModeBroadcastReceiver, intentFilter_addactivity);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_addcity_main);
        //横向
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        addCityActivityAdapter = new AddCityActivityAdapter(weatherCityList, new AddCityActivityAdapter.OnAddCityClickListener() {
            @Override
            public void onAddCityClick(String city) {
                //Toast.makeText(AddCityActivity.this,city,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction("hupeng");
                intent.putExtra("city",city);
                sendBroadcast(intent);
                finish();
            }
        });


        recyclerView.setAdapter(addCityActivityAdapter);
        ivBackWeather = (ImageView)findViewById(R.id.iv_back_weather);
        ivBackWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ivEditCity = (ImageView)findViewById(R.id.iv_edit_city);
        ivEditCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddCityActivity.this,EditCityActivity.class));
            }
        });


        ivAddNewCity = (ImageView)findViewById(R.id.iv_add_new_city);
        ivAddNewCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddCityActivity.this,SearchCityActivity.class));
            }
        });

        //天气服务
        weatherService = new WeatherService();

        //数据库服务
        db = x.getDb(daoConfig);

        //广播注册
        IntentFilter intentFilter = new IntentFilter("zhangpeng");
        citySelectedBroadcastReceiver = new CitySelectedBroadcastReceiver();
        registerReceiver(citySelectedBroadcastReceiver,intentFilter);

    }

    @Override
    protected void onDestroy() {
        changeToDay();
        unregisterReceiver(nightModeBroadcastReceiver);
        unregisterReceiver(citySelectedBroadcastReceiver);
        super.onDestroy();
    }

    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }



    public class CitySelectedBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            final String city = intent.getStringExtra("city");
//            Toast.makeText(AddCityActivity.this, city, Toast.LENGTH_SHORT).show();
            final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddCityActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            sweetAlertDialog.setTitleText("Loading");
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.show();
            try {
                if (db.selector(WeatherCity.class).where("city","=",city).findFirst() != null){
                    new SweetAlertDialog(AddCityActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("这个城市已经被添加过了哦！")
                            .setContentText("")
                            .show();
                    sweetAlertDialog.cancel();
                }else {
                    weatherService.getWeather(city, new WeatherService.GetWeatherListener() {
                        @Override
                        public void done(Weather weather, Exception e) {
                            if (e == null){
                                sweetAlertDialog.cancel();
                                WeatherCity weatherCity = new WeatherCity();
                                weatherCity.setCity(city);
                                weatherCity.setProvince("辽宁省");
                                weatherCity.setTmp(weather.now.tmp);
                                weatherCity.setHum(weather.now.hum);
                                weatherCity.setAirQuality(weather.aqi.city.qlty);
                                weatherCity.setCondCode(Integer.parseInt(weather.now.cond.code));
                                weatherCity.setWindDir(weather.now.wind.dir);
                                weatherCity.setTmpMax(weather.daily_forecast.get(0).tmp.max);
                                weatherCity.setTmpMin(weather.daily_forecast.get(0).tmp.min);

                                try {
                                    db.save(weatherCity);
                                    SweetAlertDialog successSweetAlertDialog = new SweetAlertDialog(AddCityActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("城市添加成功")
                                            .setContentText("");
                                    weatherCityList.add(weatherCity);
                                    addCityActivityAdapter.notifyDataSetChanged();
                                    successSweetAlertDialog.show();
                                }catch (Exception e1){
                                    e.printStackTrace();
                                }
                            }else {
                                new SweetAlertDialog(AddCityActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("城市添加失败")
                                        .setContentText(" ")
                                        .show();
                            }
                        }
                    });
                }
            } catch (DbException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            List<WeatherCity>weatherCities = db.findAll(WeatherCity.class);
            weatherCityList.removeAll(weatherCityList);
            weatherCityList.addAll(weatherCities);
            addCityActivityAdapter.notifyDataSetChanged();
        } catch (DbException e) {
            e.printStackTrace();
        }
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



    @Override
    public void changeToNight() {
        if (mIsAddedView == true)
            return;
        mNightViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        mWindowManager = getWindowManager();
        mNightView = new View(this);
        mNightView.setBackgroundResource(R.color.colorNight);
        mWindowManager.addView(mNightView, mNightViewParam);
        mIsAddedView = true;
    }

    //设置白天模式
    @Override
    public void changeToDay(){

        if (mIsAddedView && mNightView!=null) {
            mWindowManager.removeViewImmediate(mNightView);
            mWindowManager = null;
            mNightView = null;
            mIsAddedView=false;
        }
    }


}
