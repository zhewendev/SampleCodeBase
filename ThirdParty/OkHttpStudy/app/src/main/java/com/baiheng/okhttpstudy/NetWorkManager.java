package com.baiheng.okhttpstudy;

import okhttp3.OkHttpClient;

/**
 * 网络管理类
 */
public class NetWorkManager {
    private static final String TAG = NetWorkManager.class.getSimpleName();
    private static NetWorkManager mNetworkManager;
    private OkHttpClient mOkHttpClient;

    public static synchronized NetWorkManager getInstance() {
        if (mNetworkManager == null) {
            mNetworkManager = new NetWorkManager();
        }
        return mNetworkManager;
    }

    public NetWorkManager() {
        mOkHttpClient = new OkHttpClient();
    }

}
