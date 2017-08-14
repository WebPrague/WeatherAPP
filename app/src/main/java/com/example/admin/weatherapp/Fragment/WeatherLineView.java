package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.weatherapp.BaseActivity;
import com.example.admin.weatherapp.MainActivity;
import com.example.admin.weatherapp.R;

/**
 * Created by admin on 2017/8/13.
 */

public class WeatherLineView  extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherline);
    }
}
