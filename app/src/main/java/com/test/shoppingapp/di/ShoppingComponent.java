package com.test.shoppingapp.di;

import android.app.Application;

import com.test.shoppingapp.ShoppingApplication;
import com.test.shoppingapp.network.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
        NetworkModule.class})
public interface ShoppingComponent {

    void inject(ShoppingApplication shoppingApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ShoppingComponent build();
    }
}
