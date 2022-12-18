package com.example.agencycomposeapp.room.relations

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["clientId", "flatId"])
data class ClientsFlatCrossRef(
    val clientId: UUID,
    val flatId: UUID
)