package com.yezi.zuo.careweather.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.yezi.zuo.careweather.R;
import com.yezi.zuo.careweather.Tools.ActivityCollector;
import com.yezi.zuo.careweather.Tools.LogUtil;

/**
 * Created by zuo on 2016/11/28.
 * 功能，
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        LogUtil.d("BaseActivity",getClass().getSimpleName());//可知当前Activity

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.deleteActivity(this);//方便管理Activity
    }

}
