package com.baiheng.dagger2study;

import javax.inject.Inject;

public class Engine {

    private String type;

    @Inject
    Engine(){}

    Engine(String type) {
        this.type = type;
    }

    public void run(){
        System.out.println("引擎转起来了~~~");
        System.out.println("type = " + type);
    }
}
