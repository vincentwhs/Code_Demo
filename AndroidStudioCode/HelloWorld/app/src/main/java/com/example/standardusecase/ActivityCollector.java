package com.example.standardusecase;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RD_huaishuai_wang on 2018/2/1.
 * 用于管理Activity活动，方便关闭
 */

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeAcitvity(Activity activity){
        activities.remove(activity);
    }

    public  static void finishAll(){
        for (Activity activity : activities){
            activity.finish();
        }
    }
}
