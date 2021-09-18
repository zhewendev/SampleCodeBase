package com.zhewen.aidlserverstudy;
import com.zhewen.aidlServerstudy.User;
import com.zhewen.aidlServerstudy.IClientCallback;

interface ICommandServer {
    void registerClientCallback(IClientCallback client,String pkgName);
    void unregisterClientCallback(IClientCallback client,String pkgName);
    int add(int value1,int value2);
    List<User> addUser(in User user);
}