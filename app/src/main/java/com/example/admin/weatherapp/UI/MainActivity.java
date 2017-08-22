package com.example.admin.weatherapp.UI;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.weatherapp.Fragment.MeFragment;
import com.example.admin.weatherapp.Fragment.ViewFragment;
import com.example.admin.weatherapp.Fragment.WeatherFragment;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.db.WeatherCity;
import com.example.admin.weatherapp.weather.Weather;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

public class MainActivity extends BaseActivity {

    private LinearLayout linearLayout_weather;
    private LinearLayout linearLayout_view;
    private LinearLayout linearLayout_me;

    private WeatherFragment weatherFragment;
    private ViewFragment viewFragment;
    private MeFragment meFragment;

    private Button Button_weather;
    private Button Button_view;
    private Button Button_me;

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;



    public void initial(){
        weatherFragment = new WeatherFragment();
        viewFragment = new ViewFragment();
        meFragment = new MeFragment();

        linearLayout_weather = (LinearLayout) findViewById(R.id.ll_weather);
        linearLayout_view = (LinearLayout) findViewById(R.id.ll_view);
        linearLayout_me = (LinearLayout) findViewById(R.id.ll_me);

        Button_weather = (Button)findViewById(R.id.btn_weather);
        Button_view = (Button)findViewById(R.id.btn_view);
        Button_me = (Button)findViewById(R.id.btn_me);

        imageView1 = (ImageView)findViewById(R.id.imageview_weather);
        imageView2 = (ImageView)findViewById(R.id.imageview_view);
        imageView3 = (ImageView)findViewById(R.id.imageview_me);



        linearLayout_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();

                imageView1.setImageResource(R.drawable.weather_press);
                imageView2.setImageResource(R.drawable.view);
                imageView3.setImageResource(R.drawable.me);
                Button_weather.setTextColor(Color.parseColor("#3f9ada"));
                Button_view.setTextColor(Color.parseColor("#ffffff"));
                Button_me.setTextColor(Color.parseColor("#ffffff"));

                //resetImg();
                //Button_weather.setImageResource(R.drawable.weather);
                transaction.show(weatherFragment);
                transaction.hide(viewFragment);
                transaction.hide(meFragment);
                transaction.commit();
            }
        });

        linearLayout_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();

                imageView1.setImageResource(R.drawable.weather);
                imageView2.setImageResource(R.drawable.view_press);
                imageView3.setImageResource(R.drawable.me);
                Button_weather.setTextColor(Color.parseColor("#ffffff"));
                Button_view.setTextColor(Color.parseColor("#3f9ada"));
                Button_me.setTextColor(Color.parseColor("#ffffff"));
                //resetImg();
                //imageButton_view.setImageResource(R.drawable.view);
                transaction.hide(weatherFragment);
                transaction.show(viewFragment);
                transaction.hide(meFragment);
                transaction.commit();
            }
        });

        linearLayout_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                imageView1.setImageResource(R.drawable.weather);
                imageView2.setImageResource(R.drawable.view);
                imageView3.setImageResource(R.drawable.me_press);
                Button_weather.setTextColor(Color.parseColor("#ffffff"));
                Button_view.setTextColor(Color.parseColor("#ffffff"));
                Button_me.setTextColor(Color.parseColor("#3f9ada"));
               // resetImg();
                //imageButton_me.setImageResource(R.drawable.me);
                transaction.hide(weatherFragment);
                transaction.hide(viewFragment);
                transaction.show(meFragment);
                transaction.commit();
            }
        });
        setDefaultFragment();

        //initialDB();
    }



    private void initialDB(){
        DbManager db = x.getDb(daoConfig);
        try{
            db.findAll(WeatherCity.class);
        }catch (Exception e){
            WeatherCity weatherCity = new WeatherCity();
            weatherCity.setTmp("666");

            try {
                db.save(weatherCity);
            } catch (DbException e1) {
                e1.printStackTrace();
            }
            try {
                WeatherCity weatherCity1 = db.findFirst(WeatherCity.class);
                db.delete(weatherCity1);
            }catch (Exception e1){

            }
        }
        WeatherCity weatherCity = new WeatherCity();
        weatherCity.setTmp("666");
        try {
            db.save(weatherCity);
        } catch (DbException e) {
            e.printStackTrace();
        }

        try {
            List<WeatherCity>weatherCities = db.findAll(WeatherCity.class);
            for (WeatherCity weatherCity1 : weatherCities){
                Toast.makeText(MainActivity.this, weatherCity1.getTmp(),Toast.LENGTH_LONG).show();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置默认Fragment，以录音界面为默认界面
     */
    private void setDefaultFragment()
    {
        imageView1.setImageResource(R.drawable.weather_press);
        imageView2.setImageResource(R.drawable.view);
        imageView3.setImageResource(R.drawable.me);
        Button_weather.setTextColor(Color.parseColor("#3f9ada"));
        Button_view.setTextColor(Color.parseColor("#ffffff"));
        Button_me.setTextColor(Color.parseColor("#ffffff"));

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_weather, weatherFragment);
        transaction.replace(R.id.fragment_view, viewFragment);
        transaction.replace(R.id.fragment_me, meFragment);

        transaction.hide(viewFragment);
        transaction.hide(meFragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initState();
        initial();
        //Toast.makeText(MainActivity.this,"hello world", Toast.LENGTH_LONG).show();


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
