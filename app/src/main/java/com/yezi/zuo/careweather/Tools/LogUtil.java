package com.yezi.zuo.careweather.Tools;

import android.util.Log;

/**
 * Created by zuo on 2016/11/27.
 */

public class LogUtil {

    public static final int LEVEL=1;


    public static void v(String tag,String msg){
        if(LEVEL<=1){
            Log.v(tag,msg);
        }

    }

    public static void d(String tag,String msg){
        if(LEVEL<=2){
            Log.d(tag,msg);
        }

    }
    public static void i(String tag,String msg){
        if(LEVEL<=3){
            Log.i(tag,msg);
        }

    }
    public static void w(String tag,String msg){
        if(LEVEL<=4){
            Log.w(tag,msg);
        }

    }
    public static void e(String tag,String msg){
        if(LEVEL<=5){
            Log.e(tag,msg);
        }

    }



}
