package com.example.ctrl.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.intent.R;

import java.util.List;

/**
 * Created by RD_huaishuai_wang on 2018/2/2.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Msg> mMsgs;
    public ChatAdapter(List<Msg> mlist){
        this.mMsgs = mlist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = mMsgs.get(position);
        if (msg.getType() == Msg.TYPE_RECIEVED){
            holder.layout_left.setVisibility(View.VISIBLE);
            holder.layout_right.setVisibility(View.GONE);
            holder.textView_left.setText(msg.getContent());
        }else {
            holder.layout_left.setVisibility(View.GONE);
            holder.layout_right.setVisibility(View.VISIBLE);
            holder.textView_right.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgs.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout layout_left;
        public LinearLayout layout_right;
        public TextView textView_left;
        public  TextView textView_right;
        public ViewHolder(View itemView) {
            super(itemView);
            layout_left = itemView.findViewById(R.id.layout_left);
            layout_right = itemView.findViewById(R.id.layout_right);
            textView_left = itemView.findViewById(R.id.tv_left);
            textView_right = itemView.findViewById(R.id.tv_right);
        }
    }

}
