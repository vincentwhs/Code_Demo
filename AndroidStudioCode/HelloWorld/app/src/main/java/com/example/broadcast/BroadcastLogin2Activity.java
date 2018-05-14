package com.example.broadcast;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intent.R;

public class BroadcastLogin2Activity extends BaseActivity {
    private EditText account;
    private EditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_login2);

        account = findViewById(R.id.et_account);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = account.getText().toString();
                String pwd = password.getText().toString();
                if (acc.equals("admin") && pwd.equals("123456")){
                    Intent intent = new Intent(BroadcastLogin2Activity.this, BroadcastOfflinePracticeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(BroadcastLogin2Activity.this , "login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void actionStart(Context content){
        Intent intent = new Intent(content, BroadcastLogin2Activity.class);
        content.startActivity(intent);
    }
}
