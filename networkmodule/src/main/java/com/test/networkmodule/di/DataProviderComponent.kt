package com.test.networkmodule.di

import com.test.networkmodule.DataProvider
import com.test.networkmodule.di.network.CommonNetworkModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [CommonNetworkModule::class, ShoppingHostingModule::class])
interface DataProviderComponent {
    fun inject(dataProvider: DataProvider)

    @Component.Builder
    interface Builder {

        fun build(): DataProviderComponent
    }
}
