package com.test.shoppingapp.shoppinglist


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

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

    fun getErrorData(): LiveData<String> {
        return shoppingRepository.errorData
    }

    override fun onCleared() {
        super.onCleared()
        shoppingRepository.onCleared()
    }

    companion object {
        private const val ALL = 0
        private const val MEN = 1
        private const val WOMEN = 2
    }
}
