package com.example.admin.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.weatherapp.db.WeatherCity;
import com.example.admin.weatherapp.weather.Weather;
import com.example.admin.weatherapp.weather.WeatherService;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by admin on 2017/8/16.
 */

public class AddCityActivityAdapter extends RecyclerView.Adapter<AddCityActivityAdapter.ViewHolder> {

    public static interface OnAddCityClickListener{
        public void onAddCityClick(String city);
    }
    public OnAddCityClickListener onAddCityClickListener;

    private List<WeatherCity> mcityWeatherList;

    ImageOptions imageOptions = new ImageOptions.Builder()
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            // 默认自动适应大小
            // .setSize(...)
            .setIgnoreGif(true)
            // 如果使用本地文件url, 添加这个设置可以在本地文件更新后刷新立即生效.
            .setUseMemCache(true)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP).build();

    public AddCityActivityAdapter(List<WeatherCity> mcityWeatherList, OnAddCityClickListener onAddCityClickListener) {
        this.onAddCityClickListener = onAddCityClickListener;
        this.mcityWeatherList = mcityWeatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_addcity_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.cityDetailName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddCityClickListener.onAddCityClick((String) view.getTag());
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //数据
        WeatherCity weatherCity = mcityWeatherList.get(position);
        holder.cityDetailName.setText(weatherCity.getCity());
        holder.cityProvince.setText(weatherCity.getProvince());

        x.image().bind(holder.cityWeatherTmpImage,"assets://weather/" +  WeatherService.heFengToXinZhiMapping.get(weatherCity.getCondCode()) + ".png", imageOptions);
        //holder.cityWeatherTmpImage.setImageResource(cityWeather.getWeather_imageid());
        holder.cityWeatherTmp.setText(weatherCity.getTmp() + "°");
        holder.cityQulity.setText("空气"+weatherCity.getAirQuality());
        holder.cityShidu.setText("湿度"+weatherCity.getHum()+"%");
        holder.cityWind.setText(weatherCity.getWindDir() );
        holder.cityMaxandMin.setText(weatherCity.getTmpMax()+"°/"+weatherCity.getTmpMin()+"°");

        holder.cityDetailName.setTag(mcityWeatherList.get(position).getCity());
    }


    @Override
    public int getItemCount() {
        return mcityWeatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //界面
        TextView cityDetailName;
        TextView cityProvince;
        ImageView cityWeatherTmpImage;
        TextView cityWeatherTmp;
        TextView cityQulity;
        TextView cityShidu;
        TextView cityWind;
        TextView cityMaxandMin;
        View addCityView;

        public ViewHolder(View itemView) {
            super(itemView);
            addCityView = itemView;
            cityDetailName = (TextView) itemView.findViewById(R.id.tv_city_detail);
            cityProvince = (TextView) itemView.findViewById(R.id.tv_city_province);
            cityWeatherTmp = (TextView) itemView.findViewById(R.id.tv_city_tmp);
            cityQulity = (TextView) itemView.findViewById(R.id.tv_city_qulity);
            cityShidu = (TextView) itemView.findViewById(R.id.tv_city_shidu);
            cityWind = (TextView) itemView.findViewById(R.id.tv_city_wind);
            cityMaxandMin = (TextView) itemView.findViewById(R.id.tv_city_max_and_min);
            cityWeatherTmpImage= (ImageView) itemView.findViewById(R.id.iv_city_weather_image);

        }
    }
}
