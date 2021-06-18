package ch.leytto.cynoclient.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ch.leytto.cynoclient.db.dao.DiseaseDao
import ch.leytto.cynoclient.db.entities.Disease
import kotlinx.coroutines.flow.Flow

class DiseaseRepository(private val diseaseDao: DiseaseDao) : AbstractRepository() {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allDiseases: Flow<List<Disease>> = diseaseDao.getDiseases()

    @WorkerThread
    fun find(id: String): LiveData<Disease> {
        return diseaseDao.getDisease(id);
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(disease: Disease) {
        diseaseDao.insert(disease)
    }
}