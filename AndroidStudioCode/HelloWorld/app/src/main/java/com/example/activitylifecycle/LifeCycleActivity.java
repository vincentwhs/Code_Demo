package com.example.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.intent.R;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        if (savedInstanceState != null){
            String temp = savedInstanceState.getString("test");
            Log.d("test", temp);
        }

        Log.d("test","onCreate");

        Button btn_normal = findViewById(R.id.btn_start_normal);
        Button btn_dialog = findViewById(R.id.btn_start_dialog);

        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycleActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycleActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String temp = "something you just typed";
        outState.putString("test", temp);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("test","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("test","onRestart");
    }
}
