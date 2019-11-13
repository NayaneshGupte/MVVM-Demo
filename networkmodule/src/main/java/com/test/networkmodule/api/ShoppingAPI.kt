package com.test.networkmodule.api


import com.test.networkmodule.response.Page
import com.test.networkmodule.response.ShopListingResponse

import io.reactivex.Single

/**
 * Network SDK should always expose interface and not actual implementation.
 * This way SDK can decide from where to fetch the data and provide it to the app.
 * E.g. it could load it from local DB in case network is not available.
 */
interface ShoppingAPI {

    val shoppingListForMen: Single<List<ShopListingResponse>>

    val shoppingListForWomen: Single<List<ShopListingResponse>>

    val shoppingListForAll: Single<List<ShopListingResponse>>

    val getPage: Single<Page>
}
