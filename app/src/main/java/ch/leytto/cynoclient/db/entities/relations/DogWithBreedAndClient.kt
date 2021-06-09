package ch.leytto.cynoclient.db.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ch.leytto.cynoclient.db.entities.*

data class DogWithBreedAndClient(
    @Embedded val dog: Dog,
    @Relation(
        parentColumn = "breed",
        entityColumn = "id"
    )
    val breed: Breed,
    @Relation(
        parentColumn = "crossbreed",
        entityColumn = "id"
    )
    val crossbreed: Breed?,

    @Relation(
        parentColumn = "id_client",
        entityColumn = "id"
    )
    val client: Client
) {
}