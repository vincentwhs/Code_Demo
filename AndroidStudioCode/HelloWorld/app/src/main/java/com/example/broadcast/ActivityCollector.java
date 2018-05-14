package com.example.broadcast;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RD_huaishuai_wang on 2018/3/8.
 */

public class ActivityCollector {

    private static List<Activity> collector = new ArrayList<>();

    public static void addActivity(Activity activity){
        collector.add(activity);
    }

    public static void removeActivity(Activity activity){
        collector.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity : collector){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
