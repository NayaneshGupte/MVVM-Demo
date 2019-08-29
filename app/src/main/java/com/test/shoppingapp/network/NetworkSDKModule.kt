package com.test.shoppingapp.network

import com.test.networkmodule.DataProvider
import com.test.networkmodule.api.ShoppingAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkSDKModule {

    @Provides
    @Singleton
    fun provideNetworkSDK(): DataProvider {
        return DataProvider.instance
    }

    @Provides
    @Singleton
    fun provideShoppingAPI(dataProvider: DataProvider): ShoppingAPI {
        return dataProvider.shoppingAPI
    }
}