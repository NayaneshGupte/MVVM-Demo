package com.test.shoppingapp.network

import com.test.networkmodule.NetworkSDK
import com.test.networkmodule.api.ShoppingAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkSDKModule {

    @Provides
    @Singleton
    fun provideNetworkSDK(): NetworkSDK {
        return NetworkSDK.instance
    }

    @Provides
    @Singleton
    fun provideShoppingAPI(networkSDK: NetworkSDK): ShoppingAPI {
        return networkSDK.shoppingAPI
    }
}