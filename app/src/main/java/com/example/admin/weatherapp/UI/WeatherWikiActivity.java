package com.example.admin.weatherapp.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.weatherapp.R;
import com.example.admin.weatherapp.UI.BaseActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2017/8/21.
 */

public class WeatherWikiActivity extends BaseActivity {

    private ImageView ivBackWeather;
    private Button btnQing;
    private Button btnFeng;
    private Button btnYu;
    private Button btnLeizheyu;
    private Button btnTaifeng;
    private Button btnBingbao;
    private Button btnYun;
    private Button btnShidu;
    private Button btnJiangyuliang;
    private Button btnZiwaixian;
    private Button btnNengjiandu;
    private Button btnPmer;
    private Button btnPmshi;
    private Button btnXue;
    private Button btnSheshidu;
    private Button btnHuashidu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_wiki);
        initState();
        btnQing = (Button)findViewById(R.id.btn_qing);
        btnBingbao = (Button)findViewById(R.id.btn_bingbao);
        btnFeng = (Button)findViewById(R.id.btn_feng);
        btnHuashidu = (Button)findViewById(R.id.btn_huashidu);
        btnJiangyuliang = (Button)findViewById(R.id.btn_jiangyuliang);
        btnNengjiandu = (Button)findViewById(R.id.btn_nengjiandu);
        btnPmer = (Button)findViewById(R.id.btn_pmer);
        btnPmshi = (Button)findViewById(R.id.btn_pmshi);
        btnXue = (Button)findViewById(R.id.btn_xue);
        btnSheshidu = (Button)findViewById(R.id.btn_sheshidu);
        btnShidu = (Button)findViewById(R.id.btn_shidu);
        btnYu = (Button)findViewById(R.id.btn_yu);
        btnYun = (Button)findViewById(R.id.btn_yun);
        btnLeizheyu = (Button)findViewById(R.id.btn_leizhenyu);
        btnTaifeng = (Button)findViewById(R.id.btn_taifeng);
        btnZiwaixian = (Button)findViewById(R.id.btn_ziwaixian);
        ivBackWeather = (ImageView)findViewById(R.id.iv_back_weather);

        ivBackWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnQing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String st = btnQing.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnFeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnFeng.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });
        btnYu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnYu.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnLeizheyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnLeizheyu.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnTaifeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnTaifeng.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnBingbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnBingbao.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnYun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnYun.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnShidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnShidu.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnJiangyuliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnJiangyuliang.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnZiwaixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnZiwaixian.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnNengjiandu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnNengjiandu.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });


        btnPmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnPmer.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });


        btnPmshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnPmshi.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnXue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnXue.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

        btnSheshidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnSheshidu.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });
        btnHuashidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = btnHuashidu.getText().toString();
                Uri uri = null;
                try {
                    uri = Uri.parse("http://baike.baidu.com/item/" + URLEncoder.encode(st,"UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(it);
                    }
                };
                timer.schedule(task, 0);
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

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
