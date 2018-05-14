package com.example.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intent.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsContentFragment extends Fragment {


    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return view = inflater.inflate(R.layout.fragment_news_content, container, false);
    }

    public void refresh(String title, String content){
        View viewvisibly = view.findViewById(R.id.ll_news_content_1);
        viewvisibly.setVisibility(View.VISIBLE);
        TextView tv_title = view.findViewById(R.id.news_title);
        TextView tv_content = view.findViewById(R.id.tv_news_content_1);

        tv_title.setText(title);
        tv_content.setText(content);
    }

}
