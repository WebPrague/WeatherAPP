package com.example.admin.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class QQLoginActivity extends AppCompatActivity {

    private static final String APPID = "101424928";
    private Tencent mTencent;
    private IUiListener loginListener; //授权登录监听器
    private IUiListener userInfoListener; //获取用户信息监听器
    private String scope; //获取信息的范围参数
    private UserInfo userInfo; //qq用户信息
    private String qq_name = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);
        initData();
        qqLogin();

        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("正在获取QQ登录数据.....");
        pDialog.setCancelable(true);
        pDialog.show();
    }


    private void initData(){
        mTencent = Tencent.createInstance(APPID,QQLoginActivity.this);

        //要所有权限，不然会再次申请增量权限，这里不要设置成get_user_info,add_t
        scope = "all";
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {

                System.out.println("有数据返回.....");
                if(o == null){
                    return;
                }
                JSONObject jo = (JSONObject) o;
                try {
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));

                    if (ret == 0) {
                        //Toast.makeText(QQLoginActivity.this, "登录成功",Toast.LENGTH_LONG).show();

                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);

                        userInfo = new UserInfo(QQLoginActivity.this, mTencent.getQQToken());
                        userInfo.getUserInfo(userInfoListener);

                    }
                    } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onError(UiError uiError) {
                Toast.makeText(QQLoginActivity.this,"登录失败...",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancel() {
                finish();
            }
        };
        userInfoListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                if(o == null){
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) o;
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");
                    String userphoto = jo.getString("figureurl_qq_2");


                    //Toast.makeText(QQLoginActivity.this, "你好，" + nickName,Toast.LENGTH_LONG).show();
                    //qq_name = nickName;

                    //发送广播
                    Intent intent = new Intent();
                    intent.setAction("qq_zhangpeng");
                    intent.putExtra("qqName",nickName);
                    intent.putExtra("qqPhotoUrl",userphoto);
                    sendBroadcast(intent);
                    finish();

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            @Override
            public void onError(UiError uiError) {
                Toast.makeText(QQLoginActivity.this,"登录失败...",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancel() {
                finish();
            }
        };


    }

    private void qqLogin(){
        if (!mTencent.isSessionValid()){
            mTencent.login(QQLoginActivity.this,scope,loginListener);
        }
    }

    @Override
    protected void onDestroy() {
        if (mTencent != null) {
            //注销登录
            mTencent.logout(QQLoginActivity.this);
        }
        super.onDestroy();
    }


    //确保能接收到回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);


    }

}
