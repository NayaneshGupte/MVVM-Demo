package com.test.networkmodule.di.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import com.test.networkmodule.BuildConfig
import com.test.networkmodule.parser.CustomTypeAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class CommonNetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Log.d(TAG, message) }.setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }

    @Provides
    @Singleton
    fun provideTypeAdapterFactory(): TypeAdapterFactory {
        return CustomTypeAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGson(typeAdapterFactory: TypeAdapterFactory): Gson {
        return GsonBuilder()
            .registerTypeAdapterFactory(typeAdapterFactory)
            .create()
    }

    // Use newBuilder() to customize so that thread-pool and connection-pool same are used
    //https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html
    @Provides
    fun provideOkHttpClientBuilder(client: OkHttpClient): OkHttpClient.Builder {
        return client.newBuilder()
    }

    @Provides
    @Singleton
    fun customEventListener(): CustomEventListener {
        return CustomEventListener { message -> Log.d(TAG, message) }
    }

    @Provides
    @Singleton
    fun provideBaseOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        customEventListener: CustomEventListener
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        builder.eventListener(customEventListener)

        return builder.build()
    }

    companion object {
        private val TAG = "Okhttp"
    }

}
