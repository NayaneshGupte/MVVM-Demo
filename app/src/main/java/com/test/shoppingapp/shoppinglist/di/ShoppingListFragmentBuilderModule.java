package com.test.shoppingapp.shoppinglist.di;

import com.test.shoppingapp.shoppinglist.fragment.ItemFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ShoppingListFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ItemFragment bindItemFragment();
}
