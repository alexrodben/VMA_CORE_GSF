package com.garb.api.models

data class ProductModel(
    val idProduct: String? = null,
    val sku: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val price: Double? = null,
    val isTaxes: Boolean = false,
    val enabled: Boolean = false,
    val idCategory: Long = 0,
    val category: CategoryModel? = null
)
