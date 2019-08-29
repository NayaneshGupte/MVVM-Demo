package com.test.shoppingapp.shoppinglist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * This data class serves purpose of passing data to ShoppingListFragment.
 *
 * This is to avoid remembering what data destination fragment / activity is expecting and
 * what keys need to be passed in bundle. Makes code more readable.
 */
@Parcelize
data class ItemExtras(val position: Int) : Parcelable
