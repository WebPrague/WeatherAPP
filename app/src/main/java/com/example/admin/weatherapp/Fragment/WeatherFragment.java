package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.admin.weatherapp.HourForcast;
import com.example.admin.weatherapp.HourForecastAdapter;
import com.example.admin.weatherapp.UI.AddCityActivity;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.WeatherForecast;
import com.example.admin.weatherapp.view.IndexHorizontalScrollView;
import com.example.admin.weatherapp.view.Today24HourView;
import com.example.admin.weatherapp.weather.DailyForecast;
import com.example.admin.weatherapp.weather.HourlyForecast;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

import org.w3c.dom.Text;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.admin.weatherapp.R.drawable.view;

/**
 * Created by admin on 2017/8/11.
 */

public class WeatherFragment extends Fragment implements AMapLocationListener{
//
//    //获取位置城市
   private String location = null;

    //获取位置的详细描述
    private String locationDetail = null;

    //LBS

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;

    private SweetAlertDialog pDialog;

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
    private TextView tvCity;
    private TextView tvStreet;
    private ImageView ivDirCity;

    private IndexHorizontalScrollView indexHorizontalScrollView;
    private Today24HourView today24HourView;


    private AddCityActivity.CitySelectedBroadcastReceiver citySelectedBroadcastReceiver;

    //天气相关
    private static WeatherService weatherService = null;
    public static Weather weather;

    private void initLBS(){

        //添加控件
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("定位中，请稍后");
        pDialog.setCancelable(false);
        pDialog.show();

        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);

        //声明AMapLocationClientOption对象

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setInterval(1000);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setWifiActiveScan(false);
        mLocationOption.setMockEnable(true);
        mLocationOption.setHttpTimeOut(20000);
        mLocationOption.setLocationCacheEnable(true);


        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }


    /*
    * 从本地存储中获取位置信息
    * */
    private String getLocationFromLocal(){

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("config",getActivity().MODE_PRIVATE);
        this.location = sharedPreferences.getString("location",null);
        return this.location;
    }


    /*
    * 从本地存储中获取位置的详细信息
    * */
    private String getLocationDetailFromLocal(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("config",getActivity().MODE_PRIVATE);
        this.locationDetail = sharedPreferences.getString("street","");
        return this.locationDetail;
    }




    private void locationSuccess(AMapLocation aMapLocation){
       // Toast.makeText(getActivity(), aMapLocation.getProvince() + aMapLocation.getCity() + aMapLocation.getDistrict() + aMapLocation.getStreet(),Toast.LENGTH_LONG).show();
        this.tvCity.setText(aMapLocation.getCity());
        this.tvStreet.setText(aMapLocation.getStreet());
        this.location = aMapLocation.getCity();
        this.locationDetail = aMapLocation.getStreet();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("location", aMapLocation.getCity());
        editor.putString("street",  aMapLocation.getStreet());
        editor.commit();


        initialWeather();
    }
    private void locationFail(String msg){

    }

    private BroadcastReceiver broadcastReceiver;

    @Override
    public void onAttach(Context context) {



        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

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

        tvCity = (TextView)view.findViewById(R.id.tv_city);
        tvStreet = (TextView)view.findViewById(R.id.tv_street);
        ivDirCity = (ImageView)view.findViewById(R.id.iv_dir_city);

        indexHorizontalScrollView = (IndexHorizontalScrollView) view.findViewById(R.id.index_horizontal_scrollView);
        today24HourView = (Today24HourView) view.findViewById(R.id.today_24_hour_view);
        indexHorizontalScrollView.setToday24HourView(today24HourView);

        ivDirCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initLBS();
            }
        });
        return view;
    }

    private void initHourForecast(){

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

    private void updateHourlyForecastNew(List<HourlyForecast> hourlyForecasts){
        int temp[] = new int[24];
        int weatherRes[] = new int[24];
        for (int i = 0 ; i < hourlyForecasts.size(); i ++){
            temp[i] = Integer.parseInt(hourlyForecasts.get(i).tmp);
            weatherRes[i] = WeatherService.heFengToXinZhiMapping.get(Integer.parseInt(hourlyForecasts.get(i).cond.code));
        }
        int timeStart = 0;
        try {
            timeStart = Integer.parseInt(new SimpleDateFormat("H").format(new Date(System.currentTimeMillis())));
        }catch (Exception e){

        }
        this.today24HourView.setData(temp, weatherRes, timeStart);
    }


    public String getFormatTime(String date){
        return date.substring(10,16);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter("hupeng");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String city = intent.getStringExtra("city");
                tvCity.setText(city);
                tvStreet.setText("");
                location = city;
//                locationDetail = aMapLocation.getStreet();

                initialWeather();
            }
        };

        getActivity().registerReceiver(broadcastReceiver, intentFilter);

        //天气服务
        weatherService = new WeatherService();
        getLocationFromLocal();
        getLocationDetailFromLocal();
        if (this.location == null){
            initLBS();
        }else {
            this.tvCity.setText(this.location);
            this.tvStreet.setText(this.locationDetail);
            initialWeather();
        }
    }
    private void initialWeather(){
        //赋值控件变量
        if (weatherService == null){
            weatherService = new WeatherService();
        }
        weatherService.getWeather(this.location, new WeatherService.GetWeatherListener() {
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
                    updateHourlyForecastNew(weather.hourly_forecast);
                }else {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        pDialog.cancel();


        if (aMapLocation.getErrorCode() == 0){
            final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("定位成功")
                    .setContentText("");
            sweetAlertDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    sweetAlertDialog.cancel();
                }
            },1500);

            locationSuccess(aMapLocation);
        }else {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("定位失败")
                    .setContentText(aMapLocation.getErrorInfo())
                    .show();
            locationFail(aMapLocation.getErrorInfo());
        }

        if (mLocationClient.isStarted()){
            mLocationClient.stopLocation();
        }
    }
}
