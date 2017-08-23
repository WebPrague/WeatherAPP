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
 * Created by admin on 2017/8/17.
 */
public class EditCityActivityAdapter extends RecyclerView.Adapter<EditCityActivityAdapter.ViewHolder> {

    public interface OnCityDeletedListener{
        public void onCityDeleted(int cityId);
    }

    private List<WeatherCity> weatherCityList;
    private OnCityDeletedListener onCityDeletedListener;
    public EditCityActivityAdapter(List<WeatherCity> weatherCityList, OnCityDeletedListener onCityDeletedListener) {
        this.weatherCityList = weatherCityList;
        this.onCityDeletedListener = onCityDeletedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_edit_city_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    ImageOptions imageOptions = new ImageOptions.Builder()
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            // 默认自动适应大小
            // .setSize(...)
            .setIgnoreGif(true)
            // 如果使用本地文件url, 添加这个设置可以在本地文件更新后刷新立即生效.
            .setUseMemCache(true)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP).build();

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //数据
        final WeatherCity weatherCity = weatherCityList.get(position);
        holder.editCityDetail.setText(weatherCity.getCity());
        holder.editCityProvince.setText(weatherCity.getProvince());
       //  holder.editDeleteCity.setImageResource(weatherCity.getEdit_delete_imageid());
       // x.image().bind(holder.e, "assets://weather/" +  WeatherService.heFengToXinZhiMapping.get(weatherCity.getCondCode()) + ".png", imageOptions);

        holder.editDeleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCityDeletedListener.onCityDeleted(weatherCityList.get(position).getId());
                weatherCityList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(0,weatherCityList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return weatherCityList.size();
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
