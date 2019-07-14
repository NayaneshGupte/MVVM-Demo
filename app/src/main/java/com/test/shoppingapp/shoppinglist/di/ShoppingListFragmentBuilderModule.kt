package com.test.shoppingapp.shoppinglist.di

import com.test.shoppingapp.shoppinglist.fragment.ShoppingListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ShoppingListFragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun bindItemFragment(): ShoppingListFragment
}
