package ch.leytto.cynoclient.ui.client

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.Locality
import ch.leytto.cynoclient.viewmodels.ClientViewModel
import ch.leytto.cynoclient.viewmodels.LocalityViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*


class ClientNewFragment : Fragment() {
    private val clientViewModel: ClientViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).clientRepository)
    }

    private val localityViewModel: LocalityViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).localityRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun save() {
        val firstname: TextInputEditText = this.requireView().findViewById(R.id.first_name_input);
        val lastname: TextInputEditText = this.requireView().findViewById(R.id.last_name_input);
        val fem: RadioButton = this.requireView().findViewById(R.id.radio_gender_female);
        val email: TextInputEditText = this.requireView().findViewById(R.id.email_input);
        val phone: TextInputEditText = this.requireView().findViewById(R.id.phone_input);
        val street: TextInputEditText = this.requireView().findViewById(R.id.street_input);
        val npa: TextInputEditText = this.requireView().findViewById((R.id.locality_input));

        lifecycleScope.launch(Dispatchers.IO) {
            var locId: Integer? = null;
            if (!npa.text.isNullOrEmpty()) {
                val loc = localityViewModel.getLocalityByZip(npa.text.toString())
                if(loc != null)
                    locId = Integer(loc.id)
            }

            withContext(Dispatchers.Main) {
                val client = Client(
                    firstname = firstname.text.toString(),
                    lastname = lastname.text.toString(),
                    female = fem.isChecked,

                    email = email.text.toString(),
                    phone = phone.text.toString(),
                    street = street.text.toString(),
                    idLocality = locId,

                    id = 0
                )
                clientViewModel.insert(client).observe(viewLifecycleOwner) { id: Long ->
                    val bundle = bundleOf("ARG_CLIENT_ID" to id.toString())
                    NavHostFragment.findNavController(this@ClientNewFragment)
                        .navigate(R.id.action_clientNew_to_clientDetails, bundle);
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_client_new, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener {
             kotlin.run { this.save() }
        }

        return root;
    }

    override fun onStart() {
        super.onStart()
    }
}