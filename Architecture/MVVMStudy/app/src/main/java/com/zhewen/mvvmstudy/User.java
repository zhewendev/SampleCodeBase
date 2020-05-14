package com.zhewen.mvvmstudy;
/**
* @author: zhewen
* created: 2020/5/14
* desc:
*/
public class User {
    //名字
    private String name;
    //是否男的
    private boolean isMale;
    //年龄
    private int age;

    public String getName() {
        return name;
    }

    public User(String name, boolean isMale, int age) {
        this.name = name;
        this.isMale = isMale;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

