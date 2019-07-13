package com.test.networkmodule.di.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.test.networkmodule.BuildConfig;
import com.test.networkmodule.parser.CustomTypeAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class CommonNetworkModule {
    private static final String TAG = "Okhttp";

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.d(TAG, message)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    public TypeAdapterFactory provideTypeAdapterFactory() {
        return CustomTypeAdapterFactory.create();
    }

    @Provides
    @Singleton
    public Gson provideGson(TypeAdapterFactory typeAdapterFactory) {
        return new GsonBuilder()
                .registerTypeAdapterFactory(typeAdapterFactory)
                .create();
    }

    // Use newBuilder() to customize so that thread-pool and connection-pool same are used
    //https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html
    @Provides
    public OkHttpClient.Builder provideOkHttpClientBuilder(OkHttpClient client) {
        return client.newBuilder();
    }

    @Provides
    @Singleton
    public CustomEventListener customEventListener() {
        return new CustomEventListener(message -> Log.d(TAG, message));
    }

    @Provides
    @Singleton
    public OkHttpClient provideBaseOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                                                CustomEventListener customEventListener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor);
        }
        builder.eventListener(customEventListener);

        return builder.build();
    }

}
