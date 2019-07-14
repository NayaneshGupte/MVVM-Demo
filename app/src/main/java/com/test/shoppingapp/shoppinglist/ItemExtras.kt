package com.test.shoppingapp.shoppinglist

import android.os.Parcel
import android.os.Parcelable

/**
 * This data class serves purpose of passing data to ShoppingListFragment.
 *
 * This is to avoid remembering what data destination fragment / activity is expecting and
 * what keys need to be passed in bundle. Makes code more readable.
 */
data class ItemExtras(val position: Int) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(position)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemExtras> {
        override fun createFromParcel(parcel: Parcel): ItemExtras {
            return ItemExtras(parcel)
        }

        override fun newArray(size: Int): Array<ItemExtras?> {
            return arrayOfNulls(size)
        }
    }
}