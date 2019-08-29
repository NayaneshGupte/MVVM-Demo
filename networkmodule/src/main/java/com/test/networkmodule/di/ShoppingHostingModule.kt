package com.test.networkmodule.di

import com.test.networkmodule.retrofitapi.ShoppingRetrofitAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
internal object ShoppingHostingModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideShoppingRetrofitApi(oktHttpClient: OkHttpClient): ShoppingRetrofitAPI =
        Retrofit.Builder()
            .client(oktHttpClient)
            // ideally should be provided from somewhere like build config. Hard coding for test purpose
            .baseUrl("https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ShoppingRetrofitAPI::class.java)

}
