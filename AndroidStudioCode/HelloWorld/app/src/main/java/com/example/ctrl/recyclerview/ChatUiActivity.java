package com.example.ctrl.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.intent.R;

import java.util.ArrayList;
import java.util.List;

public class ChatUiActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<Msg>();

    private EditText editText;
    private Button btn_send;
    private RecyclerView recyclerView;
    private  ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ui);

        initMsgs();

        recyclerView = findViewById(R.id.recyclerview_chat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatAdapter(msgList);
        recyclerView.setAdapter(chatAdapter);

        editText = findViewById(R.id.et_chat_1);
        btn_send  =findViewById(R.id.btn_chat_1);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!"".equals(text)) {
                    Msg msg = new Msg(text, Msg.TYPE_SEND);
                    msgList.add(msg);
                    chatAdapter.notifyItemInserted(msgList.size() - 1);
                    recyclerView.scrollToPosition(msgList.size()-1);
                    editText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECIEVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECIEVED);
        msgList.add(msg3);
    }

    public static void actionStart(Context content){
        Intent intent = new Intent(content, ChatUiActivity.class);
        content.startActivity(intent);
    }
}
