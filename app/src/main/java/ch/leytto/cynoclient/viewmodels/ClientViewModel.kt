package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocalityAndDogWithBreedAndDiseases
import ch.leytto.cynoclient.model.ClientRepository
import kotlinx.coroutines.launch

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {

    val AllClients: LiveData<List<Client>> = repository.allClients.asLiveData()

    fun getClient(id: String) : LiveData<Client> = repository.find(id)

    fun getClientWithLocalityAndDogWithBreedAndDiseases(id: String) : LiveData<ClientWithLocalityAndDogWithBreedAndDiseases> = repository.getClientWithLocalityAndDogWithBreedAndDiseases(id)
    
    fun insert(client: Client) : LiveData<Long> {
        val res = MutableLiveData<Long>()
        viewModelScope.launch {
            val r = repository.insert(client)
            res.postValue(r)
        }
        return res
    }

    fun getClientById(id: Int){
        val clientById: Unit = repository.getClientById(id);
        return clientById;

    }
}