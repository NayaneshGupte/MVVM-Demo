package com.test.shoppingapp


import com.test.networkmodule.NetworkSDK
import com.test.shoppingapp.di.DaggerShoppingComponent
import com.test.shoppingapp.di.applyAutoInjector
import dagger.Lazy
import dagger.android.DaggerApplication
import javax.inject.Inject

class ShoppingApplication : DaggerApplication() {

    @Inject
    lateinit var networkSDKLazy: Lazy<NetworkSDK>

    override fun onCreate() {
        super.onCreate()
        initializeDI()
    }

    private fun initializeDI() {
        applyAutoInjector()
        networkSDKLazy.get()?.initialize()
    }

    override fun applicationInjector() = DaggerShoppingComponent.builder()
        .application(this)
        .build()
}
