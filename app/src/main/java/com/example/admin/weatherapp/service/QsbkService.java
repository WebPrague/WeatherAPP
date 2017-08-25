package com.example.admin.weatherapp.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZhangPeng on 2017/8/25.
 */

public class QsbkService {
    Context context;
    public QsbkService(Context context){
        this.context = context;
    }



    public class Qsbk{
        public String nickName;
        public String userPhoto;
        public String content;
        public int voteUp;
        public int voteDown;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getVoteUp() {
            return voteUp;
        }

        public void setVoteUp(int voteUp) {
            this.voteUp = voteUp;
        }

        public int getVoteDown() {
            return voteDown;
        }

        public void setVoteDown(int voteDown) {
            this.voteDown = voteDown;
        }
    }

    public interface GetQsbkListener{
        public void getQsbk(List<Qsbk>list, Exception e);
    }

    private int getPage(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        int page = sharedPreferences.getInt("qsbk_page", 1);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("qsbk_page", page + 1);
        editor.commit();
        return page * 10;
    }

    private OkHttpClient client = new OkHttpClient.Builder().build();

    public void getQsbk(final GetQsbkListener listener){
        final String url = "http://m2.qiushibaike.com/article/list/suggest?page=" + getPage() + "&type=refresh&count=30&format=word";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.getQsbk(null,e);
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String body = response.body().string();
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                List<Qsbk>qsbks = new ArrayList<Qsbk>();

                                JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
                                JsonArray items = jsonObject.get("items").getAsJsonArray();
                                for (int i = 0 ; i < items.size() ; i ++){
                                    JsonObject item = items.get(i).getAsJsonObject();
                                    if (item.get("format").getAsString().equals("word")){
                                        try {
                                            Qsbk qsbk = new Qsbk();
                                            qsbk.nickName = item.get("user").getAsJsonObject().get("login").getAsString();
                                            qsbk.userPhoto = "http:" + item.get("user").getAsJsonObject().get("thumb").getAsString();
                                            qsbk.content = item.get("content").getAsString();
                                            qsbk.voteUp = item.get("votes").getAsJsonObject().get("up").getAsInt();
                                            qsbk.voteDown = item.get("votes").getAsJsonObject().get("down").getAsInt();
                                            qsbks.add(qsbk);
                                        }catch (Exception e){

                                        }

                                    }
                                }
                                listener.getQsbk(qsbks,null);
                            }
                        });
                    }
                });
            }
        }).start();

    }
}
