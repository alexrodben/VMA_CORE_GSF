package com.garb.aidl_core

import android.os.Parcel
import android.os.Parcelable

data class VendingDispatchItem(val mod: Long = 0, val row: Long = 0, val column: Long = 0, val quantity: Long = 1) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(mod)
        parcel.writeLong(row)
        parcel.writeLong(column)
        parcel.writeLong(quantity)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<VendingDispatchItem> {
        override fun createFromParcel(parcel: Parcel) = VendingDispatchItem(parcel)
        override fun newArray(size: Int) = arrayOfNulls<VendingDispatchItem?>(size)
    }
}
