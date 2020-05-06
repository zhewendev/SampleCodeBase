package com.baiheng.dagger2study.subcomponent;

import com.baiheng.dagger2study.MarkCarModule;

import dagger.Component;

@Component(
        modules = {
                MarkCarModule.class
        }
)
public interface MainComponent {
}
