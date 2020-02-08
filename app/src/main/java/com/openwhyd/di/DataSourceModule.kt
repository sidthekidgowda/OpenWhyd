package com.openwhyd.di

import com.openwhyd.datasource.HotTracksDataSource
import com.openwhyd.datasource.HotTracksDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun providesHotTracksDataSource(hotTracksDataSource: HotTracksDataSourceImpl): HotTracksDataSource
}