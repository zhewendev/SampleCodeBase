package com.zhewen.aidlserverstudy;
import com.zhewen.aidlServerstudy.User;
import com.zhewen.aidlServerstudy.IClientCallback;

interface ICommandServer {
    void registerClientCallback(IClientCallback client);
    void unregisterClientCallback(IClientCallback client);
    int add(int value1,int value2);
    List<User> addUser(in User user);
    void addUserIn(in User user);
    void addUserOut(out User user);
    void addUserInOut(inout User user);
    oneway void addByOneWay(int value1,int value2);
}