package com.example.admin.weatherapp.weather;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/8/14.
 */

public class WeatherService {
    private final String KEY = "91952d504c9d4f3eb621786153f094cd";

    public interface GetWeatherListener{
        public void done(Weather weather, Exception e);
    }

    private OkHttpClient client = new OkHttpClient.Builder().build();

    public void getWeather(final String city, final GetWeatherListener getWeatherListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://free-api.heweather.com/v5/weather?city=" + city+ "&key=" + KEY;
                Request request = new Request.Builder().url(url).build();
                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                getWeatherListener.done(null,new Exception("网络错误"));
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();
                        try {
                            JsonObject jsonObject = (JsonObject)(new JsonParser().parse(body));
                            String weatherStr = jsonObject.get("HeWeather5").getAsJsonArray().get(0).getAsJsonObject().toString();
                            final Weather weather = new Gson().fromJson(weatherStr,Weather.class);

                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    getWeatherListener.done(weather,null);
                                }
                            });
                        }catch (Exception e){
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    getWeatherListener.done(null,new Exception("解析JSON字符串的时候发生异常"));
                                }
                            });
                        }
                    }
                });


            }
        }).start();
    }
}
