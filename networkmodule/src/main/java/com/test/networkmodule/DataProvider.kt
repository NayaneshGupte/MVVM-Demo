package com.test.networkmodule


import com.test.networkmodule.api.ShoppingAPI
import com.test.networkmodule.api.ShoppingAPIImpl
import com.test.networkmodule.di.DaggerDataProviderComponent
import com.test.networkmodule.exceptions.SDKNotInitializedException
import dagger.Lazy
import javax.inject.Inject

class DataProvider private constructor() {

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
        val dataProviderComponent = DaggerDataProviderComponent.builder()
            .build()
        dataProviderComponent.inject(this)
        isSDKInitialized = true
    }

    companion object {
        private var INSTANCE: DataProvider? = null

        val instance: DataProvider
            get() {
                when (INSTANCE) {
                    null -> INSTANCE = DataProvider()
                }
                return INSTANCE!!
            }
    }
}
