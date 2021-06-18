package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.Locality
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocalityAndDogWithBreedAndDiseases
import ch.leytto.cynoclient.model.ClientRepository
import ch.leytto.cynoclient.model.LocalityRepository
import kotlinx.coroutines.launch

class LocalityViewModel(private val repository: LocalityRepository) : ViewModel() {

    val AllLocalities: LiveData<List<Locality>> = repository.allLocalities.asLiveData()

    fun getLocality(id: String) : LiveData<Locality> = repository.find(id)

    fun getLocalityByZip(zip: String) : Locality = repository.findByZip(zip)
}