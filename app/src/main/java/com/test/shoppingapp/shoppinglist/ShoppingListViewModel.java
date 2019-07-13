package com.test.shoppingapp.shoppinglist;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.test.networkmodule.response.ShopListingResponse;
import com.test.shoppingapp.shoppinglist.repository.ShoppingRepository;

import java.util.List;

import javax.inject.Inject;

public class ShoppingListViewModel extends ViewModel {

    private static final int ALL = 0;
    private static final int MEN = 1;
    private static final int WOMEN = 2;

    private final ShoppingRepository shoppingRepository;

    @Inject
    public ShoppingListViewModel(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    public void findListFromIndex(int position) {
        switch (position) {
            case ALL:
                shoppingRepository.getShoppingListForAll();
                break;

            case MEN:
                shoppingRepository.getShoppingListForMen();
                break;

            case WOMEN:
                shoppingRepository.getShoppingListForWomen();
                break;
        }
    }

    public LiveData<List<ShopListingResponse>> getLiveDataForShoppingList(int position) {
        switch (position) {
            case ALL:
                return shoppingRepository.getAllShoppingList();

            case MEN:
                return shoppingRepository.getMensShoppingList();

            case WOMEN:
                return shoppingRepository.getWomensShoppingList();
        }
        return null;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        shoppingRepository.onCleared();
    }
}
