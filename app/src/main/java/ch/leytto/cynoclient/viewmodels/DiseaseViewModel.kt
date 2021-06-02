package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Disease
import ch.leytto.cynoclient.model.DiseaseRepository
import kotlinx.coroutines.launch

class DiseaseViewModel(private val repository: DiseaseRepository) : ViewModel() {

    val AllDiseases: LiveData<List<Disease>> = repository.allDiseases.asLiveData()

    fun insert(disease: Disease) = viewModelScope.launch {
        repository.insert(disease)
    }

}