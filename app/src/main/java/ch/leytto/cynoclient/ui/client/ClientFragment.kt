package ch.leytto.cynoclient.ui.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.ClientListAdapter
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.viewmodels.ClientViewModel
import ch.leytto.cynoclient.viewmodels.DogViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

class ClientFragment : Fragment() {

    private val clientViewModel: ClientViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).clientRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_clients_list, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.client_recyclerview)
        val adapter = ClientListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        clientViewModel.AllClients.observe(viewLifecycleOwner) { clients: List<Client> ->
            clients.let { adapter.submitList(it) }
        }

        return root
    }
}