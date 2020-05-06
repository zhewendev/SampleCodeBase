package com.baiheng.dagger2study;

import javax.inject.Singleton;

import dagger.Component;

@Component
@Singleton
public interface AppComponent {
    void inject(App app);
}
