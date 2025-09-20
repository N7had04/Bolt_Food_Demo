package com.example.boltfooddemo.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryCode(
    @SerialName("code")
    val code: String,
    @SerialName("dial_code")
    val dialCode: String,
    @SerialName("name")
    val name: String
)