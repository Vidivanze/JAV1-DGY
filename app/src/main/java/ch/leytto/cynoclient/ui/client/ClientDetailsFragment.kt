package ch.leytto.cynoclient.ui.client

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
import ch.leytto.cynoclient.DetailedDogListAdapter
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocalityAndDogWithBreedAndDiseases
import ch.leytto.cynoclient.viewmodels.ClientViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

private const val ARG_CLIENT_ID = "ARG_CLIENT_ID"

class ClientDetailsFragment : Fragment() {
    private var paramClientId: String? = null

    private val clientViewModel: ClientViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).clientRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramClientId = it.getString(ARG_CLIENT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_client_details, container, false)

        clientViewModel.getClientWithLocalityAndDogWithBreedAndDiseases(paramClientId!!).observe(viewLifecycleOwner) { rel: ClientWithLocalityAndDogWithBreedAndDiseases ->
            val c = rel.client
            val dogs = rel.dogs

            root.findViewById<TextView>(R.id.full_name).text =
                getString(R.string.full_name_format, c.firstname, c.lastname)

            if (c.email == null) {
                root.findViewById<TextView>(R.id.email).visibility = GONE;
            }
            else {
                root.findViewById<TextView>(R.id.email).text = c.email;
            }
            // Phone is NOTNULL
            root.findViewById<TextView>(R.id.phone).text = c.phone;

            if (c.street.isNullOrEmpty()) {
                root.findViewById<TextView>(R.id.address).visibility = GONE;
            }
            else {
                root.findViewById<TextView>(R.id.address).text =
                    getString(R.string.address_format, c.street,  rel.locality?.zip, rel.locality?.toponym)
            }

            if(dogs.count() == 0) {
                root.findViewById<TextView>(R.id.notice_no_dogs).visibility = VISIBLE
            }
            else {
                val recyclerView = root.findViewById<RecyclerView>(R.id.dog_recyclerview)
                val adapter = DetailedDogListAdapter()
                recyclerView.adapter = adapter

                adapter.submitList(dogs)
            }
        }

        return root;
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClientDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CLIENT_ID, paramClientId)
                }
            }
    }
}