package com.test.shoppingapp.network;

import com.test.networkmodule.NetworkSDK;
import com.test.networkmodule.api.ShoppingAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public NetworkSDK provideNetworkSDK() {
        return NetworkSDK.getInstance();
    }

    @Provides
    @Singleton
    public ShoppingAPI provideShoppingAPI(NetworkSDK networkSDK) {
        return networkSDK.getShoppingAPI();
    }
}