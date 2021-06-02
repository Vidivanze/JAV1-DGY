package ch.leytto.cynoclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.Disease

class DiseaseListAdapter : ListAdapter<Disease, DiseaseListAdapter.DiseaseViewHolder>(DiseaseComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        return DiseaseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class DiseaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameItemView: TextView = itemView.findViewById(R.id.name)

        fun bind(disease: Disease?) {
            nameItemView.text = disease?.noun
        }

        companion object {
            fun create(parent: ViewGroup): DiseaseViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.disease_adapter, parent, false)
                return DiseaseViewHolder(view)
            }
        }
    }

    class DiseaseComparator : DiffUtil.ItemCallback<Disease>() {
        override fun areItemsTheSame(oldItem: Disease, newItem: Disease): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Disease, newItem: Disease): Boolean {
            return oldItem.id == newItem.id
        }
    }
}