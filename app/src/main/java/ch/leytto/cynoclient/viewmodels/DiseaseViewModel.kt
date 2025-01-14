package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Disease
import ch.leytto.cynoclient.model.DiseaseRepository
import kotlinx.coroutines.launch

class DiseaseViewModel(private val repository: DiseaseRepository) : ViewModel() {

    val AllDiseases: LiveData<List<Disease>> = repository.allDiseases.asLiveData()

    fun getDisease(id: String) : LiveData<Disease> = repository.find(id)

    fun insert(disease: Disease) : LiveData<Long> {
        val res = MutableLiveData<Long>()
        viewModelScope.launch {
            val r = repository.insert(disease)
            res.postValue(r)
        }
        return res
    }

}