package com.example.boltfooddemo.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MenuItem(
    val itemID: Int,
    val imageUrl: String,
    val itemDescription: String,
    val itemName: String,
    val itemPrice: Double,
    val restaurantID: Int,
    val restaurantName: String
)