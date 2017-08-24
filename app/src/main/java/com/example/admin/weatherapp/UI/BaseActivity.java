package com.example.admin.weatherapp.UI;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.util.SpUtil;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by admin on 2017/8/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private WindowManager mWindowManager = null;
    private View mNightView = null;
    private WindowManager.LayoutParams mNightViewParam;
    private boolean mIsAddedView;
    /**
     * 夜间模式覆盖view 是否可用
     * true:可用 false:不可用
     */
    private boolean nightModeEnable=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int readMode= SpUtil.getinstance(BaseActivity.this).getReaderModeCode();
        //是否是夜间模式
        if( readMode==1 && nightModeEnable){
            changeToNight();
        }

        x.view().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
        setListener();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initView();
        initData();
        setListener();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initView();
        initData();
        setListener();
    }

    @Override
    protected void onDestroy() {
        changeToDay();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

   protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();

    //设置夜间模式
    public void changeToNight() {
        if (mIsAddedView == true)
            return;
        mNightViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        mWindowManager = getWindowManager();
        mNightView = new View(this);
        mNightView.setBackgroundResource(R.color.colorNight);
        mWindowManager.addView(mNightView, mNightViewParam);
        mIsAddedView = true;
    }

    //设置白天模式
    public void changeToDay(){

        if (mIsAddedView && mNightView!=null) {
            mWindowManager.removeViewImmediate(mNightView);
            mWindowManager = null;
            mNightView = null;
            mIsAddedView=false;
        }
    }
    /**
     * 设置夜间模式 添加view是否可用
     * 必须在super.onCreate(savedInstanceState);之前调用
     */
    public void setChangeModeUnEnable(){
        nightModeEnable=false;
    }


    protected DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("weather.db")
            // 不设置dbDir时, 默认存储在app的私有目录.
            .setDbVersion(2)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    // 开启WAL, 对写入加速提升巨大
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    // TODO: ...
                    // db.addColumn(...);
                    // db.dropTable(...);
                    // ...
                    // or
                    // db.dropDb();
                }
            });
}
