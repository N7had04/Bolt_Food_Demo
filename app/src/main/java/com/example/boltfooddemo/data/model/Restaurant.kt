package com.example.boltfooddemo.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    val restaurantID: Int,
    val address: String,
    val parkingLot: String,
    val restaurantName: String,
    val type: String
)