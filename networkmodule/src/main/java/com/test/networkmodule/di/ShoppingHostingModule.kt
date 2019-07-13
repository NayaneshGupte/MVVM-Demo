package com.test.networkmodule.di

import com.google.gson.Gson
import com.test.networkmodule.retrofitapi.ShoppingRetrofitAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ShoppingHostingModule {

    @Provides
    @Singleton
    fun provideShoppingRetrofitApi(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ShoppingRetrofitAPI {
        return Retrofit.Builder()
            // ideally should be provided from somewhere like build config. Hard coding for test purpose
            .baseUrl("https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ShoppingRetrofitAPI::class.java)
    }
}
