package ch.leytto.cynoclient.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ch.leytto.cynoclient.db.dao.DogDao
import ch.leytto.cynoclient.db.entities.Breed
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.relations.DogWithBreedAndClient
import kotlinx.coroutines.flow.Flow

class DogRepository(private val dogDao: DogDao) : AbstractRepository() {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    //val allDogs: Flow<List<Dog>> = dogDao.getDogs()

    val dogsWithBreed: Flow<List<DogWithBreedAndClient>> = dogDao.getDogs();

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(dog: Dog) {
        dogDao.insert(dog)
    }

}