package com.yezi.zuo.careweather.Tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zuo on 2016/11/29.
 */

public class AutoOpenReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"我启动了",Toast.LENGTH_LONG).show();
    }
}