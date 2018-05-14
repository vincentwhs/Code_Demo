package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intent.R;

public class SimpleFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_fragment_simple_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);

        btn_fragment_simple_1 = findViewById(R.id.btn_fragment_simple_1);
        btn_fragment_simple_1.setOnClickListener((View.OnClickListener) this);
       // StartFragment(new SimpleRightFragment());
    }

    public static void actionStart(Context content){
        Intent intent = new Intent(content, SimpleFragmentActivity.class);
        content.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_fragment_simple_1){
            StartFragment(new SimpleAnotherRightFragment());
        }
    }

    private void StartFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout_fragment_1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
