package com.example.admin.weatherapp.UI;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.admin.weatherapp.CityRepo;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.SearchCity;
import com.example.admin.weatherapp.SearchCityActivityAdapter;
import com.example.admin.weatherapp.db.CityForecast;
import com.example.admin.weatherapp.db.WeatherCity;

import com.example.admin.weatherapp.db.WeatherCity;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class SearchCityActivity extends BaseActivity {



    private List<CityForecast> cityForecasts = new ArrayList<CityForecast>();
    private List<SearchCity> searchCityList = new ArrayList<SearchCity>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        initState();
        //db = x.getDb(daoConfig);
        initSearchCity();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_searchCity_main);

        //瀑布
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        SearchCityActivityAdapter adapter = new SearchCityActivityAdapter(searchCityList, new SearchCityActivityAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String city) {
//                Toast.makeText(SearchCityActivity.this, city, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setAction("zhangpeng");
                intent.putExtra("city",city);

                sendBroadcast(intent);

                finish();
            }
        });
        recyclerView.setAdapter(adapter);

    }


    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initSearchCity(){

        SearchCity searchCity01 = new SearchCity("大连市");
        searchCityList.add(searchCity01);
        SearchCity searchCity02 = new SearchCity("盘锦市");
        searchCityList.add(searchCity02);
        SearchCity searchCity03 = new SearchCity("鞍山市");
        searchCityList.add(searchCity03);
        SearchCity searchCity04 = new SearchCity("辽阳市");
        searchCityList.add(searchCity04);
        SearchCity searchCity05 = new SearchCity("抚顺市");
        searchCityList.add(searchCity05);
        SearchCity searchCity06 = new SearchCity("本溪市");
        searchCityList.add(searchCity06);
        SearchCity searchCity07 = new SearchCity("丹东市");
        searchCityList.add(searchCity07);
        SearchCity searchCity08 = new SearchCity("锦州市");
        searchCityList.add(searchCity08);
        SearchCity searchCity09 = new SearchCity("铁岭市");
        searchCityList.add(searchCity09);
        SearchCity searchCity10 = new SearchCity("营口市");
        searchCityList.add(searchCity10);
        SearchCity searchCity11 = new SearchCity("阜新市");
        searchCityList.add(searchCity11);
        SearchCity searchCity12 = new SearchCity("丹东市");
        searchCityList.add(searchCity12);
        SearchCity searchCity13 = new SearchCity("葫芦岛市");
        searchCityList.add(searchCity13);
        SearchCity searchCity14 = new SearchCity("朝阳市");
        searchCityList.add(searchCity14);

    }
}
