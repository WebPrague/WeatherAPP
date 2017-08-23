package com.example.admin.weatherapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.weatherapp.db.CityForecast;
import com.example.admin.weatherapp.db.UserAddCity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/23.
 */

//数据库的查插删改
public class UserAddCityRepo {

    private MyDBHelper myDBHelper;
    public UserAddCityRepo(Context context){
        myDBHelper = new MyDBHelper(context);
    }

    public void insert(UserAddCity userAddCity){
        SQLiteDatabase database = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserAddCity.KEY_cityname,userAddCity.cityName);
        values.put(UserAddCity.KEY_provincename,userAddCity.provinceName);
        values.put(UserAddCity.KEY_airquality,userAddCity.airQuality);
        values.put(UserAddCity.KEY_airhum,userAddCity.airhum);
        values.put(UserAddCity.KEY_winddir,userAddCity.windDir);
        values.put(UserAddCity.KEY_tmp,userAddCity.tmp);
        values.put(UserAddCity.KEY_tmpMax,userAddCity.tmpMax);
        values.put(UserAddCity.KEY_tmpMin,userAddCity.tmpMin);
        values.put(UserAddCity.KEY_condcode,userAddCity.condcode);
        database.insert(UserAddCity.TABLE,null,values);
        database.close();
        //return (int)city_Id;
    }

    public List<UserAddCity> getUserAddCity(){
        List<UserAddCity> allUserAddCitys = new ArrayList<UserAddCity>();
        SQLiteDatabase database = myDBHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from UserAddCity",null);
        while (cursor.moveToNext()){

            UserAddCity userAddCity = new UserAddCity();
            userAddCity.cityID = cursor.getInt(0);
            userAddCity.cityName = cursor.getString(1);
            userAddCity.provinceName = cursor.getString(2);
            userAddCity.airQuality = cursor.getString(3);
            userAddCity.airhum = cursor.getString(4);
            userAddCity.windDir = cursor.getString(5);
            userAddCity.tmp = cursor.getString(6);
            userAddCity.tmpMax = cursor.getString(7);
            userAddCity.tmpMin = cursor.getString(8);
            userAddCity.condcode = cursor.getInt(9);
            allUserAddCitys.add(userAddCity);
        }
        cursor.close();
        database.close();
        return allUserAddCitys;
    }

}
