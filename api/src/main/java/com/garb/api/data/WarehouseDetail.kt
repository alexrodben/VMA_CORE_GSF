package com.garb.api.data

data class WarehouseDetail(
    val idWhDetail: Long = 0,
    val idProduct: String? = null,
    val lot: String? = null,
    val expirationDate: String? = null
)