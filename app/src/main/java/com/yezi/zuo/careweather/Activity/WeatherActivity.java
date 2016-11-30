package com.yezi.zuo.careweather.Activity;

import android.os.Bundle;
import android.view.Window;

import com.yezi.zuo.careweather.R;

/**
 * Created by zuo on 2016/11/30.
 */

public class WeatherActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weatheractivity);
        init();
    }

    private void init() {
    }
}
