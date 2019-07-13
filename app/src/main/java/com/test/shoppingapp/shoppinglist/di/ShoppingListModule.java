package com.test.shoppingapp.shoppinglist.di;

import android.support.v7.app.AppCompatActivity;

import com.test.shoppingapp.shoppinglist.ShoppingListActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ShoppingListModule {

    @Provides
    public AppCompatActivity provideShoppingListActivity(ShoppingListActivity shoppingListActivity) {
        return shoppingListActivity;
    }
}
