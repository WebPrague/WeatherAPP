package com.example.admin.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.weatherapp.weather.DailyForecast;
import com.example.admin.weatherapp.weather.WeatherService;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */

public class WeatherLineViewAdapter extends RecyclerView.Adapter<WeatherLineViewAdapter.ViewHolder> {

    private List<WeatherForecast> mweatherForecastList;


    ImageOptions imageOptions = new ImageOptions.Builder()
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            // 默认自动适应大小
            // .setSize(...)
            .setIgnoreGif(true)
            // 如果使用本地文件url, 添加这个设置可以在本地文件更新后刷新立即生效.
            .setUseMemCache(true)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP).build();

    public WeatherLineViewAdapter(List<WeatherForecast> weatherForecastList) {
        this.mweatherForecastList = weatherForecastList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_weatherline_item,parent,false);
        //初始化ViewHolder
        final ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherForecast weatherForecast = mweatherForecastList.get(position);
        holder.weatherDateTitle.setText(weatherForecast.getDate_title());
        holder.weatherDateDetail.setText(weatherForecast.getDate_detail());
//        holder.weatherMaxImage.setImageResource(weatherForecast.getMax_imageid());
//        holder.weatherMinImage.setImageResource(weatherForecast.getMin_imageid());
        x.image().bind(holder.weatherMaxImage,"assets://weather/" +  WeatherService.heFengToXinZhiMapping.get(weatherForecast.getMax_imageid()) + ".png", imageOptions);
        x.image().bind(holder.weatherMinImage, "assets://weather/" +  WeatherService.heFengToXinZhiMapping.get(weatherForecast.getMin_imageid()) + ".png", imageOptions);
        holder.weatherMaxWeather.setText(weatherForecast.getMax_weather());
        holder.weatherMinWeather.setText(weatherForecast.getMin_weather());
        holder.weatherWind.setText(weatherForecast.getWind());
        holder.weatherWindSpeed.setText(weatherForecast.getWind_speed());
        holder.weatherImageAirQuality.setImageResource(weatherForecast.getImage_air_quality());
    }



    @Override
    public int getItemCount() {
        return mweatherForecastList.size();
    }



    //防止不断初始化对象
    public class ViewHolder extends RecyclerView.ViewHolder{

        //界面
        TextView weatherDateTitle;
        TextView weatherDateDetail;
        ImageView weatherMaxImage;
        TextView weatherMaxWeather;
        ImageView weatherMinImage;
        TextView weatherMinWeather;
        TextView weatherWind;
        TextView weatherWindSpeed;
        ImageView weatherImageAirQuality;
        View weatherView;

        public ViewHolder(View itemView) {
            super(itemView);
            weatherView = itemView;
            weatherDateTitle = (TextView) itemView.findViewById(R.id.tv_date_title);
            weatherDateDetail = (TextView) itemView.findViewById(R.id.tv_date_detail);
            weatherMaxImage = (ImageView) itemView.findViewById(R.id.iv_max_image);
            weatherMinImage = (ImageView) itemView.findViewById(R.id.iv_min_image);
            weatherMaxWeather = (TextView) itemView.findViewById(R.id.tv_max_weather);
            weatherMinWeather = (TextView) itemView.findViewById(R.id.tv_min_weather);
            weatherWind = (TextView) itemView.findViewById(R.id.tv_wind);
            weatherWindSpeed = (TextView) itemView.findViewById(R.id.tv_wind_speed);
            weatherImageAirQuality = (ImageView) itemView.findViewById(R.id.iv_air_quality);
        }
    }


}
