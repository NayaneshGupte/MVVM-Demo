package com.test.shoppingapp

import com.test.networkmodule.response.ShopListingResponse

interface OnListFragmentInteractionListener {
    fun onListFragmentInteraction(item: ShopListingResponse)
}