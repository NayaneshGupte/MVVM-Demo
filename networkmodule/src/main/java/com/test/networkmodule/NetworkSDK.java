package com.test.networkmodule;


import com.test.networkmodule.api.ShoppingAPI;
import com.test.networkmodule.api.ShoppingAPIImpl;
import com.test.networkmodule.di.DaggerNetworkComponent;
import com.test.networkmodule.di.NetworkComponent;
import com.test.networkmodule.exceptions.SDKNotInitializedException;

import javax.inject.Inject;

import dagger.Lazy;

public class NetworkSDK {

    private static final String TAG = "NetworkSDK";
    private static NetworkSDK INSTANCE;

    @Inject
    Lazy<ShoppingAPIImpl> shoppingAPILazy;

    private boolean isSDKInitialized;


    public static NetworkSDK getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkSDK();
        }
        return INSTANCE;
    }

    public void initialize() {
        NetworkComponent sdkComponent = DaggerNetworkComponent.builder()
                .build();
        sdkComponent.inject(this);
        isSDKInitialized = true;
    }

    public ShoppingAPI getShoppingAPI() {
        if (isSDKInitialized) {
            return shoppingAPILazy.get();
        } else {
            throw new SDKNotInitializedException();
        }
    }
}
