package ch.leytto.cynoclient.ui.disease

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.db.entities.Disease
import ch.leytto.cynoclient.viewmodels.DiseaseViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText


class DiseaseNewFragment : Fragment() {
    private val diseaseViewModel: DiseaseViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).diseaseRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun save() {
        val noun: TextInputEditText = this.requireView().findViewById(R.id.noun_input);
        val description: TextInputEditText = this.requireView().findViewById(R.id.description_input);
        val symptoms: TextInputEditText = this.requireView().findViewById(R.id.symptoms_input);
        val preventive: TextInputEditText = this.requireView().findViewById(R.id.preventive_input);
        val curative: TextInputEditText = this.requireView().findViewById(R.id.curative_input);
        val vaccinable: RadioButton = this.requireView().findViewById(R.id.radio_vaccinable);
        val zoonosis: RadioButton = this.requireView().findViewById(R.id.radio_zoonosis);



        // TODO: FIND idLocality from npa

        val disease: Disease = Disease(
                noun = noun.text.toString(),
                description = description.text.toString(),
                symptoms = symptoms.text.toString(),
                preventive = preventive.text.toString(),
                curative = curative.text.toString(),
                vaccinable = vaccinable.isChecked,
                zoonosis = zoonosis.isChecked,


                id = 0
        )
        diseaseViewModel.insert(disease).observe(viewLifecycleOwner) { id: Long ->
            val bundle = bundleOf("ARG_DISEASE_ID" to id.toString())
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_diseaseNew_to_diseaseDetails, bundle);
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_disease_new, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener {
            run { this.save() }
        }

        return root;
    }

    override fun onStart() {
        super.onStart()
    }
}