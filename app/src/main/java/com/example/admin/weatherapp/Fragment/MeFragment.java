package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.weatherapp.QQLoginActivity;
import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.UI.MainActivity;
import com.example.admin.weatherapp.UI.WeatherWikiActivity;
import com.example.admin.weatherapp.util.SpUtil;
import com.example.admin.weatherapp.weather.WeatherService;
import com.mob.commons.SHARESDK;

import org.w3c.dom.Text;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by admin on 2017/8/11.
 */

public class MeFragment extends Fragment  {


    private TextView tvQQLogout;
    private TextView tvShareWeather;
    private TextView tvWeatherWiki;
    private static String APP_KEY = "205d30da23dce";
    private Switch mSwitch;
    private TextView tvQQLogin;
    private ImageView ivUserPhoto;
    private BroadcastReceiver qqBroadcastReceiver;
    private LinearLayout llQQLogout;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_me,container,false);

        tvWeatherWiki = (TextView)view.findViewById(R.id.tv_weather_wiki);
        tvShareWeather = (TextView)view.findViewById(R.id.tv_share_weather);

        tvWeatherWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WeatherWikiActivity.class);
                startActivity(intent);
            }
        });



        llQQLogout = (LinearLayout) view.findViewById(R.id.ll_qq_logout);


        llQQLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvQQLogin.setText("点击登录");
                //ivUserPhoto.setBackground(getActivity().getResources().getDrawable(R.drawable.icon_qq));
                ivUserPhoto.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_qq));

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("config",getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("qqName");
                editor.remove("qqPhotoUrl");
                editor.commit();
                llQQLogout.setVisibility(View.GONE);
            }
        });



        ivUserPhoto = (ImageView)view.findViewById(R.id.iv_userphoto);
        tvShareWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareSDK(view);
                //ShareSDK.initSDK(getActivity(),APP_KEY);
            }
        });

        mSwitch = (Switch) view.findViewById(R.id.switch1);

        tvQQLogin = (TextView)view.findViewById(R.id.tv_qq_login);
        tvQQLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //qqLogin();
                Intent intent = new Intent(getActivity(),QQLoginActivity.class);
                 startActivity(intent);
            }
        });

        return view;
    }


    /**
     * 设置监听
     * */
    private void setListener(){
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent intent = new Intent();
                intent.setAction("ye_jian_guang_bo_by_hp");
                intent.putExtra("is_checked", b);
                getActivity().sendBroadcast(intent);
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListener();

        if (SpUtil.getinstance((MainActivity)getActivity()).getReaderModeCode() == 1){
            mSwitch.setChecked(true);

        }



        IntentFilter intentFilter_qqlogin = new IntentFilter("qq_zhangpeng");
        qqBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String qqname = intent.getStringExtra("qqName");
                String qqphotourl = intent.getStringExtra("qqPhotoUrl");
                tvQQLogin.setText(qqname);
                llQQLogout.setVisibility(View.VISIBLE);
                x.image().bind(ivUserPhoto,qqphotourl, imageOptions);
                //Toast.makeText(getActivity(),qqname+qqphotourl,Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("config",((MainActivity) getActivity()).MODE_PRIVATE).edit();
                editor.putString("qqName",qqname);
                editor.putString("qqPhotoUrl",qqphotourl);
                editor.commit();
            }
        };
        getActivity().registerReceiver(qqBroadcastReceiver,intentFilter_qqlogin);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("config",((MainActivity) getActivity()).MODE_PRIVATE);
       String qqName =  sharedPreferences.getString("qqName",null);
       String qqPhotoUrl =  sharedPreferences.getString("qqPhotoUrl",null);

        if (qqName == null&&qqPhotoUrl == null){
            llQQLogout.setVisibility(View.GONE);
        }else{
            tvQQLogin.setText(qqName);
            x.image().bind(ivUserPhoto,qqPhotoUrl, imageOptions);
        }


    }

    @Override
    public void onDestroy() {

        getActivity().unregisterReceiver(qqBroadcastReceiver);
        super.onDestroy();
    }

    MainActivity mainActivity = (MainActivity)getActivity();

    public void shareSDK(View view){
        shareToQQByShareSDK();
    }

    private void shareToQQByShareSDK(){
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://blog.csdn.net/u013451048");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("功能测试，请自动忽略");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl("http://avatar.csdn.net/C/3/D/1_u013451048.jpg");
        // url仅在微信（包括好友和朋友圈）中使用
        //oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("功能测试：qq空间评论");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("功能测试：app name");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        // 启动分享GUI
        oks.show(this.getActivity());
    }


}
