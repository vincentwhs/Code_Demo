package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.intent.R;

public class MyBroastcastActivity extends AppCompatActivity {

    private LocalBroadcastReciever localBroadcastReciever;
    private LocalBroadcastManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broastcast);

        Button btn_cast = findViewById(R.id.btn_my_broastcast);
        btn_cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcast.MY_BROADCAST");
                //发送标准广播
                //sendBroadcast(intent);
                //发送有序广播
                sendOrderedBroadcast(intent, null);
            }
        });

        localBroadcastReciever = new LocalBroadcastReciever();

        manager = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter("com.example.broadcast.LOCAL_BROADCAST");
        manager.registerReceiver(localBroadcastReciever,filter);

        Button btn_lacal_cast = findViewById(R.id.btn_my_broastcast_local);
        btn_lacal_cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.broadcast.LOCAL_BROADCAST");
                manager.sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(localBroadcastReciever);
    }

    public static void actionStart(Context content){
        Intent intent = new Intent(content, MyBroastcastActivity.class);
        content.startActivity(intent);
    }

    class LocalBroadcastReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
