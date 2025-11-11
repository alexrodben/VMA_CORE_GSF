package com.garb.api.models

import com.squareup.moshi.Json

data class Error404Model(
    @Json(name = "message_error")
    val messageError: String
)
