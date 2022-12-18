package com.example.agencycomposeapp.room.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.room.entities.Flat

data class ClientWithFlats(
    @Embedded val client: Client,
    @Relation(
        parentColumn = "clientId",
        entityColumn = "flatId",
        associateBy = Junction(ClientsFlatCrossRef:: class)
    )
    val flats: List<Flat>
)