package com.example.admin.weatherapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.weatherapp.db.CityForecast;
import com.example.admin.weatherapp.db.UserAddCity;

/**
 * Created by admin on 2017/8/23.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    /**
     * 创建数据库的构造方法
     *  context 应用程序上下文
     * name 数据库的名字
     * factory 查询数据库的游标工厂一般情况下用sdk默认的
     * version 数据库的版本一般大于0
     */

    public MyDBHelper(Context context){
        super(context,"weather.db",null,4);
    }
    private String tag = "MyDBHelper.class";


    /**
     * 在数据库第一次创建时会执行
     *  db
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(tag,"concreate....");
        //创建数据表
        String CREATE_TABLE_City="CREATE TABLE "+ CityForecast.TABLE+"("
                +CityForecast.KEY_cityID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +CityForecast.KEY_cityname+" TEXT,"
                +CityForecast.KEY_provincename+" TEXT,"
                +CityForecast.KEY_airquality+" TEXT,"
                +CityForecast.KEY_airhum+" TEXT,"
                +CityForecast.KEY_winddir+" TEXT,"
                +CityForecast.KEY_tmp+" TEXT,"
                +CityForecast.KEY_tmpMaxMin+" TEXT,"
                +CityForecast.KEY_condcode+" INTEGER)";
        sqLiteDatabase.execSQL(CREATE_TABLE_City);

        String CREATE_TABLE_UserAddCity="CREATE TABLE "+ UserAddCity.TABLE+"("
                +CityForecast.KEY_cityID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +CityForecast.KEY_cityname+" TEXT,"
                +CityForecast.KEY_provincename+" TEXT,"
                +CityForecast.KEY_airquality+" TEXT,"
                +CityForecast.KEY_airhum+" TEXT,"
                +CityForecast.KEY_winddir+" TEXT,"
                +CityForecast.KEY_tmp+" TEXT,"
                +CityForecast.KEY_tmpMaxMin+" TEXT,"
                +CityForecast.KEY_condcode+" INTEGER)";
        sqLiteDatabase.execSQL(CREATE_TABLE_UserAddCity);


    }


    /**
     * 更新数据的时候调用的方法
     *  db
     *  oldVersion
     *  newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(tag,"onUpgrade......");
        //如果旧表存在，删除，所以数据将会消失
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ CityForecast.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ UserAddCity.TABLE);
        //再次创建表
        onCreate(sqLiteDatabase);

    }
}
