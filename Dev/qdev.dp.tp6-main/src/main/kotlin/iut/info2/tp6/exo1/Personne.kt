package iut.info2.tp6.exo1

/**
 * Classe representant une Personne
 *
 * @property nom le nom de la personne
 * @property prenom le prénom de la personne
 */
data class Personne internal constructor(
    override val nom: String,
    private val prenom: String
) : PersonneA(nom) {

    override fun toString(): String {
        return "Personne " + prenom + " " + nom
    }
}



