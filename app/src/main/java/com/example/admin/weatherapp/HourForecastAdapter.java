package com.example.admin.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.weatherapp.weather.WeatherService;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/21.
 */

public class HourForecastAdapter extends RecyclerView.Adapter<HourForecastAdapter.ViewHolder> {
    private List<HourForcast> mhourForecastList;

    ImageOptions imageOptions = new ImageOptions.Builder()
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            // 默认自动适应大小
            // .setSize(...)
            .setIgnoreGif(true)
            // 如果使用本地文件url, 添加这个设置可以在本地文件更新后刷新立即生效.
            .setUseMemCache(true)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP).build();


    public HourForecastAdapter(List<HourForcast> mhourForecastList) {
        this.mhourForecastList = mhourForecastList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hourly_weather_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HourForcast hourForcast = mhourForecastList.get(position);
        //holder.hourWeatherImage.setImageResource(hourForcast.getHourWeatherImageId());
        x.image().bind(holder.hourWeatherImage,"assets://weather/" +  WeatherService.heFengToXinZhiMapping.get(hourForcast.getHourWeatherImageId()) + ".png", imageOptions);
        holder.hourWind.setText(hourForcast.getHourWind());
        holder.hourWeatherQulity.setText(hourForcast.getHourWeatherQulity());
        holder.hourTime.setText(hourForcast.getHourTime());
    }

    @Override
    public int getItemCount() {
        return mhourForecastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hourWeatherImage;
        TextView hourWind;
        TextView hourWeatherQulity;
        TextView hourTime;
        View hourView;

        public ViewHolder(View itemView) {
            super(itemView);

            hourView = itemView;
            hourWeatherImage = (ImageView)itemView.findViewById(R.id.iv_hour_weather);
            hourWind = (TextView)itemView.findViewById(R.id.tv_hour_wind);
            hourWeatherQulity =(TextView)itemView.findViewById(R.id.tv_hour_weather_qulity);
            hourTime = (TextView)itemView.findViewById(R.id.tv_hour_time);
        }
    }
}
