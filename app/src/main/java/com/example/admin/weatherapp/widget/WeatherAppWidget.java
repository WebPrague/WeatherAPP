package com.example.admin.weatherapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherAppWidget extends AppWidgetProvider {
    private static Set<Integer> idsSet = new HashSet();

    @Override
    public void onUpdate(Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            idsSet.add(Integer.valueOf(appWidgetId));
        }

        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_app_widget);
        WeatherService weatherService = new WeatherService();
        weatherService.getWeather("沈阳市", new WeatherService.GetWeatherListener() {
            @Override
            public void done(Weather weather, Exception e) {
                if (e == null){
                    for (int id : idsSet){
                        remoteViews.setTextViewText(R.id.tv_widget_tmp,weather.now.tmp);
                        remoteViews.setTextViewText(R.id.tv_widget_cond_text,weather.now.cond.txt);
                        remoteViews.setTextViewText(R.id.tv_widget_city,"666");
                        int widgetImageId;
                        widgetImageId = Integer.parseInt(weather.now.cond.code);

                        //remoteViews.setImageViewResource(R.id.iv_city_weather_image,);
                        appWidgetManager.updateAppWidget(id, remoteViews);
                    }

                }else{

                }
            }
        });

        super.onUpdate(context, appWidgetManager,appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // 当 widget 被删除时，对应的删除set中保存的widget的id
        for (int appWidgetId : appWidgetIds) {
            idsSet.remove(Integer.valueOf(appWidgetId));
        }
        super.onDeleted(context, appWidgetIds);
    }


    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }


    @Override
    public void onEnabled(Context context) {


        super.onEnabled(context);

    }


     @Override
     public void onReceive(Context context, Intent intent) {

     }


}

