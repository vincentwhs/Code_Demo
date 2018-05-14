package com.example.ctrl.recyclerview;

/**
 * Created by RD_huaishuai_wang on 2018/2/2.
 */

public class Msg {
    public final static int TYPE_RECIEVED = 0;
    public static final int TYPE_SEND=1;

    private String content;
    private int type;

    public  Msg(String content, int type){
        this.content = content;
        this.type=type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
