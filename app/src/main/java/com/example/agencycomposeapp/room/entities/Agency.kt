package com.example.agencycomposeapp.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Agency(
    @PrimaryKey(autoGenerate = false)
    val agencyId: UUID,
    val agencyName: String,
)