package com.example.wetpka.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val passwordHash: String,
    val firstName: String,
    val lastName: String,
    val cardNumber: String,
    val district: String,
    val validFrom: String,
    val validTo: String,
    val memberSince: String,
    val membershipPaidTo: String = "",
    val permitValidTo: String = "",
    val seaPermitValidTo: String = ""
)

