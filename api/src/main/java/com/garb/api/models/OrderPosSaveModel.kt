package com.garb.api.models

import com.garb.api.data.SelectedLaneDetail

data class OrderPosSaveModel(
    val order: OrderQrCode? = null,
    val selectedInventory: MutableList<SelectedInventory?>? = null
) {
    data class OrderQrCode(
        val idOrder: Long = 0,
        val orderType: String? = null,
        val idQr: String? = null,
        val idMachine: Long = 0,
        val createdAt: String? = null,
        val status: String? = null,
        val items: MutableList<OrderItem?>? = null
    )

    data class OrderItem(
        val idProduct: String? = null,
        val quantity: Long = 0,
        val name: String? = null,
        val lot: Any? = null,
        val pendingQty: Long = 0,
        val active: Boolean = false,
    )

    data class SelectedInventory(
        val idMachine: Long = 0,
        val idRow: Long = 0,
        val idColumn: Long = 0,
        val selectedLaneDetails: MutableList<SelectedLaneDetail?>? = null
    )

}
