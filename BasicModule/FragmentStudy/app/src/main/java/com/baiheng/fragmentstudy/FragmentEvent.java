package com.baiheng.fragmentstudy;

/**
 * 定义EventBus通信对象
 */
public class FragmentEvent {
    private static final String TAG = FragmentEvent.class.getSimpleName();

    private String mUserName;
    private int mUserAge;
    public FragmentEvent(){

    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public int getUserAge() {
        return mUserAge;
    }

    public void setUserAge(int userAge) {
        this.mUserAge = userAge;
    }
}
