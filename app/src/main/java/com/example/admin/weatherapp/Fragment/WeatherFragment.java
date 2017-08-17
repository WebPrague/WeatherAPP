package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.weatherapp.AddCityActivity;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

/**
 * Created by admin on 2017/8/11.
 */

public class WeatherFragment extends Fragment {
    private TextView tvTmp;
    private TextView tvCond;
    private TextView tvQlty;
    private TextView tvAqi;
    private TextView tvNowWindDir;
    private TextView tvNowWindSpd;
    private TextView tvNowHum;
    private TextView tvNowFl;
    private TextView tvTodayText;
    private TextView tvTodayDir;
    private TextView tvMaxMin;
    private TextView tvTomorrowText;
    private TextView tvTomorrowDir;
    private TextView tvTomorrowMaxMin;
    private TextView tvAfterText;
    private TextView tvAfterDir;
    private TextView tvAfterMaxMin;

    private WeatherService weatherService = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather,container,false);

        Button btn_weatherline = (Button) view.findViewById(R.id.btn_longweather);
        btn_weatherline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),WeatherLineView.class);
                startActivity(intent);
            }
        });

       ImageView tvAddCity= (ImageView)view.findViewById(R.id.iv_add_city);
        tvAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddCityActivity.class);
                startActivity(intent);
            }
        });
        Typeface robotoThinTypeface = Typeface.createFromAsset(getResources().getAssets(), "Roboto-Thin.ttf");



        //初始化控件
        tvTmp = (TextView)view.findViewById(R.id.tv_tmp);
        tvTmp.setTypeface(robotoThinTypeface);
        tvCond = (TextView)view.findViewById(R.id.tv_cond);
        tvQlty = (TextView)view.findViewById(R.id.aqi_qlty);
        tvAqi = (TextView)view.findViewById(R.id.tv_aqi);
        tvNowWindDir = (TextView)view.findViewById(R.id.now_wind_dir);
        tvNowWindSpd = (TextView)view.findViewById(R.id.tv_now_wind_spd);
        tvNowHum = (TextView)view.findViewById(R.id.tv_now_hum);
        tvNowFl = (TextView)view.findViewById(R.id.tv_now_fl);
        tvTodayText = (TextView)view.findViewById(R.id.tv_today_text);
        tvTodayDir = (TextView)view.findViewById(R.id.tv_today_dir);
        tvMaxMin = (TextView)view.findViewById(R.id.tv_max_min);
        tvTomorrowText = (TextView)view.findViewById(R.id.tv_tomorrow_text);
        tvTomorrowDir = (TextView)view.findViewById(R.id.tv_tomorrow_dir);
        tvTomorrowMaxMin = (TextView)view.findViewById(R.id.tv_tomorrow_max_min);
        tvAfterText = (TextView)view.findViewById(R.id.tv_after_text);
        tvAfterDir = (TextView)view.findViewById(R.id.tv_after_dir);
        tvAfterMaxMin = (TextView)view.findViewById(R.id.tv_after_max_min);


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //赋值控件变量
        weatherService = new WeatherService();
        weatherService.getWeather("沈阳", new WeatherService.GetWeatherListener() {
            @Override
            public void done(Weather weather, Exception e) {
                if (e == null){
                    tvTmp.setText(weather.now.tmp+"°");
                    tvCond.setText(weather.now.cond.txt);
                    tvQlty.setText("空气"+weather.aqi.city.qlty);
                    tvAqi.setText(weather.aqi.city.aqi);
                    tvNowWindDir.setText(weather.now.wind.dir);
                    tvNowWindSpd.setText(weather.now.wind.spd+"km/h");
                    tvNowHum.setText(weather.now.hum+"%");
                    tvNowFl.setText(weather.now.fl+"°");
                    tvTodayText.setText(weather.daily_forecast.get(0).cond.txt_d);
                    tvTodayDir.setText(weather.daily_forecast.get(0).wind.dir);
                    tvMaxMin.setText(weather.daily_forecast.get(0).tmp.max+"°/"+weather.daily_forecast.get(0).tmp.min+"°");
                    tvTomorrowText.setText(weather.daily_forecast.get(1).cond.txt_d);
                    tvTomorrowDir.setText(weather.daily_forecast.get(1).wind.dir);
                    tvTomorrowMaxMin.setText(weather.daily_forecast.get(1).tmp.max+"°/"+weather.daily_forecast.get(1).tmp.min+"°");
                    tvAfterText.setText(weather.daily_forecast.get(2).cond.txt_d);
                    tvAfterDir.setText(weather.daily_forecast.get(2).wind.dir);
                    tvAfterMaxMin.setText(weather.daily_forecast.get(2).tmp.max+"°/"+weather.daily_forecast.get(2).tmp.min+"°");

                }else {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
