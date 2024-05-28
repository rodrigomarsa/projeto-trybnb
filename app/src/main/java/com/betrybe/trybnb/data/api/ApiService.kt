package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.BookingId
import com.betrybe.trybnb.data.models.LoginRequest
import com.betrybe.trybnb.data.models.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth")
    suspend fun getToken(
        @Body request: LoginRequest
    ): Response<Token>

    @GET("booking")
    suspend fun getAllBookingIds(): Response<List<BookingId>>

    @GET("booking/{id}")
    @Headers("Accept: application/json")
    suspend fun getBookingById(
        @Path("id") id: String
    ): Response<Booking>
}
