package com.example.wetpka.model

data class WaterBody(
    val id: Int,
    val name: String,
    val region: String,
    val sizeInfo: String,
    val district: String,
    val latitude: Double,
    val longitude: Double,
    val imageResId: Int
)