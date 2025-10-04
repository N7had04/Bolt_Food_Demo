package com.example.boltfooddemo.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "menu_item")
data class MenuItem(
    @PrimaryKey(autoGenerate = true)
    val itemID: Int = 0,
    val imageUrl: String,
    val itemDescription: String,
    val itemName: String,
    val itemPrice: Double,
    val restaurantID: Int,
    val restaurantName: String
)