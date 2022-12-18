package com.example.agencycomposeapp.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.agencycomposeapp.room.entities.Agency
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.room.entities.Director
import com.example.agencycomposeapp.room.entities.Flat
import com.example.agencycomposeapp.room.relations.*
import java.util.*

@Dao
interface AgencyDao {

    @Insert
    suspend fun insertAgency(agency: Agency)

    @Insert
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClientFlatCrossRef(crossRef: ClientsFlatCrossRef)

    @Transaction
    suspend fun insertDirectorAndAgency(director: Director, agency: Agency) {
        insertDirector(director)
        insertAgency(agency)
    }

    @Transaction
    suspend fun insertFlatAndCrossRef(flat: Flat, crossRef: ClientsFlatCrossRef) {
        insertFlat(flat)
        insertClientFlatCrossRef(crossRef)
    }

    @Insert
    suspend fun insertClient(client: Client)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlat(flat: Flat)

    @Update
    suspend fun updateFlat(flat: Flat)

    @Query("UPDATE clientsflatcrossref SET clientId = :clientId WHERE flatId = :flatId")
    suspend fun updateFlatCrossRef(clientId: UUID, flatId: UUID)

    @Query("DELETE FROM flat WHERE flatId = :flatId")
    suspend fun deleteFlatById(flatId: UUID)

    @Query("DELETE FROM client WHERE clientId = :clientId")
    suspend fun deleteClientById(clientId: UUID)

    @Query("DELETE FROM agency")
    fun deleteAllAgencies()

    @Query("SELECT * FROM flat")
    fun getAllFlats(): LiveData<List<Flat>>

    @Query("SELECT * FROM agency")
    fun getAllAgencies(): LiveData<List<Agency>>

    @Query("SELECT * FROM agency WHERE agencyId = :agencyId")
    fun getAgencyById(agencyId: UUID): Agency?

    @Query("SELECT * FROM client WHERE clientPhoneNumber = :clientPhoneNumber")
    fun getClientByPhone(clientPhoneNumber: String): Client?

    @Query("SELECT * FROM director WHERE directorPhone = :directorPhoneNumber")
    fun getDirectorByPhone(directorPhoneNumber: String): Director?

    @Transaction
    @Query("SELECT * FROM agency WHERE agencyId = :agencyId")
    suspend fun getAgencyAndDirectorWithAgencyName(agencyId: UUID): List<AgencyAndDirector>

    @Transaction
    @Query("SELECT * FROM agency WHERE agencyId = :agencyId")
    suspend fun getAgencyWithClients(agencyId: UUID): List<AgencyWithClients>

    @Transaction
    @Query("SELECT * FROM flat WHERE flatID = :flatID")
    fun getClientsOfFlat(flatID: UUID): List<FlatWithClient>

    @Transaction
    @Query("SELECT * FROM client WHERE clientId = :clientId")
    fun getFlatsOfClient(clientId: UUID): List<ClientWithFlats>

    @Transaction
    @Query("SELECT * FROM flat JOIN clientsflatcrossref ON flat.flatId = clientsflatcrossref.flatId JOIN client ON client.clientId = clientsflatcrossref.clientId JOIN agency ON client.agencyId =agency.agencyId JOIN director ON agency.agencyId = director.agencyId  WHERE director.directorId = :directorId ")
    fun getFlatsOfDirectorsAgency(directorId: UUID): List<Flat>

    @Transaction
    @Query("SELECT * FROM client JOIN agency ON client.agencyId = agency.agencyId JOIN director ON agency.agencyId = director.agencyId WHERE director.directorId = :directorId ")
    fun getClientsOfDirectorsAgency(directorId: UUID): List<Client>

}