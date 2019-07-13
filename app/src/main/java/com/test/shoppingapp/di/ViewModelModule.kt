package com.test.shoppingapp.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.test.shoppingapp.shoppinglist.ShoppingListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ShoppingViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(ShoppingListViewModel::class)
    internal abstract fun bindShoppingListViewModel(homeViewModel: ShoppingListViewModel): ViewModel
}
