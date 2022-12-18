package com.example.agencycomposeapp.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.agencycomposeapp.room.entities.Agency
import com.example.agencycomposeapp.room.entities.Director

data class AgencyAndDirector(
    @Embedded val agency: Agency,
    @Relation(
        parentColumn = "agencyId",
        entityColumn = "agencyId"
    )
    val director: Director
)