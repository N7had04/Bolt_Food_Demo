package com.example.boltfooddemo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "favourite_restaurants")
@Serializable
data class FavRestaurant(
    @PrimaryKey(autoGenerate = true)
    val restaurantID: Int = 0,
    val address: String,
    val parkingLot: String,
    val restaurantName: String,
    val type: String
)
