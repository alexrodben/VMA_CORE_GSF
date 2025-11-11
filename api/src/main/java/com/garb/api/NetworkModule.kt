// NetworkModule.kt
package com.garb.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val token = TokenProvider.getToken()

        val newRequest = if (!token.isNullOrEmpty()) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        chain.proceed(newRequest)
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authInterceptor)
        .build()

    private var retrofit: Retrofit? = null
    private var apiService: ApiService? = null

    fun init(baseUrl: String) {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        apiService = retrofit?.create(ApiService::class.java)
    }

    fun getApi(): ApiService {
        return apiService ?: throw IllegalStateException("⚠️ NetworkModule no inicializado. Llama a init(baseUrl) primero.")
    }
}

/*
    NetworkModule.init(""https://dev.gateway.contasa-services.com:444"")
    val api = NetworkModule.getApi()
 */