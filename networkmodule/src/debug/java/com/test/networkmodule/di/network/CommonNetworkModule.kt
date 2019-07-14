package com.test.networkmodule.di.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Module to provide okhttp and Moshi across this NetworkSDK for different APIs
 */
@Module
internal object CommonNetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    @JvmStatic
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()
}
