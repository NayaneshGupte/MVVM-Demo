package com.test.shoppingapp.shoppinglist.di

import com.test.shoppingapp.shoppinglist.fragment.ItemFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ShoppingListFragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun bindItemFragment(): ItemFragment
}
