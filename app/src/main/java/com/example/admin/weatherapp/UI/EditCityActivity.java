package com.example.admin.weatherapp.UI;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.admin.weatherapp.EditCity;
import com.example.admin.weatherapp.EditCityActivityAdapter;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.db.WeatherCity;
import com.example.admin.weatherapp.weather.Weather;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EditCityActivity extends BaseActivity {

    private Button btnOkEditcity;
    private Button btnCancelcity;
    private List<WeatherCity> weatherCityList = new ArrayList<WeatherCity>();
    private List<Integer>deletedCityList = new ArrayList<Integer>();
    private DbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_city);
        initState();

        //Db
        db = x.getDb(daoConfig);

        try {
            weatherCityList.addAll(db.findAll(WeatherCity.class));
        } catch (DbException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_editcity_main);
        //横向
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        EditCityActivityAdapter adapter = new EditCityActivityAdapter(weatherCityList, new EditCityActivityAdapter.OnCityDeletedListener() {
            @Override
            public void onCityDeleted(int cityId) {
                deletedCityList.add(cityId);
            }
        });
        recyclerView.setAdapter(adapter);

        btnOkEditcity = (Button)findViewById(R.id.btn_ok_editcity);
        btnOkEditcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存并返回
                for (int cityId : deletedCityList){
                    try {
                        db.deleteById(WeatherCity.class, cityId);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                finish();
            }
        });

        btnCancelcity = (Button)findViewById(R.id.btn_quxiao_editcity);
        btnCancelcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(EditCityActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("更改尚未保存，您确定要离开吗?")
                        .setContentText("")
                        .setCancelText("我再看看")
                        .setConfirmText("去意已决")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                                finish();
                            }
                        })
                        .show();
                //若未进行修改，则直接返回

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


}
