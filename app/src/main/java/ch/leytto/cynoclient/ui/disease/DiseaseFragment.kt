package ch.leytto.cynoclient.ui.disease

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.DiseaseListAdapter
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodels.DiseaseViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

class DiseaseFragment : Fragment() {

    private val diseaseViewModel: DiseaseViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).diseaseRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_diseases_list, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.disease_recyclerview)
        val adapter = DiseaseListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        diseaseViewModel.AllDiseases.observe(viewLifecycleOwner) { diseases ->
            diseases.let { adapter.submitList(it) }
        }

        return root
    }
}