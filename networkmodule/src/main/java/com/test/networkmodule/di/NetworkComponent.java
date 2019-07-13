package com.test.networkmodule.di;

import com.test.networkmodule.NetworkSDK;
import com.test.networkmodule.di.network.CommonNetworkModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        CommonNetworkModule.class,
        ShoppingHostingModule.class})
public interface NetworkComponent {
    void inject(NetworkSDK networkSDK);

    @Component.Builder
    interface Builder {

        NetworkComponent build();
    }
}
