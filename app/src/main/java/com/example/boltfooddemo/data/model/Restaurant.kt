package com.example.boltfooddemo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Entity(tableName = "restaurant")
@Serializable
data class Restaurant(
    @PrimaryKey
    val restaurantID: Int,
    val address: String,
    val parkingLot: String,
    val restaurantName: String,
    val type: String
): Parcelable