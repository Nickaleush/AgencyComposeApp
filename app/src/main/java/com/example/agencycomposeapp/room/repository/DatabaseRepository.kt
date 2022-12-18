package com.example.agencycomposeapp.room.repository

import androidx.lifecycle.LiveData
import com.example.agencycomposeapp.room.entities.Agency
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.room.entities.Director
import com.example.agencycomposeapp.room.entities.Flat
import com.example.agencycomposeapp.room.relations.ClientWithFlats
import com.example.agencycomposeapp.room.relations.ClientsFlatCrossRef
import com.example.agencycomposeapp.room.relations.FlatWithClient
import java.util.*

interface DatabaseRepository {

    val getAllFlats: LiveData<List<Flat>>

    val getAllAgencies: LiveData<List<Agency>>

    fun getAgencyById(agencyId: UUID): Agency?

    fun getFlatsOfDirectorsAgency(directorId: UUID): List<Flat>

    fun getClientsOfDirectorsAgency(directorId: UUID): List<Client>

    fun getClientByPhone(clientPhoneNumber: String): Client?

    fun getDirectorByPhone(directorPhoneNumber: String): Director?

    fun getFlatsOfClient(clientId: UUID): List<ClientWithFlats>

    fun getClientsOfFlat(flatId: UUID): List<FlatWithClient>

    suspend fun createFlat(flat: Flat, onSuccess: () -> Unit)

    suspend fun updateFlat(flat: Flat, onSuccess: () -> Unit)

    suspend fun updateFlatCrossRef(flatId: UUID, clientId: UUID, onSuccess: () -> Unit)

    suspend fun deleteFlatById(flatId: UUID, onSuccess: () -> Unit)

    suspend fun deleteClientById(clientId: UUID, onSuccess: () -> Unit)

    suspend fun createClient(client: Client, onSuccess: () -> Unit)

    suspend fun createDirector(director: Director, onSuccess: () -> Unit)

    suspend fun createAgency(agency: Agency, onSuccess: () -> Unit)

    suspend fun insertDirectorAndAgency(director: Director, agency: Agency, onSuccess: () -> Unit)

    suspend fun insertFlatAndCrossRef(
        flat: Flat,
        crossRef: ClientsFlatCrossRef,
        onSuccess: () -> Unit
    )

}