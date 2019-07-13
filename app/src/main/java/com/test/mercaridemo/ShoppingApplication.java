package com.test.shoppingapp;


import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.test.networkmodule.NetworkSDK;
import com.test.shoppingapp.di.AppInjector;
import com.test.shoppingapp.di.DaggerShoppingComponent;
import com.test.shoppingapp.di.ShoppingComponent;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class ShoppingApplication extends MultiDexApplication implements HasActivityInjector {

    private static ShoppingApplication SHOPPING_APPLICATION_INSTANCE;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    Lazy<NetworkSDK> networkSDKLazy;

    private ShoppingComponent shoppingComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        SHOPPING_APPLICATION_INSTANCE = this;
        initializeDI();

    }

    public static ShoppingApplication getInstance() {
        return SHOPPING_APPLICATION_INSTANCE;
    }


    private void initializeDI() {
        shoppingComponent = DaggerShoppingComponent.builder()
                .application(this)
                .build();

        shoppingComponent.inject(this);
        AppInjector.init();
        networkSDKLazy.get().initialize();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
