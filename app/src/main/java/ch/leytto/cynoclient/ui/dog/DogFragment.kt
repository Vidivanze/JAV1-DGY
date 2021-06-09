package ch.leytto.cynoclient.ui.dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodels.DogViewModel
import ch.leytto.cynoclient.viewmodels.ClientViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory
import android.widget.TableLayout
import android.widget.TextView
import ch.leytto.cynoclient.db.dao.ClientDao
import ch.leytto.cynoclient.db.entities.relations.DogWithBreedAndClient
import ch.leytto.cynoclient.model.ClientRepository
import kotlinx.coroutines.flow.Flow

class DogFragment : Fragment() {

    private val dogViewModel: DogViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).dogRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dogs_list, container, false)

        val dogsTable = root.findViewById<TableLayout>(R.id.dogs_table)

        dogViewModel.dogWithBreedAndClient.observe(viewLifecycleOwner) { dogs ->
            dogs.forEach {
                val row = LayoutInflater.from(context).inflate(R.layout.dogs_list_row, null)
                row.findViewById<TextView>(R.id.name).text = it.dog.noun
                row.findViewById<TextView>(R.id.breed).text = it.breed.noun
                row.findViewById<TextView>(R.id.owner).text = it.client.firstname + " " + it.client.lastname
                dogsTable.addView(row)
            }
        }

        return root
    }
}