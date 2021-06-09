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

    fun insert(client: Client) = viewModelScope.launch {
        repository.insert(client)
    }

}