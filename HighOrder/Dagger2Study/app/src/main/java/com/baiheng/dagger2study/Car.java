package com.baiheng.dagger2study;

import com.baiheng.dagger2study.retention.QualifierA;
import com.baiheng.dagger2study.retention.QualifierB;

import javax.inject.Inject;
import javax.inject.Named;

public class Car {
    @Named("EngineA")
    @Inject
    Engine engineA;

    @Named("EngineB")
    @Inject
    Engine engineB;

    public Car() {
        DaggerCarComponent.builder().markCarModule(new MarkCarModule())
                .build().inject(this);
    }

    public Engine getEngineA() {
        return this.engineA;
    }

    public Engine getEngineB() {
        return this.engineB;
    }
}
