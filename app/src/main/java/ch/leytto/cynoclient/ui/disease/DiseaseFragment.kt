package ch.leytto.cynoclient.ui.disease

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodels.DiseaseViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

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

                val disease = it;
                row.findViewById<TextView>(R.id.noun).text = disease.noun

                row.isClickable = true
                row.setOnClickListener {
                    // Go to client client.id page
                    //view.findNavController().navigate(R.id.)
                    val bundle = bundleOf("ARG_DISEASE_ID" to disease.id.toString())
                    NavHostFragment.findNavController(this).navigate(R.id.action_nav_diseases_to_DiseaseDetails, bundle);
                }

                diseasesTable.addView(row);
            }
        }

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            run {
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_nav_diseases_to_diseaseNew);
            }
        }

        return root
    }
}