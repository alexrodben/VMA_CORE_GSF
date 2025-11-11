package com.garb.api.models

data class CategoryModel(
    val idCategory: Long = 0,
    val name: String? = null,
    val description: String? = null,
    val enabled: Boolean = false,
)