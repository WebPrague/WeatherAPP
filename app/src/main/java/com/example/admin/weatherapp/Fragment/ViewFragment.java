package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.weatherapp.DuanziActivityAdapter;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.service.QsbkService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/11.
 */

public class ViewFragment extends Fragment {

    private ImageView ivVoteUp;
    private ImageView ivVoteDown;
    private QsbkService qsbkService = null;
    private DuanziActivityAdapter duanziActivityAdapter;
    private List<QsbkService.Qsbk> qsbkList = new ArrayList<QsbkService.Qsbk>();
    RecyclerView mrecyclerView;
    private SwipeRefreshLayout srlDuanZi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view,container,false);
        mrecyclerView = (RecyclerView)view.findViewById(R.id.rv_duanzi_main);
        srlDuanZi = (SwipeRefreshLayout) view.findViewById(R.id.demo_swiperefreshlayout);
        srlDuanZi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initDuanzi(true);
            }
        });

        ivVoteDown = (ImageView)view.findViewById(R.id.iv_laugh);
        ivVoteUp = (ImageView)view.findViewById(R.id.iv_shit);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //横向



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(layoutManager);
        duanziActivityAdapter = new DuanziActivityAdapter(qsbkList);
        mrecyclerView.setAdapter(duanziActivityAdapter);
        qsbkService = new QsbkService(getActivity());

        initDuanzi(false);
    }

    private void initDuanzi(final boolean notify){


        qsbkService.getQsbk(new QsbkService.GetQsbkListener() {
            @Override
            public void getQsbk(List<QsbkService.Qsbk> list, Exception e) {

                if (e == null){
                    qsbkList.removeAll(qsbkList);
                    qsbkList.addAll(list);

                    duanziActivityAdapter.notifyDataSetChanged();
                    if (notify){
                        Toast.makeText(getActivity(),"更新了" + list.size() + "条数据",Toast.LENGTH_LONG).show();
                    }
                }else{

                }
                srlDuanZi.setRefreshing(false);
            }
        });



    }

}
