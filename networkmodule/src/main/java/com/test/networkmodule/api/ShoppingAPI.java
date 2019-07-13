package com.test.networkmodule.api;


import com.test.networkmodule.response.ShopListingResponse;

import java.util.List;

import io.reactivex.Single;

public interface ShoppingAPI {

    Single<List<ShopListingResponse>> getShoppingListForMen();

    Single<List<ShopListingResponse>> getShoppingListForWomen();

    Single<List<ShopListingResponse>> getShoppingListForAll();
}
