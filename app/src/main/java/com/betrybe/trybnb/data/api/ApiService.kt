package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.LoginRequest
import com.betrybe.trybnb.data.models.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth")
    suspend fun getToken(
        @Body request: LoginRequest
    ): Response<Token>
}