package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

/**
 * Created by admin on 2017/8/11.
 */

public class WeatherFragment extends Fragment {
    private TextView tvTmp;
    private TextView tvCond;

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

        //初始化控件
        tvTmp = (TextView)view.findViewById(R.id.tv_tmp);
        tvCond = (TextView)view.findViewById(R.id.tv_cond);


        //赋值控件变量
        weatherService = new WeatherService();
        weatherService.getWeather("沈阳", new WeatherService.GetWeatherListener() {
            @Override
            public void done(Weather weather, Exception e) {
                if (e == null){
                    tvTmp.setText(weather.now.tmp+"°");
                    tvCond.setText(weather.now.cond.txt);
                }else {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
