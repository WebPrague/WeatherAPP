package com.example.admin.weatherapp.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.admin.weatherapp.AddCityActivityAdapter;
import com.example.admin.weatherapp.CityWeather;
import com.example.admin.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/16.
 */

public class AddCityActivity extends BaseActivity {
    private ImageView ivBackWeather;
    private ImageView ivEditCity;
    private ImageView ivAddNewCity;
    private List<CityWeather> cityWeatherList = new ArrayList<CityWeather>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcity);
        initState();

        initCityWeather();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_addcity_main);
        //横向
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AddCityActivityAdapter addCityActivityAdapter = new AddCityActivityAdapter(cityWeatherList);
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
        ivEditCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddCityActivity.this,SearchCityActivity.class));
            }
        });



    }

    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initCityWeather(){
        CityWeather city01 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city01);
        CityWeather city02 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city02);
        CityWeather city03 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city03);
        CityWeather city04 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city04);
        CityWeather city05 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city05);
        CityWeather city06 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city06);
        CityWeather city07 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city07);
        CityWeather city08 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city08);
        CityWeather city09 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city09);
        CityWeather city10 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city10);
        CityWeather city11 = new CityWeather("阿尔山","乌兰浩特，内蒙古",R.drawable.qing,"23","优","67","东北风","24","13");
        cityWeatherList.add(city11);
    }

}
