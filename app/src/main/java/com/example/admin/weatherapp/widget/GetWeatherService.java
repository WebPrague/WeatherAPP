//package com.example.admin.weatherapp.widget;
//
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.IBinder;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.widget.ImageView;
//import android.widget.RemoteViews;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.admin.weatherapp.R;
//import com.example.admin.weatherapp.weather.Weather;
//import com.example.admin.weatherapp.weather.WeatherService;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by admin on 2017/8/23.
// */
//
//public class GetWeatherService extends Service {
//
//    private RemoteViews remoteViews;
//    private WeatherService weatherService = new WeatherService();
//
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        remoteViews = new RemoteViews(this.getPackageName(), R.layout.weather_app_widget);
//        weatherService.getWeather("沈阳市", new WeatherService.GetWeatherListener() {
//            @Override
//            public void done(Weather weather, Exception e) {
//                if (e == null){
//
//                    remoteViews.setTextViewText(R.id.tv_widget_tmp,weather.now.tmp);
//                    remoteViews.setTextViewText(R.id.tv_widget_cond_text,weather.now.cond.txt);
//                    remoteViews.setTextViewText(R.id.tv_widget_city,"沈阳市");
//                    int widgetImageId;
//                    widgetImageId = Integer.parseInt(weather.now.cond.code);
//
//                    //remoteViews.setImageViewResource(R.id.iv_city_weather_image,);
//
//                }else{
//
//                }
//            }
//        });
//    }
//
//
//
//    // 广播接收者去接收系统每分钟的提示广播，来更新时间
//    private BroadcastReceiver mTimePickerBroadcast = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            //updateTime();
//        }
//    };
//
//    @Override
//    public void onStart(Intent intent, int startId) {
//        // 注册系统每分钟提醒广播（注意：这个广播只能在代码中注册）
//        IntentFilter updateIntent = new IntentFilter();
//        updateIntent.addAction("android.intent.action.TIME_TICK");
//        registerReceiver(mTimePickerBroadcast, updateIntent);
//        super.onStart(intent, startId);
//    }
//
//    @Override
//    public void onDestroy() {
//        // 注销系统的这个广播
//        unregisterReceiver(mTimePickerBroadcast);
//        //被系统干掉后，
//        Intent intent = new Intent(getApplicationContext(), GetWeatherService.class);
//        getApplication().startService(intent);
//        super.onDestroy();
//    }
//}
