package com.example.boltfooddemo.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    @SerialName("address")
    val address: String,
    @SerialName("parkingLot")
    val parkingLot: String,
    @SerialName("restaurantID")
    val restaurantID: Int,
    @SerialName("restaurantName")
    val restaurantName: String,
    @SerialName("type")
    val type: String
)