package com.example.admin.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2017/8/17.
 */

public class EditCityActivityAdapter extends RecyclerView.Adapter<EditCityActivityAdapter.ViewHolder> {

    private List<EditCity> meditCityList;

    public EditCityActivityAdapter(List<EditCity> meditCityList) {
        this.meditCityList = meditCityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_edit_city_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //数据
        EditCity editCity = meditCityList.get(position);
        holder.editCityDetail.setText(editCity.getEdit_city_detail());
        holder.editCityProvince.setText(editCity.getEdit_city_province());
        holder.editDeleteCity.setImageResource(editCity.getEdit_delete_imageid());

        holder.editDeleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meditCityList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(0,meditCityList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return meditCityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //界面
        ImageView editDeleteCity;
        TextView editCityDetail;
        TextView editCityProvince;
        View editCityView;



        public ViewHolder(View itemView) {
            super(itemView);
            editCityView = itemView;
            editCityDetail = (TextView) itemView.findViewById(R.id.tv_editcity_detail);
            editCityProvince = (TextView) itemView.findViewById(R.id.tv_editcity_province);
            editDeleteCity = (ImageView)itemView.findViewById(R.id.iv_delete_city);
        }
    }


}
