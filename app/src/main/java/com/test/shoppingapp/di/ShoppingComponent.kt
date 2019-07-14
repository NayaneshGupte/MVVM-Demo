package com.test.shoppingapp.di

import com.test.shoppingapp.ShoppingApplication
import com.test.shoppingapp.network.NetworkSDKModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        NetworkSDKModule::class]
)
interface ShoppingComponent : AndroidInjector<ShoppingApplication> {

    override fun inject(shoppingApplication: ShoppingApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ShoppingApplication): Builder

        fun build(): ShoppingComponent
    }
}
