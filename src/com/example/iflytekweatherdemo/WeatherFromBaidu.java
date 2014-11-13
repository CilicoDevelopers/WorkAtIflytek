package com.example.iflytekweatherdemo;

import com.example.iflytekweather.util.HttpUtil;
import com.example.iflytekweather.util.ProgressDialogUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class WeatherFromBaidu extends Activity implements Handler.Callback{
    private static final String TAG = "WeatherFromBaidu";
    
    private TextView resultText;
    //城市
    private String location;
    //返回天气结果
    private String result;
    
    private Handler handler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        initView();
        getWeather();
    }
    /**
     * 获取天气
     */
    private void getWeather() {
        location=getIntent().getStringExtra("city");
        handler=new Handler(this);
        ProgressDialogUtils.showProgressDialog(WeatherFromBaidu.this, "正在获取天气数据...");
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                HttpUtil util=new HttpUtil();
                result=util.executeGet(location);
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
