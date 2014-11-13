package com.example.iflytekweatherdemo;

import com.example.iflytekweather.util.HttpUtil;
import com.example.iflytekweather.util.ProgressDialogUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class WeatherFromWeb extends Activity implements Handler.Callback {
    private static final String TAG = "WeatherFromWeb";
    
    private TextView resultText;
    
    private Handler handler;
    private String location,result;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        initView();
        getWeather();
    }

    private void getWeather() {
        location=getIntent().getStringExtra("city");
        handler=new Handler(this);
        ProgressDialogUtils.showProgressDialog(WeatherFromWeb.this, "正在获取天气数据...");
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                HttpUtil util=new HttpUtil();
                result=util.executeGetQXJ("101010100");
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void initView() {
        resultText=(TextView) findViewById(R.id.weather_result);
    }

    @Override
    public boolean handleMessage(Message msg) {
        ProgressDialogUtils.dismissProgressDialog();
        resultText.setText(result);
        return true;
    }
}
