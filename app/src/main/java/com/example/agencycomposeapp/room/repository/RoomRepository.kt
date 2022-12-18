package com.example.agencycomposeapp.room.repository

import androidx.lifecycle.LiveData
import com.example.agencycomposeapp.room.daos.AgencyDao
import com.example.agencycomposeapp.room.entities.Agency
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.room.entities.Director
import com.example.agencycomposeapp.room.entities.Flat
import com.example.agencycomposeapp.room.relations.ClientsFlatCrossRef
import com.example.agencycomposeapp.room.relations.FlatWithClient
import java.util.*

class RoomRepository(private val agencyDao: AgencyDao) : DatabaseRepository {

    override val getAllFlats: LiveData<List<Flat>>
        get() = agencyDao.getAllFlats()

    override val getAllAgencies: LiveData<List<Agency>>
        get() = agencyDao.getAllAgencies()

    override fun getAgencyById(agencyId: UUID): Agency? =
        agencyDao.getAgencyById(agencyId = agencyId)

    override fun getClientByPhone(clientPhoneNumber: String): Client? =
        agencyDao.getClientByPhone(clientPhoneNumber)

    override fun getDirectorByPhone(directorPhoneNumber: String): Director? =
        agencyDao.getDirectorByPhone(directorPhoneNumber)

    override fun getFlatsOfClient(clientId: UUID) = agencyDao.getFlatsOfClient(clientId)

    override fun getClientsOfFlat(flatId: UUID): List<FlatWithClient> =
        agencyDao.getClientsOfFlat(flatID = flatId)

    override fun getFlatsOfDirectorsAgency(directorId: UUID): List<Flat> =
        agencyDao.getFlatsOfDirectorsAgency(directorId)

    override fun getClientsOfDirectorsAgency(directorId: UUID): List<Client> =
        agencyDao.getClientsOfDirectorsAgency(directorId = directorId)

    override suspend fun createFlat(flat: Flat, onSuccess: () -> Unit) {
        agencyDao.insertFlat(flat = flat)
        onSuccess()
    }

    override suspend fun updateFlat(flat: Flat, onSuccess: () -> Unit) {
        agencyDao.updateFlat(flat = flat)
        onSuccess()
    }

    override suspend fun updateFlatCrossRef(flatId: UUID, clientId: UUID, onSuccess: () -> Unit) {
        agencyDao.updateFlatCrossRef(flatId = flatId, clientId = clientId)
        onSuccess()
    }

    override suspend fun deleteFlatById(flatId: UUID, onSuccess: () -> Unit) {
        agencyDao.deleteFlatById(flatId = flatId)
        onSuccess()
    }

    override suspend fun deleteClientById(clientId: UUID, onSuccess: () -> Unit) {
        agencyDao.deleteClientById(clientId = clientId)
        onSuccess()
    }

    override suspend fun createClient(client: Client, onSuccess: () -> Unit) {
        agencyDao.insertClient(client = client)
    }

    override suspend fun createDirector(director: Director, onSuccess: () -> Unit) {
        agencyDao.insertDirector(director = director)
    }

    override suspend fun createAgency(agency: Agency, onSuccess: () -> Unit) {
        agencyDao.insertAgency(agency = agency)
    }

    override suspend fun insertDirectorAndAgency(
        director: Director,
        agency: Agency,
        onSuccess: () -> Unit
    ) {
        agencyDao.insertDirectorAndAgency(agency = agency, director = director)
    }

    override suspend fun insertFlatAndCrossRef(
        flat: Flat,
        crossRef: ClientsFlatCrossRef,
        onSuccess: () -> Unit
    ) {
        agencyDao.insertFlatAndCrossRef(flat = flat, crossRef = crossRef)
    }
}