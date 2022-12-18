package com.example.agencycomposeapp.room.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.room.entities.Flat

data class FlatWithClient(
    @Embedded val flat: Flat,
    @Relation(
        parentColumn = "flatId",
        entityColumn = "clientId",
        associateBy = Junction(ClientsFlatCrossRef:: class)
    )
    val clients: List<Client>
)