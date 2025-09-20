package com.example.boltfooddemo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val password: String
)
