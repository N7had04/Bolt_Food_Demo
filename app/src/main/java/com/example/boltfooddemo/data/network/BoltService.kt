package com.example.boltfooddemo.data.network

import com.example.boltfooddemo.data.model.MenuItem
import com.example.boltfooddemo.data.model.Restaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BoltService {

    @GET("api/Restaurant")
    suspend fun getAllRestaurants(): Response<List<Restaurant>>

    @GET("api/Restaurant/{id}/menu")
    suspend fun getMenu(
        @Path("id") id: Int
    ): Response<List<MenuItem>>
}