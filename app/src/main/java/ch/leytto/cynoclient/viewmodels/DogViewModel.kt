package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.model.ClientRepository
import ch.leytto.cynoclient.model.DogRepository
import kotlinx.coroutines.launch

class DogViewModel(private val dogRepository: DogRepository) : ViewModel() {

    val AllDogs: LiveData<List<Dog>> = dogRepository.allDogs.asLiveData()

    fun insert(dog: Dog) = viewModelScope.launch {
        dogRepository.insert(dog)
    }

    /*fun getClientById(id: Int){
        val client = clientRepository.getClientById(id);
        println(client);
        return client;
    }*/

}