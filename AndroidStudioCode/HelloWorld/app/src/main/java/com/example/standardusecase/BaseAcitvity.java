package com.example.standardusecase;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by RD_huaishuai_wang on 2018/2/1.
 * 用于随时随地找到具体的Activity活动
 */

public class BaseAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.随时随地找到具体的Activity活动
        Log.d("test",  getClass().getSimpleName());

        //2.随时随地关掉程序Demo
        //add
        ActivityCollector.addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeAcitvity(this);
    }

    public void finishDemo(){
        ActivityCollector.finishAll();
        //可以调用关闭进程方法
        Process.killProcess(Process.myPid());
    }
}
