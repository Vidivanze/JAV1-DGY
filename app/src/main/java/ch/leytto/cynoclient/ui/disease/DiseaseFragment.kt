package ch.leytto.cynoclient.ui.disease

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodels.DiseaseViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

class DiseaseFragment : Fragment() {

    private val diseaseViewModel: DiseaseViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).diseaseRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_diseases_list, container, false)

        val diseasesTable = root.findViewById<TableLayout>(R.id.diseases_table)

        diseaseViewModel.AllDiseases.observe(viewLifecycleOwner) { diseases ->
            diseases.forEach {
                val row = LayoutInflater.from(context).inflate(R.layout.diseases_list_row, null)

                row.findViewById<TextView>(R.id.noun).text = it.noun

                diseasesTable.addView(row);
            }
        }

        return root
    }
}