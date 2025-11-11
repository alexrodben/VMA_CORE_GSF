package com.garb.api.request

import com.garb.api.models.InvoiceNitModel

data class InvoiceGenerateRequest(
    val products: MutableList<Item?>? = null,
    val person: InvoiceNitModel? = null,
    val establishmentCode: String? = null,
    val idMachine: Long = 0,
) {
    data class Item(
        val description: String? = null,
        val discount: Double = 0.0,
        val quantity: Long = 0,
        val price: Double = 0.0,
        val tax: Boolean = false,
    )
}