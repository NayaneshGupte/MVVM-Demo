package com.test.shoppingapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.test.shoppingapp.shoppinglist.ShoppingListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ShoppingViewModelFactory factory);


    @Binds
    @IntoMap
    @ViewModelKey(ShoppingListViewModel.class)
    abstract ViewModel bindShoppingListViewModel(ShoppingListViewModel homeViewModel);
}
