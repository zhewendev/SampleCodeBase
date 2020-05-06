package com.baiheng.eventbusstudy;

public class ThirdEvent {

    private String mMsg;
    public ThirdEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = "MainEvent:"+msg;
    }
    public String getMsg() {
        return mMsg;
    }
}
