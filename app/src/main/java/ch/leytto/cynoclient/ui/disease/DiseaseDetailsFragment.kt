package ch.leytto.cynoclient.ui.disease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodels.DiseaseViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

private const val ARG_DISEASE_ID = "ARG_DISEASE_ID"

class DiseaseDetailsFragment : Fragment() {
    private var paramDiseaseId: String? = null

    private val diseaseViewModel: DiseaseViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).diseaseRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramDiseaseId = it.getString(ARG_DISEASE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_disease_details, container, false)

        diseaseViewModel.getDisease(paramDiseaseId!!).observe(viewLifecycleOwner){

            root.findViewById<TextView>(R.id.noun).text = it.noun

            root.findViewById<TextView>(R.id.description_header).text
            root.findViewById<TextView>(R.id.description).text = it.description

            root.findViewById<TextView>(R.id.symptoms_header).text
            root.findViewById<TextView>(R.id.symptoms).text = it.symptoms

            root.findViewById<TextView>(R.id.preventive_header).text
            root.findViewById<TextView>(R.id.preventive).text = it.preventive

            root.findViewById<TextView>(R.id.curative_header).text
            root.findViewById<TextView>(R.id.curative).text = it.curative


        }

        return root;
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiseaseDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DISEASE_ID, paramDiseaseId)
                }
            }
    }
}