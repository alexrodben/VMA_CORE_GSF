package com.garb.api.request

import com.garb.api.models.MachineModel
import com.squareup.moshi.Json

class LoginRequest(machine: MachineModel) {
    @Json(name = "userName")
    val userName: String = machine.idCompany + "_apkInstance"

    @Json(name = "password")
    val password: String = "hrt3ZyWY)fEvfhCh" + machine.idCompany
}