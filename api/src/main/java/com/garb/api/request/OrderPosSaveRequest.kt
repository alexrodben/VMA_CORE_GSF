package com.garb.api.request

import com.garb.api.models.InvoiceGenerateModel
import com.garb.api.models.InvoiceNitModel

data class OrderPosSaveRequest(
    val order: Order? = null,
    val invoice: Invoice? = null,
    val payTrans: Any? = null,
    val customerInfo: CustomerInfo? = null
) {

    data class Invoice(val invoiceGenerateModel: InvoiceGenerateModel) {

        val idOrder: Int = 0
        val isAnnulled: Boolean = false

        var certifierName: String? = invoiceGenerateModel.invoiceCertifier?.name
        var idCertifier: Long = invoiceGenerateModel.invoiceCertifier?.idCertifier ?: 0L

        var internalId: String? = invoiceGenerateModel.invoice?.internalId
        var name: String? = invoiceGenerateModel.invoice?.name
        var tin: String? = invoiceGenerateModel.invoice?.tin
        var invoiceDate: String? = invoiceGenerateModel.invoice?.invoiceDate
        var serialNumber: String? = invoiceGenerateModel.invoice?.serialNumber
        var invoiceNumber: String? = invoiceGenerateModel.invoice?.invoiceNumber
        var address: String? = invoiceGenerateModel.invoice?.address
        var description: String? = invoiceGenerateModel.invoice?.description
        var taxesTotal: Double = invoiceGenerateModel.invoice?.taxesTotal ?: 0.0
        var grandTotal: Double = invoiceGenerateModel.invoice?.grandTotal ?: 0.0
        var establishmentCode: String? = invoiceGenerateModel.invoice?.establishmentCode
    }

    data class Order(
        val idMachine: Long = 0,
        val items: MutableList<Item> = mutableListOf()
    )

    data class Item(
        val idProduct: String? = null,
        val quantity: Long = 0,
        val lot: String? = null
    )

    data class CustomerInfo(val invoice: InvoiceNitModel) {
        val idOrder: Long = 0
        val email: String? = invoice.email
        val name: String? = invoice.name
        val tin: String? = invoice.tin
    }
}
