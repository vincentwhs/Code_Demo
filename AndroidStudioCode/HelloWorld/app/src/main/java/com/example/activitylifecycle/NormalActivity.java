package com.example.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;

import com.example.intent.R;

public class NormalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }
}
