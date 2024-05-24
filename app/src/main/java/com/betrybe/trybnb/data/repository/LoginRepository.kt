package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.models.LoginRequest
import com.betrybe.trybnb.data.models.Response
import com.betrybe.trybnb.data.models.Token
import com.betrybe.trybnb.data.network.LoginDataSource

class LoginRepository(private val mLoginDataSource: LoginDataSource = LoginDataSource()) {
    suspend fun login(request: LoginRequest): Response<Token> {
        try {
            val currentToken = mLoginDataSource.getToken(request)
            if (currentToken?.token != null) {
                val token = Token(currentToken.token)
                return Response(true, "", token)
            }
        } catch (e: Exception) {
            return Response(false, e.message.orEmpty(), null)
        }
        return Response(false, "Erro ao fazer login", null)
    }
}