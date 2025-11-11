// ApiService.kt

package com.garb.api

import okhttp3.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}
