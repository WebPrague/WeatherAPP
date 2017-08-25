package com.example.admin.weatherapp;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.weatherapp.service.QsbkService;

import org.xutils.x;

import java.util.List;

/**
 * Created by ZhangPeng on 2017/8/25.
 */

public class DuanziActivityAdapter extends RecyclerView.Adapter<DuanziActivityAdapter.ViewHolder> {

    private List<QsbkService.Qsbk> qsbkList;

    public DuanziActivityAdapter(List<QsbkService.Qsbk> qsbkList) {
        this.qsbkList = qsbkList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_duanzi_item,parent,false);
        final DuanziActivityAdapter.ViewHolder holder = new DuanziActivityAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //数据

        QsbkService.Qsbk qsbk = qsbkList.get(position);

        holder.tvDuanziUsername.setText(qsbk.getNickName());
        holder.tvDuanzi.setText(qsbk.getContent());
        holder.tvVoteDown.setText(qsbk.getVoteDown() + "");
        holder.tvVoteUp.setText(qsbk.getVoteUp() + "");
        //holder.ivDuanziImage.setImageResource(Integer.parseInt(qsbk.getUserPhoto()));
        x.image().bind(holder.ivDuanziImage,qsbk.getUserPhoto());

    }

    @Override
    public int getItemCount() {
        return qsbkList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

       ImageView ivDuanziImage;
       TextView tvDuanziUsername;
       TextView tvDuanzi;
       TextView tvVoteUp;
       TextView tvVoteDown;
       View duanziView;

        public ViewHolder(View itemView) {
            super(itemView);

            duanziView  = itemView;
            ivDuanziImage = (ImageView)itemView.findViewById(R.id.iv_duanzi_image);
            tvDuanzi = (TextView)itemView.findViewById(R.id.tv_duanzi);
            tvDuanziUsername = (TextView)itemView.findViewById(R.id.tv_duanzi_username);
            tvVoteUp = (TextView)itemView.findViewById(R.id.tv_voteup);
            tvVoteDown = (TextView)itemView.findViewById(R.id.tv_votedown);
        }
    }
}
