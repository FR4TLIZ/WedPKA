package com.example.wetpka.model

data class Fish(
    val id: Int,
    val name: String,
    val latinName: String,
    val englishName: String,
    val category: String,
    val protectionSize: String,
    val dailyLimit: String,
    val protectionPeriod: String,
    val spawningTime: String,
    val regions: String,
    val habitat: String,
    val goodBites: String,
    val badBites: String,
    val imageResId: Int
)