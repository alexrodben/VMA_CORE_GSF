// TokenProvider.kt

package com.garb.api

object TokenProvider {
    private var token: String? = null

    fun getToken(): String? = token

    fun saveToken(newToken: String) {
        token = newToken
    }

    fun clear() {
        token = null
    }
}
