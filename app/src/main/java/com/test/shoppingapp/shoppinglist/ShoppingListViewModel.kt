package com.test.shoppingapp.shoppinglist


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import com.test.networkmodule.response.ShopListingResponse
import com.test.shoppingapp.shoppinglist.repository.ShoppingRepository

import javax.inject.Inject

class ShoppingListViewModel @Inject
constructor(private val shoppingRepository: ShoppingRepository) : ViewModel() {

    fun findListFromIndex(position: Int) {
        when (position) {
            ALL -> shoppingRepository.getShoppingListForAll()

            MEN -> shoppingRepository.getShoppingListForMen()

            WOMEN -> shoppingRepository.getShoppingListForWomen()
        }
    }

    fun getLiveDataForShoppingList(position: Int): LiveData<List<ShopListingResponse>>? {
        when (position) {
            ALL -> return shoppingRepository.getAllShoppingList()

            MEN -> return shoppingRepository.getMensShoppingList()

            WOMEN -> return shoppingRepository.getWomensShoppingList()
        }
        return null
    }

    override fun onCleared() {
        super.onCleared()
        shoppingRepository.onCleared()
    }

    companion object {
        private val ALL = 0
        private val MEN = 1
        private val WOMEN = 2
    }
}
