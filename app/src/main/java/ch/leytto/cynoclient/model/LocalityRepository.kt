package ch.leytto.cynoclient.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ch.leytto.cynoclient.db.dao.DogDao
import ch.leytto.cynoclient.db.dao.LocalityDao
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.Locality
import kotlinx.coroutines.flow.Flow

class LocalityRepository(private val localityDao: LocalityDao) : AbstractRepository() {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allLocalities: Flow<List<Locality>> = localityDao.getLocalities()

    @WorkerThread
    fun find(id: String): LiveData<Locality> {
        return localityDao.getLocality(id);
    }

    @WorkerThread
    fun findByZip(zip: String): Locality {
        return localityDao.getLocalityByZip(zip);
    }
}