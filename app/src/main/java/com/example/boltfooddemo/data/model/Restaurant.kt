package com.example.boltfooddemo.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class Restaurant(
    @PrimaryKey(autoGenerate = true)
    val restaurantID: Int = 0,
    val address: String,
    val parkingLot: String,
    val restaurantName: String,
    val type: String
)