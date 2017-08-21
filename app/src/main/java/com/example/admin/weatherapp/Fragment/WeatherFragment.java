package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.admin.weatherapp.HourForcast;
import com.example.admin.weatherapp.HourForecastAdapter;
import com.example.admin.weatherapp.UI.AddCityActivity;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.WeatherForecast;
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

import static com.example.admin.weatherapp.R.drawable.view;

/**
 * Created by admin on 2017/8/11.
 */

public class WeatherFragment extends Fragment {

    public LocationClient mLocationClient = null;

    public BDAbstractLocationListener myListener = new MyLocationListener();

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


    private static WeatherService weatherService = null;
    public static Weather weather;

    private void initLBS(){

        if (mLocationClient != null){
            if (mLocationClient.isStarted()){
                mLocationClient.stop();
            }
        }
        mLocationClient = new LocationClient(this.getActivity().getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        //option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        //option.setIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        //option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();


    }

    private void locationSuccess(BDLocation bdLocation){

        //tvCity.setText();
        tvCity.setText(bdLocation.getCity());
        tvStreet.setText(bdLocation.getStreet());

        String location;
        String locationDetail;

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("config",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString()

        //Toast.makeText(getActivity(),bdLocation.getProvince() + bdLocation.getCity() + bdLocation.getDistrict() + bdLocation.getStreet() + " " + bdLocation.getLatitude() + "," + bdLocation.getLongitude(),Toast.LENGTH_SHORT).show();
    }
    private void locationFail(String msg){

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
    public String getFormatTime(String date){
        return date.substring(10,16);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initLBS();
        //赋值控件变量
        weatherService = new WeatherService();
        weatherService.getWeather("沈阳市", new WeatherService.GetWeatherListener() {
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


    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
            location.getTime();    //获取定位时间
            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息
            location.getRadius();    //获取定位精准度
            location.getAddrStr();    //获取地址信息
            location.getCountry();    //获取国家信息
            location.getCountryCode();    //获取国家码
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息

            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息

            if (location.getLocType() == BDLocation.TypeGpsLocation){

                //当前为GPS定位结果，可获取以下信息
                location.getSpeed();    //获取当前速度，单位：公里每小时
                location.getSatelliteNumber();    //获取当前卫星数
                location.getAltitude();    //获取海拔高度信息，单位米
                location.getDirection();    //获取方向信息，单位度
                locationSuccess(location);

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

                locationSuccess(location);
                //当前为网络定位结果，可获取以下信息
                location.getOperators();    //获取运营商信息

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                locationSuccess(location);
                //当前为网络定位结果

            } else if (location.getLocType() == BDLocation.TypeServerError) {
                locationFail("网络定位失败");
                //当前网络定位失败
                //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                locationFail("当前网络不通");
                //当前网络不通

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                locationFail("用户没有授权");
                //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
                //可进一步参考onLocDiagnosticMessage中的错误返回码

            }
            mLocationClient.stop();
        }


    }

}
