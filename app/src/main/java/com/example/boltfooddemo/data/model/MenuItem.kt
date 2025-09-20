package com.example.boltfooddemo.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItem(
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("itemDescription")
    val itemDescription: String,
    @SerialName("itemID")
    val itemID: Int,
    @SerialName("itemName")
    val itemName: String,
    @SerialName("itemPrice")
    val itemPrice: Double,
    @SerialName("restaurantID")
    val restaurantID: Int,
    @SerialName("restaurantName")
    val restaurantName: String
)