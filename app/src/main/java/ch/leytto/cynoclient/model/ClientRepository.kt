package ch.leytto.cynoclient.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ch.leytto.cynoclient.db.dao.ClientDao
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocalityAndDogWithBreedAndDiseases

import kotlinx.coroutines.flow.Flow

class ClientRepository(private val clientDao: ClientDao) : AbstractRepository() {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allClients: Flow<List<Client>> = clientDao.getClients()

    @WorkerThread
     fun find(id: String): LiveData<Client> {
        return clientDao.getClient(id);
    }

    @WorkerThread
    fun getClientWithLocalityAndDogWithBreedAndDiseases(id: String) : LiveData<ClientWithLocalityAndDogWithBreedAndDiseases> {
        return clientDao.getClientWithLocalityAndDogWithBreedAndDiseases(id);
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(client: Client) : Long {
        return clientDao.insert(client)
    }

    @WorkerThread
    fun getClientById(id: Int){
        clientDao.getClientById(id)
    }

}