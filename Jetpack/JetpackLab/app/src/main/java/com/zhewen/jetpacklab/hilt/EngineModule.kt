package com.zhewen.jetpacklab.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class EngineModule {
    @Binds
    abstract fun bindEngine(gasEngine: GasEngine): Engine
}