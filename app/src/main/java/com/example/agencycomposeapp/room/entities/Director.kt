package com.example.agencycomposeapp.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Director(
    @PrimaryKey(autoGenerate = false)
    val directorId: UUID,
    val directorName: String,
    val directorPhone: String,
    val directorPassword: String,
    val agencyId: UUID
)