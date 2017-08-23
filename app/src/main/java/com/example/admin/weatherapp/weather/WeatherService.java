package com.example.admin.weatherapp.weather;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.admin.weatherapp.Configuration;
import com.example.admin.weatherapp.UI.AddCityActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public static Map<Integer,Integer> heFengToXinZhiMapping;

    static {
        if (heFengToXinZhiMapping == null){
            heFengToXinZhiMapping = new HashMap<>();
        }

        heFengToXinZhiMapping.put(100, 0);
        heFengToXinZhiMapping.put(101,4);
        heFengToXinZhiMapping.put(103,5);
        heFengToXinZhiMapping.put(101,7);
        heFengToXinZhiMapping.put(104,9);
        heFengToXinZhiMapping.put(300,10);
        heFengToXinZhiMapping.put(302,11);
        heFengToXinZhiMapping.put(304,12);
        heFengToXinZhiMapping.put(305,13);
        heFengToXinZhiMapping.put(306,14);
        heFengToXinZhiMapping.put(307,15);
        heFengToXinZhiMapping.put(310,16);
        heFengToXinZhiMapping.put(311,17);
        heFengToXinZhiMapping.put(312,18);
        heFengToXinZhiMapping.put(313,19);
        heFengToXinZhiMapping.put(404,20);
        heFengToXinZhiMapping.put(407,21);
        heFengToXinZhiMapping.put(400,22);
        heFengToXinZhiMapping.put(401,23);
        heFengToXinZhiMapping.put(402,24);
        heFengToXinZhiMapping.put(403,25);
        heFengToXinZhiMapping.put(504,26);
        heFengToXinZhiMapping.put(503,27);
        heFengToXinZhiMapping.put(507,28);
        heFengToXinZhiMapping.put(508,29);
        heFengToXinZhiMapping.put(501,30);
        heFengToXinZhiMapping.put(502,31);
        heFengToXinZhiMapping.put(200,32);
        heFengToXinZhiMapping.put(207,33);
        heFengToXinZhiMapping.put(211,34);
        heFengToXinZhiMapping.put(213,35);
        heFengToXinZhiMapping.put(212,36);
        heFengToXinZhiMapping.put(901,37);
        heFengToXinZhiMapping.put(900,38);
        heFengToXinZhiMapping.put(999,99);
    }



    public interface GetWeatherListener{
        public void done(Weather weather, Exception e);
    }

    private OkHttpClient client = new OkHttpClient.Builder().build();

    public void getWeather(final String city, final GetWeatherListener getWeatherListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://123.206.21.155:8080/WeatherWeb/weather/all?city=" + city+ "&key=" + KEY;
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


    private Weather weather;
    public Weather getSyncWeatherFromHeFeng(final String nowCity){
        try {
            String url = Configuration.HEFENG_URL + "?city=" + nowCity + "&key=" + Configuration.HEFENG_KEY;
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            String body = response.body().string();
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
            if (jsonObject.get("HeWeather5").getAsJsonArray().size() > 0){
                String heWeather5 = jsonObject.get("HeWeather5").getAsJsonArray().get(0).getAsJsonObject().toString();
                Weather weather = new Gson().fromJson(heWeather5, Weather.class);
                return weather;
            }else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


}
