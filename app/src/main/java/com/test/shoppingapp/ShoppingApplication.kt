package com.test.shoppingapp


import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.test.networkmodule.NetworkSDK
import com.test.shoppingapp.di.AppInjector
import com.test.shoppingapp.di.DaggerShoppingComponent
import dagger.Lazy
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ShoppingApplication : MultiDexApplication(), HasActivityInjector {

    @set:Inject
    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null

    @set:Inject
    var networkSDKLazy: Lazy<NetworkSDK>? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeDI()

    }


    private fun initializeDI() {
        val shoppingComponent = DaggerShoppingComponent.builder()
            .application(this)
            .build()

        shoppingComponent.inject(this)
        AppInjector.init()
        networkSDKLazy!!.get().initialize()
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    companion object {

        var instance: ShoppingApplication? = null
            private set
    }
}
