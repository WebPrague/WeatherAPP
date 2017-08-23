package com.example.admin.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.weatherapp.UI.AddCityActivity;
import com.example.admin.weatherapp.UI.SearchCityActivity;
import com.example.admin.weatherapp.db.WeatherCity;

import java.util.List;

/**
 * Created by admin on 2017/8/19.
 */

public class SearchCityActivityAdapter extends RecyclerView.Adapter<SearchCityActivityAdapter.ViewHolder> {
    public static interface OnCityClickListener{
        public void onCityClick(String city);
    }

    private OnCityClickListener onCityClickListener;

    private List<SearchCity> msearchCityList;

    public SearchCityActivityAdapter(List<SearchCity> msearchCityList, OnCityClickListener onCityClickListener) {
        this.msearchCityList = msearchCityList;
        this.onCityClickListener = onCityClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_city_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);

        //给view添加点击事件
        holder.searchCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                SearchCity searchCity = msearchCityList.get(position);

                onCityClickListener.onCityClick(searchCity.getCityName());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //数据

        SearchCity searchCity = msearchCityList.get(position);
        holder.searchCityName.setText(searchCity.getCityName());
    }

    @Override
    public int getItemCount() {
        return msearchCityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Button searchCityName;
        View searchCityView;


        public ViewHolder(View itemView) {
            super(itemView);

            searchCityView = itemView;
            searchCityName = (Button) itemView.findViewById(R.id.btn_search_city_name);

        }
    }
}
