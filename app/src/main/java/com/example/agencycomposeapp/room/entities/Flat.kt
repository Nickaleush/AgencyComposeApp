package com.example.agencycomposeapp.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity
data class Flat(
    @PrimaryKey(autoGenerate = false)
    val flatId: UUID,
    val flatAddress: String,
    val flatRoomCount: Long,
    val flatSpace: Long,
    val flatCost: Long
)