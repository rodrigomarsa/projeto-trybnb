package com.betrybe.trybnb.data.network

import com.betrybe.trybnb.data.api.ApiServiceClient
import com.betrybe.trybnb.data.models.LoginRequest
import com.betrybe.trybnb.data.models.Token

class LoginDataSource {
    private val mApiServiceClient = ApiServiceClient.instance

    suspend fun getToken(request: LoginRequest): Token? {
        val currentTokenResponse = mApiServiceClient.getToken(request)
        return currentTokenResponse.body()
    }
}