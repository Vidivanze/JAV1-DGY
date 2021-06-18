package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.relations.DogWithBreedAndClient
import ch.leytto.cynoclient.model.ClientRepository
import ch.leytto.cynoclient.model.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DogViewModel(private val dogRepository: DogRepository) : ViewModel() {

    //val AllDogs: LiveData<List<Dog>> = dogRepository.allDogs.asLiveData()

    val dogWithBreedAndClient: LiveData<List<DogWithBreedAndClient>> = dogRepository.dogsWithBreed.asLiveData();

    fun insert(dog: Dog) = viewModelScope.launch {
        dogRepository.insert(dog)
    }

}