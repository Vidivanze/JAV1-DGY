package ch.leytto.cynoclient.db.dao

import androidx.room.*
import ch.leytto.cynoclient.db.entities.Disease
import kotlinx.coroutines.flow.Flow

@Dao
interface DiseaseDao {

    @Query("SELECT * FROM diseases")
    fun getDiseases(): Flow<List<Disease>>

    @Query("SELECT * FROM diseases WHERE id = :id")
    suspend fun getDiseases(id: Int): Disease

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(disease: Disease)

    @Update
    suspend fun updateDiseases(vararg diseases: Disease)

    @Delete
    suspend fun deleteDiseases(vararg diseases: Disease)
}