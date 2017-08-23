package com.example.admin.weatherapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.weatherapp.db.CityForecast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/23.
 */

//数据库的查插删改


public class CityRepo {
    private MyDBHelper myDBHelper;
    public CityRepo(Context context){
        myDBHelper = new MyDBHelper(context);
    }

    public void insert(CityForecast cityForecast){
        SQLiteDatabase database = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CityForecast.KEY_cityname,cityForecast.cityName);
        values.put(CityForecast.KEY_provincename,cityForecast.provinceName);
        database.insert(CityForecast.TABLE,null,values);
        database.close();
        //return (int)city_Id;
    }

    public List<CityForecast> getAllCity(){
        List<CityForecast> allCitys = new ArrayList<CityForecast>();
        SQLiteDatabase database = myDBHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from CityForecast",null);
        while (cursor.moveToNext()){

            CityForecast city = new CityForecast();

            city.cityID = cursor.getInt(0);
            city.cityName = cursor.getString(1);
            city.provinceName = cursor.getString(2);
            city.airQuality = cursor.getString(3);
            city.airhum = cursor.getString(4);
            city.windDir = cursor.getString(5);
            city.tmp = cursor.getString(6);
            city.tmpMaxMin = cursor.getString(7);
            city.condcode = cursor.getInt(8);
            allCitys.add(city);
        }
        cursor.close();
        database.close();
        return allCitys;
    }


}
