package com.example.admin.weatherapp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class EditCityActivity extends AppCompatActivity {

    private Button btnOkEditcity;
    private Button btnCancelcity;
    private List<EditCity> editCityList = new ArrayList<EditCity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_city);
        initState();
        initEditCity();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_editcity_main);
        //横向
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        EditCityActivityAdapter adapter = new EditCityActivityAdapter(editCityList);
        recyclerView.setAdapter(adapter);

        btnOkEditcity = (Button)findViewById(R.id.btn_ok_editcity);
        btnOkEditcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存并返回
                finish();
            }
        });

        btnCancelcity = (Button)findViewById(R.id.btn_quxiao_editcity);
        btnCancelcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //若未进行修改，则直接返回
                finish();

                //若进行删除操作，则弹出提示框
            }
        });


    }

    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initEditCity(){

        EditCity editCity01 = new EditCity("阿尔山","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity01);
        EditCity editCity02 = new EditCity("突泉","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity02);
        EditCity editCity03 = new EditCity("五岔沟","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity03);
        EditCity editCity04 = new EditCity("五岔沟","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity04);
        EditCity editCity05 = new EditCity("五岔沟","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity05);
        EditCity editCity06 = new EditCity("五岔沟","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity06);
        EditCity editCity07 = new EditCity("五岔沟","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity07);
        EditCity editCity08 = new EditCity("五岔沟","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity08);
        EditCity editCity09 = new EditCity("突泉","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity09);
        EditCity editCity10 = new EditCity("突泉","乌兰浩特，内蒙古",R.drawable.delete_city);
        editCityList.add(editCity10);


    }

}
