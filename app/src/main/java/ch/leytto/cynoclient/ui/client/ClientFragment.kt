package ch.leytto.cynoclient.ui.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.viewmodels.ClientViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

class ClientFragment : Fragment() {

    private val clientViewModel: ClientViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).clientRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_clients_list, container, false)

        val clientsTable = root.findViewById<TableLayout>(R.id.clients_table)

        clientsTable.removeAllViews()

        clientViewModel.AllClients.observe(viewLifecycleOwner) { clients: List<Client> ->
            clients.forEach {
                val row = LayoutInflater.from(context).inflate(R.layout.clients_list_row, null)
                val client = it;
                row.findViewById<TextView>(R.id.first_name).text = client.firstname
                row.findViewById<TextView>(R.id.last_name).text = client.lastname
                row.findViewById<TextView>(R.id.contact_info).text = client.email

                row.isClickable = true
                row.setOnClickListener {
                    val bundle = bundleOf("ARG_CLIENT_ID" to client.id.toString())
                    NavHostFragment.findNavController(this).navigate(R.id.action_nav_clients_to_clientDetails, bundle);
                }

                clientsTable.addView(row);
            }
        }

        return root
    }
}