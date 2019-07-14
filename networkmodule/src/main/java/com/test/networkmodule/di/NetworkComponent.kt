package com.test.networkmodule.di

import com.test.networkmodule.NetworkSDK
import com.test.networkmodule.di.network.CommonNetworkModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [CommonNetworkModule::class, ShoppingHostingModule::class])
interface NetworkComponent {
    fun inject(networkSDK: NetworkSDK)

    @Component.Builder
    interface Builder {

        fun build(): NetworkComponent
    }
}
