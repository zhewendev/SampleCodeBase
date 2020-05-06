package com.baiheng.dagger2study;

import com.baiheng.dagger2study.retention.CarScope;

import dagger.Component;

@Component(modules = {MarkCarModule.class})
@CarScope
public interface CarComponent {
    void inject(Car car);
}
