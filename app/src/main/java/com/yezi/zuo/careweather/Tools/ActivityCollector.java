package com.yezi.zuo.careweather.Tools;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuo on 2016/11/28.
 * 管理Activity方便一次性退出应用程序
 *
 *
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activities.add(activity);

    }

    public static void deleteActivity(Activity activity){
        activities.remove(activity);
    }
    public static void quitApp(){
        for(Activity activity: activities){
            activity.finish();
        }

    }


}
