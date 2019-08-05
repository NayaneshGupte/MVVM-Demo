package com.test.shoppingapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.test.shoppingapp.shoppinglist.ShoppingListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(ShoppingListViewModel::class)
    internal abstract fun bindShoppingListViewModel(shoppingListViewModel: ShoppingListViewModel): ViewModel
}
