package com.baiheng.dagger2study;

import com.baiheng.dagger2study.retention.CarScope;
import com.baiheng.dagger2study.retention.QualifierA;
import com.baiheng.dagger2study.retention.QualifierB;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
@Module
public class MarkCarModule {
    public MarkCarModule(){ }

    @Named("EngineA")
    @Provides
    @CarScope
    Engine provideEngineA(){
        return new Engine("gearA");
    }

    @Named("EngineB")
    @Provides
    Engine provideEngineB() {
        return new Engine("gearB");
    }
}
