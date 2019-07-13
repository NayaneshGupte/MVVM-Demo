package com.test.shoppingapp.di

import android.app.Application
import com.test.shoppingapp.ShoppingApplication
import com.test.shoppingapp.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        NetworkModule::class]
)
interface ShoppingComponent {

    fun inject(shoppingApplication: ShoppingApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ShoppingComponent
    }
}
