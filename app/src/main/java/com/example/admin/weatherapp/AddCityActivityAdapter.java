package com.example.admin.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2017/8/16.
 */

public class AddCityActivityAdapter extends RecyclerView.Adapter<AddCityActivityAdapter.ViewHolder> {
    private List<CityWeather> mcityWeatherList;

    public AddCityActivityAdapter(List<CityWeather> mcityWeatherList) {
        this.mcityWeatherList = mcityWeatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_addcity_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //数据
        CityWeather cityWeather = mcityWeatherList.get(position);
        holder.cityDetailName.setText(cityWeather.getDetail_city());
        holder.cityProvince.setText(cityWeather.getProvince());
        holder.cityWeatherTmpImage.setImageResource(cityWeather.getWeather_imageid());
        holder.cityWeatherTmp.setText(cityWeather.getCity_weather_tmp());
        holder.cityQulity.setText("空气"+cityWeather.getCity_qulity());
        holder.cityShidu.setText("湿度"+cityWeather.getCity_shidu()+"%");
        holder.cityWind.setText(cityWeather.getCity_wind());
        holder.cityMaxandMin.setText(cityWeather.getCity_max_tmp()+"°/"+cityWeather.getCity_min_tmp()+"°");

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
