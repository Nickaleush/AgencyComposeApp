package com.example.agencycomposeapp.room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(indices = [Index(value = ["clientPhoneNumber"], unique = true)])
data class Client(
    @PrimaryKey(autoGenerate = false)
    val clientId: UUID,
    val clientPhoneNumber: String,
    val clientName: String,
    val clientPassword: String,
    val agencyId: UUID
)