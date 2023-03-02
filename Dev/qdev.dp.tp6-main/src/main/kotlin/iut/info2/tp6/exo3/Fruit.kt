package iut.info2.tp6.exo3

@kotlinx.serialization.Serializable
data class Fruit(
    val nom: String,
    val prix: Double,
    var quantite: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fruit) return false

        if (nom != other.nom) return false
        if (Math.abs(prix - other.prix) > 0.1) return false
        if (quantite != other.quantite) return false

        return true
    }

}