package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.intent.R;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        String title = getIntent().getStringExtra("title");
        String ncontent =getIntent().getStringExtra("content");


        NewsContentFragment newsContentFragment =
                (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.fm_news_content);
        newsContentFragment.refresh(title,ncontent);
    }

    public static void actionStart(Context content, String title, String ncontent){

        Intent intent = new Intent(content, NewsContentActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", ncontent);

        content.startActivity(intent);
    }
}
