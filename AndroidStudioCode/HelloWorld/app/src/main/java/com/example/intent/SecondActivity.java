package com.example.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        test();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        //Intent intent = getIntent();
        Button btn = findViewById(R.id.btn_sencond_intent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {
        Intent intent = new Intent();
        intent.putExtra("test", "justtest");
        setResult(RESULT_OK, intent);
        finish();
    }
}
