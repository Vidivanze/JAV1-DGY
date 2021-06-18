package ch.leytto.cynoclient.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.Locality
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalityDao {
    @Query("SELECT * FROM localities")
    fun getLocalities(): Flow<List<Locality>>

    @Query("SELECT * FROM localities WHERE id = :id")
    fun getLocality(id: String): LiveData<Locality>

    @Query("SELECT * FROM localities WHERE zip = :zip")
    fun getLocalityByZip(zip: String): Locality
}