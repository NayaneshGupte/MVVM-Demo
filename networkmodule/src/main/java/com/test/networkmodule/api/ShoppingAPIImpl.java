package com.test.networkmodule.api;


import com.test.networkmodule.backend.ShoppingReceiver;
import com.test.networkmodule.response.ShopListingResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class ShoppingAPIImpl implements ShoppingAPI {

    private final Provider<ShoppingReceiver> shoppingReceiverProvider;

    @Inject
    public ShoppingAPIImpl(Provider<ShoppingReceiver> shoppingReceiverProvider) {
        this.shoppingReceiverProvider = shoppingReceiverProvider;
    }

    @Override
    public Single<List<ShopListingResponse>> getShoppingListForMen() {
        return shoppingReceiverProvider.get().getShoppingList("Men");
    }

    @Override
    public Single<List<ShopListingResponse>> getShoppingListForWomen() {
        return shoppingReceiverProvider.get().getShoppingList("Women");
    }

    @Override
    public Single<List<ShopListingResponse>> getShoppingListForAll() {
        return shoppingReceiverProvider.get().getShoppingList("All");
    }
}
