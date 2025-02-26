package com.example.tarot.network

import com.example.tarot.model.TCards
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface TarotAPI {
    @GET("/cards")
    suspend fun getAllCards(): TCards
}