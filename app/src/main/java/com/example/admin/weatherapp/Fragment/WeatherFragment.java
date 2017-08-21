package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.weatherapp.HourForcast;
import com.example.admin.weatherapp.HourForecastAdapter;
import com.example.admin.weatherapp.UI.AddCityActivity;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.WeatherForecast;
import com.example.admin.weatherapp.weather.DailyForecast;
import com.example.admin.weatherapp.weather.HourlyForecast;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.example.admin.weatherapp.R.drawable.view;

/**
 * Created by admin on 2017/8/11.
 */

public class WeatherFragment extends Fragment {

    ImageOptions imageOptions = new ImageOptions.Builder()
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            // 默认自动适应大小
            // .setSize(...)
            .setIgnoreGif(true)
            // 如果使用本地文件url, 添加这个设置可以在本地文件更新后刷新立即生效.
            .setUseMemCache(true)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP).build();

    private List<HourForcast> hourForcastList = new ArrayList<HourForcast>();
    private RecyclerView mRecycleView;


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
    private ImageView ivTodayImage;
    private TextView tvMaxMin;
    private TextView tvTomorrowText;
    private TextView tvTomorrowDir;
    private TextView tvTomorrowMaxMin;
    private ImageView ivTomorrowImage;
    private TextView tvAfterText;
    private TextView tvAfterDir;
    private TextView tvAfterMaxMin;
    private ImageView ivAfterImage;
    private TextView tvSuggestionDress;
    private TextView tvSuggestionSunshine;
    private TextView tvSuggestionTravel;
    private TextView tvSuggestionSport;
    private TextView tvSuggestionDrive;

    private static WeatherService weatherService = null;
    public static Weather weather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather,container,false);
        mRecycleView = (RecyclerView) view.findViewById(R.id.rv_hourly_weather_main);
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
        tvSuggestionDress = (TextView)view.findViewById(R.id.tv_suggestion_dress);
        tvSuggestionSunshine = (TextView)view.findViewById(R.id.tv_suggestion_sunshine);
        tvSuggestionTravel = (TextView)view.findViewById(R.id.tv_suggestion_travel);
        tvSuggestionSport = (TextView)view.findViewById(R.id.tv_suggestion_sport);
        tvSuggestionDrive = (TextView)view.findViewById(R.id.tv_suggestion_drive);


        ivTodayImage = (ImageView)view.findViewById(R.id.iv_today_image);
        ivTomorrowImage = (ImageView)view.findViewById(R.id.iv_tomorrow_image);
        ivAfterImage = (ImageView)view.findViewById(R.id.iv_after_image);


        return view;
    }

    private void initHourForecast(){
//        HourForcast hour01 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour01);
//        HourForcast hour02 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour02);
//        HourForcast hour03 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour03);
//        HourForcast hour04 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour04);
//        HourForcast hour05 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour05);
//        HourForcast hour06 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour06);
//        HourForcast hour07 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour07);
//        HourForcast hour08 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour08);
//        HourForcast hour09 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour09);
//        HourForcast hour10 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour10);
//        HourForcast hour11 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour11);
//        HourForcast hour12 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour12);
//        HourForcast hour13 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour13);
//        HourForcast hour14 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour14);
//        HourForcast hour15 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour15);
//        HourForcast hour16 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour16);
//        HourForcast hour17 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour17);
//        HourForcast hour18 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour18);
//        HourForcast hour19 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour19);
//        HourForcast hour20 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour20);
//        HourForcast hour21 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour21);
//        HourForcast hour22 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour22);
//        HourForcast hour23 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour23);
//        HourForcast hour24 = new HourForcast(R.drawable.qing,"1级","43优","09:00");
//        hourForcastList.add(hour24);

        //横向
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);

        mRecycleView.setLayoutManager(layoutManager);
        HourForecastAdapter adapter = new HourForecastAdapter(hourForcastList);
        mRecycleView.setAdapter(adapter);

        Weather weather = WeatherFragment.weather;
        for (HourlyForecast hourlyForecast : weather.hourly_forecast){
            HourForcast hourForcast = new HourForcast(Integer.parseInt(hourlyForecast.cond.code), hourlyForecast.wind.dir+"风",hourlyForecast.wind.spd + "km/h",getFormatTime(hourlyForecast.date));

            hourForcastList.add(hourForcast);
        }

        //Integer.parseInt(hourlyForecast.cond.code)

    }
    public String getFormatTime(String date){
        return date.substring(10,16);
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
                    WeatherFragment.weather = weather;
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
                    tvSuggestionDress.setText(weather.suggestion.drsg.txt);
                    tvSuggestionSunshine.setText(weather.suggestion.uv.txt);
                    tvSuggestionTravel.setText(weather.suggestion.trav.txt);
                    tvSuggestionSport.setText(weather.suggestion.sport.txt);
                    tvSuggestionDrive.setText(weather.suggestion.cw.txt);

                    x.image().bind(ivTodayImage,"assets://weather/" +  WeatherService.heFengToXinZhiMapping.get(Integer.parseInt(weather.daily_forecast.get(0).cond.code_d)) + ".png", imageOptions);

                    //ivTodayImage.setImageResource(Integer.parseInt(weather.daily_forecast.get(0).cond.code_d));
                    //ivTomorrowImage.setImageResource(Integer.parseInt(weather.daily_forecast.get(1).cond.code_d));
                    x.image().bind(ivTomorrowImage,"assets://weather/" +  WeatherService.heFengToXinZhiMapping.get(Integer.parseInt(weather.daily_forecast.get(1).cond.code_d)) + ".png", imageOptions);

                    //ivAfterImage.setImageResource(Integer.parseInt(weather.daily_forecast.get(2).cond.code_d));

                    x.image().bind(ivAfterImage,"assets://weather/" +  WeatherService.heFengToXinZhiMapping.get(Integer.parseInt(weather.daily_forecast.get(2).cond.code_d)) + ".png", imageOptions);
                    initHourForecast();
                }else {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
