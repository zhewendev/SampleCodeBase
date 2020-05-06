package com.baiheng.dagger2study.subcomponent;

import com.baiheng.dagger2study.Logger;
import com.baiheng.dagger2study.MarkCarModule;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                MarkCarModule.class,

        }
)
public interface LoggerComponent {
    void inject(Logger logger);
}
