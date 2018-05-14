package com.example.standardusecase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;

import com.example.intent.R;

/**
 * 启动活动的最佳方法
 */
public class StandardStartActivity extends BaseAcitvity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_start);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null)
            actionBar.hide();

    }

    /**
     * 启动活动的最佳方法
     */
    public static void actionStart(Context content, String para1, String para2){
        Intent intent = new Intent(content, StandardStartActivity.class);
        intent.putExtra("para1", para1);
        intent.putExtra("para2", para2);
        content.startActivity(intent);
    }
}
