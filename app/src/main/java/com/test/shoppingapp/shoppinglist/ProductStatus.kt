package com.test.shoppingapp.shoppinglist

import android.support.annotation.StringDef

@StringDef
annotation class ProductStatus {
    companion object {
        val SOLD_OUT = "sold_out"
        val ON_SALE = "on_sale"
    }
}
