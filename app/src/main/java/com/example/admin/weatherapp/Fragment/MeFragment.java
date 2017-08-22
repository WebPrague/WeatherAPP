package com.example.admin.weatherapp.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.UI.WeatherWikiActivity;
import com.mob.commons.SHARESDK;

import org.w3c.dom.Text;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by admin on 2017/8/11.
 */

public class MeFragment extends Fragment {

    private TextView tvShareWeather;
    private TextView tvWeatherWiki;
    private static String APP_KEY = "205d30da23dce";


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

        tvShareWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareSDK(view);
                //ShareSDK.initSDK(getActivity(),APP_KEY);
            }
        });



        return view;
    }

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
