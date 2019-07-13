package com.test.shoppingapp.di


import com.test.shoppingapp.shoppinglist.ShoppingListActivity
import com.test.shoppingapp.shoppinglist.di.ShoppingListFragmentBuilderModule
import com.test.shoppingapp.shoppinglist.di.ShoppingListModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ShoppingListModule::class, ShoppingListFragmentBuilderModule::class])
    internal abstract fun bindShoppingListActivity(): ShoppingListActivity
}
