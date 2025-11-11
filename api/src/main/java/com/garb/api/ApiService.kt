// ApiService.kt

package com.garb.api

import com.garb.api.models.InvoiceGenerateModel
import com.garb.api.models.InvoiceNitModel
import com.garb.api.models.LoginModel
import com.garb.api.models.MachineModel
import com.garb.api.models.OrderPosSaveModel
import com.garb.api.models.OrderQrCodeModel
import com.garb.api.models.ProductInventoryModel
import com.garb.api.models.ProductModel
import com.garb.api.request.DispatchRequest
import com.garb.api.request.InvoiceGenerateRequest
import com.garb.api.request.InvoiceNitRequest
import com.garb.api.request.LoginRequest
import com.garb.api.request.OrderPosSaveRequest
import com.garb.api.request.QrCodeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Cloud URLs
    companion object {
        // ✅ Definir constantes dentro de companion object, no como propiedades de instancia
        const val AUTH_LOGIN = "/api/v1/auth/login"
        const val INVENTORY = "/api/v1/inventory"
        const val DISPATCH = "/api/v1/dispatch"
        const val INVOICE = "/invoice-service/api/v1/invoice"
        const val PRODUCT = "/api/v1/product"
        const val MACHINE = "/api/v1/machine"
        const val ORDER = "/api/v1/order"
    }

    // Get DEVICE INFO
    @GET("$MACHINE/getBySerialNumberWithIdCompany/{serialNumber}")
    suspend fun getMachine(@Path("serialNumber") serialNumber: String?): Response<MachineModel?>?

    // LOGIN
    @POST(AUTH_LOGIN)
    suspend fun getAuth(@Body loginRequest: LoginRequest?): Response<LoginModel?>?

    @GET("$PRODUCT/getAll")
    suspend fun getProductos(): Response<MutableList<ProductModel?>?>?

    // OBTENER IDS DE INVENTARIO
    @GET("$INVENTORY/getAll")
    suspend fun getInventoryIds(@Query("idMachine") idMachine: Int, @Query("withDetails") withDetails: Boolean): Response<ProductInventoryModel?>?

    // VALIDAR INVENTARIO
    @POST("$ORDER/pos/validateInventory")
    suspend fun validateInventory(@Body reqData: OrderPosSaveRequest.Order?): Response<Any?>?

    // CONFIRMAR ENTREGA DE PRODUCTO
    @PUT("$ORDER/pos/confirmDelivery/{idOrder}")
    suspend fun productConfirmDelivery(
        @Body orderPosConfirmDeliveryRequesta: MutableList<OrderPosSaveModel.SelectedInventory?>?,
        @Path("idOrder") idOrder: Long
    ): Response<Any?>?

    // CONFIRMAR ENTREGA DE PRODUCTO POR QR
    @PUT("$ORDER/qr/confirmDelivery/{idOrder}")
    suspend fun productConfirmDeliveryQr(
        @Body orderQrCodeModel: MutableList<OrderQrCodeModel.SelectedInventory?>?,
        @Path("idOrder") idOrder: Long
    ): Response<Any?>?

    // GUARFAR LA RECETA
    @POST("$ORDER/pos/save")
    suspend fun productPosSave(@Body reqData: OrderPosSaveRequest?): Response<OrderPosSaveModel?>?

    // CONFIRMAR ENTREGA DE PRODUCTO POR CODIGO PROMOCIONAL
    @POST("$ORDER/prom/code/confirmDelivery/{idOrder}")
    suspend fun productDeliveryCod(@Body reqData: Any?, @Path("idOrder") idOrder: String?): Response<Any?>?

    // VERIFICAR QR DE ORDEN
    @POST("$ORDER/qr/verify")
    suspend fun orderQrVerify(@Body qrCodeRequest: QrCodeRequest?): Response<OrderQrCodeModel?>?

    // CREAR FACTURA
    @POST("$INVOICE/generate")
    suspend fun invoiceGenerate(@Body reqData: InvoiceGenerateRequest?): Response<InvoiceGenerateModel?>?

    // CONSULTAR NIT
    @POST("$INVOICE/nit")
    suspend fun invoiceNit(@Body reqData: InvoiceNitRequest?): Response<InvoiceNitModel?>?

    // DISPATCH API
    @POST("$DISPATCH/products")
    suspend fun nucOrderDispatchProducts(@Body qrCodeRequest: DispatchRequest?): Response<Any?>?

    @GET("$DISPATCH/confirm")
    suspend fun nucOrderDispatchVerify(): Response<Any?>?


}
