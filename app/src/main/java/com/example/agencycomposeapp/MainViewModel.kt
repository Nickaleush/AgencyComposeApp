package com.example.agencycomposeapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.agencycomposeapp.room.AgencyDatabase
import com.example.agencycomposeapp.room.entities.Agency
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.room.entities.Director
import com.example.agencycomposeapp.room.entities.Flat
import com.example.agencycomposeapp.room.relations.ClientsFlatCrossRef
import com.example.agencycomposeapp.room.repository.RoomRepository
import com.example.agencycomposeapp.utils.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun getAllFlats() = REPOSITORY.getAllFlats

    fun getAllAgencies() = REPOSITORY.getAllAgencies

    fun initDatabase(onSuccess: () -> Unit) {
        val dao = AgencyDatabase.getInstance(context).agencyDao
        REPOSITORY = RoomRepository(dao)
        onSuccess()
    }

    fun getAgencyById(agencyId: UUID) = REPOSITORY.getAgencyById(agencyId)

    fun getClientByPhone(phone: String) = REPOSITORY.getClientByPhone(phone)

    fun getDirectorByPhone(directorPhone: String) = REPOSITORY.getDirectorByPhone(directorPhone)

    fun getFlatsOfDirectorsAgency(directorId: UUID) =
        REPOSITORY.getFlatsOfDirectorsAgency(directorId)

    fun getClientsOfDirectorsAgency(directorId: UUID) =
        REPOSITORY.getClientsOfDirectorsAgency(directorId)

    fun getFlatsOfClient(clientId: UUID) = REPOSITORY.getFlatsOfClient(clientId)

    fun getClientsOfFlat(flatId: UUID) = REPOSITORY.getClientsOfFlat(flatId)

    fun addFlat(flat: Flat, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.createFlat(flat) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun addClient(client: Client, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.createClient(client) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateFlat(flat: Flat, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.updateFlat(flat = flat) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateFlatCrossRef(flatId: UUID, clientId: UUID, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.updateFlatCrossRef(flatId = flatId, clientId = clientId) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteFlatById(flatId: UUID, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.deleteFlatById(flatId = flatId) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteClientById(clientId: UUID, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.deleteClientById(clientId = clientId) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun addDirectorAndAgency(director: Director, agency: Agency) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insertDirectorAndAgency(agency = agency, director = director) {
                viewModelScope.launch(Dispatchers.Main) {

                }
            }
        }
    }

    fun addFlatAndCrossRef(flat: Flat, crossRef: ClientsFlatCrossRef, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insertFlatAndCrossRef(flat = flat, crossRef = crossRef) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}
