package com.test.shoppingapp.shoppinglist.di

import androidx.appcompat.app.AppCompatActivity

import com.test.shoppingapp.shoppinglist.ShoppingListActivity

import dagger.Module
import dagger.Provides

@Module
class ShoppingListModule {

    @Provides
    fun provideShoppingListActivity(shoppingListActivity: ShoppingListActivity): AppCompatActivity {
        return shoppingListActivity
    }
}
