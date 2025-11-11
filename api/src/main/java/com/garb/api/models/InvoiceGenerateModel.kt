package com.garb.api.models

data class InvoiceGenerateModel(
    val invoiceResult: Boolean = false,
    val invoiceCertifier: InvoiceCertifier? = null,
    val invoice: Invoice? = null,
    val errorDescription: String? = null,
) {
    data class Invoice(
        val internalId: String? = null,
        val name: String? = null,
        val tin: String? = null,
        val invoiceDate: String? = null,
        val serialNumber: String? = null,
        val invoiceNumber: String? = null,
        val address: String? = null,
        val description: String? = null,
        val taxesTotal: Double = 0.0,
        val grandTotal: Double = 0.0,
        val establishmentCode: String? = null,
    )

    data class InvoiceCertifier(
        val idCertifier: Long = 0,
        val name: String? = null,
        val zipCode: Long = 0,
        val country: String? = null,
    )
}
