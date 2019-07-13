package com.test.networkmodule.api


import com.test.networkmodule.response.ShopListingResponse

import io.reactivex.Single

interface ShoppingAPI {

    val shoppingListForMen: Single<List<ShopListingResponse>>

    val shoppingListForWomen: Single<List<ShopListingResponse>>

    val shoppingListForAll: Single<List<ShopListingResponse>>
}
