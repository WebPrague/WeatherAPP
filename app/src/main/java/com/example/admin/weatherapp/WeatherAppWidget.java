package com.example.admin.weatherapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherAppWidget extends AppWidgetProvider {

    private String[] months={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
    private String[] days={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.weather_app_widget);
        Time time=new Time();
        time.setToNow();
        String month=time.year+" "+months[time.month];
        remoteViews.setTextViewText(R.id.txtDay, new Integer(time.monthDay).toString());
        remoteViews.setTextViewText(R.id.txtMonth, month);
        remoteViews.setTextViewText(R.id.txtWeekDay, days[time.weekDay]);
        Intent intent=new Intent("cn.com.karl.widget.click");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.layout, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        super.onUpdate(context,appWidgetManager,appWidgetIds);


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);
        if(intent.getAction().equals("cn.com.karl.widget.click")){
            Toast.makeText(context, "点击了widget日历",Toast.LENGTH_SHORT).show();
        }
    }
}

