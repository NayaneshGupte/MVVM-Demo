package com.test.networkmodule.api


import com.test.networkmodule.backend.OkHttpTestReceiver
import com.test.networkmodule.backend.ShoppingReceiver
import com.test.networkmodule.response.Page
import com.test.networkmodule.response.ShopListingResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * There could be multiple APIs which this implementation needs to handle.
 * So don't bloat this class with actual implementation.
 * Delegate this work to respective receiver.
 *
 */
@Singleton
class ShoppingAPIImpl @Inject
constructor(private val shoppingReceiverProvider: Provider<ShoppingReceiver>,
            private val okHttpTestReceiverProvider: Provider<OkHttpTestReceiver>) : ShoppingAPI {

    override val shoppingListForMen: Single<List<ShopListingResponse>>
        get() = shoppingReceiverProvider.get().getShoppingList("Men")

    override val shoppingListForWomen: Single<List<ShopListingResponse>>
        get() = shoppingReceiverProvider.get().getShoppingList("Women")

    override val shoppingListForAll: Single<List<ShopListingResponse>>
        get() = shoppingReceiverProvider.get().getShoppingList("All")

    override val getPage: Single<Page>
        get() = okHttpTestReceiverProvider.get().getPage()
}
