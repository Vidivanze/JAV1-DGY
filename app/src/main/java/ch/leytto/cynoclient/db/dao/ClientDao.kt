package ch.leytto.cynoclient.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocality
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocalityAndDogWithBreedAndDiseases
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Transaction
    @Query("SELECT * FROM clients")
    suspend fun getClientsWithLocalityAndDogWithBreedAndDiseases(): List<ClientWithLocalityAndDogWithBreedAndDiseases>

    @Query("SELECT * FROM clients")
    fun getClients() : Flow<List<Client>>

    @Transaction
    @Query("SELECT * FROM clients WHERE id=:id ")
    fun getClientWithLocalityAndDogWithBreedAndDiseases(id: String)
            : LiveData<ClientWithLocalityAndDogWithBreedAndDiseases>

    @Query("SELECT * FROM clients WHERE id=:id ")
    fun getClient(id: String): LiveData<Client>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(client: Client) : Long

    @Update
    suspend fun updateDogs(vararg clients: Client)

    @Delete
    suspend fun deleteDogs(vararg clients: Client)
}