package com.garb.aidl

import android.os.Parcel
import android.os.Parcelable

data class PaymentSuccess(
    var invoiceNumber: String? = null,
    var merchantName: String? = null,
    var headerStatus: String? = null,
    var responseCode: String? = null,
    var totalAmount: Double = 0.0,
    var terminalId: String? = null,
    var issuerName: String? = null,
    var authNumber: String? = null,
    var transDate: String? = null,
    var transHour: String? = null,
    var pan: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(invoiceNumber)
        parcel.writeString(merchantName)
        parcel.writeString(headerStatus)
        parcel.writeString(responseCode)
        parcel.writeDouble(totalAmount)
        parcel.writeString(terminalId)
        parcel.writeString(issuerName)
        parcel.writeString(authNumber)
        parcel.writeString(transDate)
        parcel.writeString(transHour)
        parcel.writeString(pan)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<PaymentSuccess> {
        override fun createFromParcel(parcel: Parcel) = PaymentSuccess(parcel)
        override fun newArray(size: Int) = arrayOfNulls<PaymentSuccess?>(size)
    }
}