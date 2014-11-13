package com.example.iflytekweatherdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private EditText cityEdit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    private void initView() {
        cityEdit=(EditText) findViewById(R.id.main_editText);
    }
    
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.main_baiduBtn:
                Intent intent=new Intent(MainActivity.this, WeatherFromBaidu.class);
                intent.putExtra("city", cityEdit.getText().toString());
                startActivity(intent);
                break;
            case R.id.main_qxjBtn:
                Intent intent2=new Intent(MainActivity.this, WeatherFromWeb.class);
                intent2.putExtra("city", cityEdit.getText().toString());
                startActivity(intent2);
                break;

            default:
                break;
        }
    }
    
}
