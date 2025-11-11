package com.garb.api.request

import com.garb.api.models.OrderPosSaveModel

data class DispatchRequest(
    val idOrder: Long = 0,
    val selectedInventory: MutableList<SelectedInventory?>? = null
) {
    data class SelectedInventory(
        val idMachine: Long = 0,
        val idRow: Long = 0,
        val idColumn: Long = 0,
        val idProduct: String? = null,
        val lot: String? = null,
        val qty: Long = 0
    )
}
