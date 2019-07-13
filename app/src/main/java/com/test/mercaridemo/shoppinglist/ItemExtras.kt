package com.test.shoppingapp.shoppinglist

import android.os.Parcel
import android.os.Parcelable


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