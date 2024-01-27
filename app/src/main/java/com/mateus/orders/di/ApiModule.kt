package com.mateus.orders.di

import com.mateus.orders.data.remote.FakeRemoteApi
import com.mateus.orders.data.remote.IFakeRemoteService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ApiModule {

    @Binds
    fun bindApi(
        fakeRemoteApi: FakeRemoteApi
    ): IFakeRemoteService

}