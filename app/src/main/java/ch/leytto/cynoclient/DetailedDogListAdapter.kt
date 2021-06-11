package ch.leytto.cynoclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.db.entities.relations.DogWithBreedAndDiseases
import java.time.LocalDate
import java.time.Period
import java.util.*

class DetailedDogListAdapter : ListAdapter<DogWithBreedAndDiseases, DetailedDogListAdapter.DogWithBreedAndDiseasesViewHolder>(DogWithBreedAndDiseasesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogWithBreedAndDiseasesViewHolder {
        return DogWithBreedAndDiseasesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DogWithBreedAndDiseasesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class DogWithBreedAndDiseasesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameItemView: TextView = itemView.findViewById(R.id.name)
        private val breedItemView: TextView = itemView.findViewById(R.id.breed)
        private val ageItemView: TextView = itemView.findViewById(R.id.age)

        fun bind(rel: DogWithBreedAndDiseases) {
            val parts = rel.dog.birthdate?.split("-");
            val age = Period.between(
                LocalDate.of(
                    parts[0].toInt(), parts[1].toInt(), parts[2].toInt()
                ),
                LocalDate.now()
            );

            nameItemView.text = rel.dog.noun
            breedItemView.text = rel.breed.noun
            ageItemView.text = age.years.toString() + " ans"
        }

        companion object {
            fun create(parent: ViewGroup): DogWithBreedAndDiseasesViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.detailed_dog_adapter, parent, false)
                return DogWithBreedAndDiseasesViewHolder(view)
            }
        }
    }

    class DogWithBreedAndDiseasesComparator : DiffUtil.ItemCallback<DogWithBreedAndDiseases>() {
        override fun areItemsTheSame(oldItem: DogWithBreedAndDiseases, newItem: DogWithBreedAndDiseases): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DogWithBreedAndDiseases, newItem: DogWithBreedAndDiseases): Boolean {
            return oldItem.dog.id == newItem.dog.id
        }
    }
}