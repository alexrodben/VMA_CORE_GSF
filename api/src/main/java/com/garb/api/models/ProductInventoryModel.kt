package com.garb.api.models

import com.garb.api.data.WarehouseDetail


class ProductInventoryModel(
    val details: MutableList<Detail?>? = null,
    val inventory: MutableList<Inventory?>? = null
) {
    class Detail(
        val totalQty: Long = 0,
        val reserved: Long = 0,
        val qtyAvailable: Long = 0,
        val idProduct: String? = null
    )

    class Inventory(
        val idMachine: Long = 0,
        val idRow: Long = 0,
        val idColumn: Long = 0,
        val maxProds: Long = 0,
        val laneDetails: MutableList<LaneDetail?>? = null
    )

    class LaneDetail(
        val quantity: Long = 0,
        val warehouseDetail: WarehouseDetail? = null
    )
}