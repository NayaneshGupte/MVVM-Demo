package com.test.networkmodule


import com.test.networkmodule.api.ShoppingAPI
import com.test.networkmodule.api.ShoppingAPIImpl
import com.test.networkmodule.di.DaggerNetworkComponent
import com.test.networkmodule.exceptions.SDKNotInitializedException
import dagger.Lazy
import javax.inject.Inject

class NetworkSDK {

    @set:Inject
    internal var shoppingAPILazy: Lazy<ShoppingAPIImpl>? = null

    private var isSDKInitialized: Boolean = false

    val shoppingAPI: ShoppingAPI
        get() = if (isSDKInitialized) {
            shoppingAPILazy!!.get()
        } else {
            throw SDKNotInitializedException()
        }

    fun initialize() {
        val sdkComponent = DaggerNetworkComponent.builder()
            .build()
        sdkComponent.inject(this)
        isSDKInitialized = true
    }

    companion object {

        private val TAG = "NetworkSDK"
        private var INSTANCE: NetworkSDK? = null


        val instance: NetworkSDK
            get() {
                if (INSTANCE == null) {
                    INSTANCE = NetworkSDK()
                }
                return INSTANCE!!
            }
    }
}
