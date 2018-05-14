package com.example.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intent.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsTitleFragment extends Fragment {

    class NewsAdapter extends RecyclerView.Adapter{

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View itemView) {
                super(itemView);
                newsTitleText = itemView.findViewById(R.id.tv_news_title_item_1);
            }
        }

        public NewsAdapter(List<News> list){
            this.mNewsList = list;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news_title, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(viewHolder.getAdapterPosition());
                    if (isTwoPane){
                        NewsContentFragment ncf = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.fm_news_content_1);
                        ncf.refresh(news.getTitle(),news.getContent());
                    }else {
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(),news.getContent());

                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            ((ViewHolder)holder).newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_news_title_1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter adapter = new NewsAdapter(getList());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<News> getList(){
        List<News> list = new ArrayList<>();
        for(int i =0; i<=50; i++){
            News news = new News();
            news.setTitle("title" + i);
            news.setContent(getRandomLengthContent("this is content" + i + "."));
            list.add(news);
        }

        return list;
    }
    private String getRandomLengthContent(String str){
        Random random = new Random();
        int r = random.nextInt(20);
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<r; i++){
            builder.append(str);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getActivity().findViewById(R.id.fl_news_content_layout);
        if (view == null){
            isTwoPane = false;
        }else {
            isTwoPane = true;
        }

    }
}
