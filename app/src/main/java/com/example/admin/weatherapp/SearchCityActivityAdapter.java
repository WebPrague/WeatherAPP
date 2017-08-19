package com.example.admin.weatherapp;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by admin on 2017/8/19.
 */

public class SearchCityActivityAdapter extends RecyclerView.Adapter<SearchCityActivityAdapter.ViewHolder> {

    private List<SearchCity> msearchCityList;

    public SearchCityActivityAdapter(List<SearchCity> msearchCityList) {
        this.msearchCityList = msearchCityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_city_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);

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
