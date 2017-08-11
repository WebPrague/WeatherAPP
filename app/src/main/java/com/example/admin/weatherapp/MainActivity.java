package com.example.admin.weatherapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.admin.weatherapp.Fragment.MeActivity;
import com.example.admin.weatherapp.Fragment.ViewActivity;
import com.example.admin.weatherapp.Fragment.WeatherActivity;

public class MainActivity extends BaseActivity {
    private WeatherActivity fragmentWeather;
    private ViewActivity fragmentView;
    private MeActivity fragmentMe;

    private Button Button_weather;
    private Button Button_view;
    private Button Button_me;

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;


    public void initial(){
        fragmentWeather = new WeatherActivity();
        fragmentView = new ViewActivity();
        fragmentMe = new MeActivity();

        Button_weather = (Button)findViewById(R.id.btn_weather);
        Button_view = (Button)findViewById(R.id.btn_view);
        Button_me = (Button)findViewById(R.id.btn_me);

        imageView1 = (ImageView)findViewById(R.id.imageview_weather);
        imageView2 = (ImageView)findViewById(R.id.imageview_view);
        imageView3 = (ImageView)findViewById(R.id.imageview_me);

        imageView1.setOnClickListener(new View.OnClickListener() {
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
                transaction.show(fragmentWeather);
                transaction.hide(fragmentView);
                transaction.hide(fragmentMe);
                transaction.commit();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
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
                transaction.hide(fragmentWeather);
                transaction.show(fragmentView);
                transaction.hide(fragmentMe);
                transaction.commit();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
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
                transaction.hide(fragmentWeather);
                transaction.hide(fragmentView);
                transaction.show(fragmentMe);
                transaction.commit();
            }
        });
        setDefaultFragment();

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

        transaction.replace(R.id.fragment_weather, fragmentWeather);
        transaction.replace(R.id.fragment_view, fragmentView);
        transaction.replace(R.id.fragment_me, fragmentMe);

        transaction.hide(fragmentView);
        transaction.hide(fragmentMe);
        transaction.commit();
    }


    /*
        * 所有图片变暗
        * */
//    private void resetImg(){
//        imageButton_weather.setImageResource(R.drawable.weather);
//        imageButton_view.setImageResource(R.drawable.view);
//        imageButton_me.setImageResource(R.drawable.me);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
    }
}
