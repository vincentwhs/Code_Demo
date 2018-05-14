package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by RD_huaishuai_wang on 2018/3/8.
 */

public class BaseActivity extends AppCompatActivity {

    ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        receiver = new ForceOfflineReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.broadcast.FORCE_OFFLINE");
        registerReceiver(receiver, filter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(receiver);
        receiver=null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

}

class ForceOfflineReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("Warning");
        builder.setMessage("You are fored to be offline.Please login again;");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();

                Intent intent = new Intent(context, BroadcastLogin2Activity.class);
                context.startActivity(intent);
            }
        });
        builder.show();
    }
}
