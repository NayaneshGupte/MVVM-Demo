package com.test.networkmodule


import com.test.networkmodule.api.ShoppingAPI
import com.test.networkmodule.api.ShoppingAPIImpl
import com.test.networkmodule.di.DaggerNetworkComponent
import com.test.networkmodule.exceptions.SDKNotInitializedException
import dagger.Lazy
import javax.inject.Inject

class NetworkSDK {

    @Inject
    lateinit var shoppingAPILazy: Lazy<ShoppingAPIImpl>

    private var isSDKInitialized: Boolean = false

    val shoppingAPI: ShoppingAPI
        get() = when {
            isSDKInitialized -> shoppingAPILazy.get()
            else -> throw SDKNotInitializedException(
                "Network SDK is not initialized," +
                        "Please call the initialize method of SDK Manager"
            )
        }

    fun initialize() {
        val sdkComponent = DaggerNetworkComponent.builder()
            .build()
        sdkComponent.inject(this)
        isSDKInitialized = true
    }

    companion object {
        private var INSTANCE: NetworkSDK? = null

        val instance: NetworkSDK
            get() {
                when (INSTANCE) {
                    null -> INSTANCE = NetworkSDK()
                }
                return INSTANCE!!
            }
    }
}
