package iut.info2.tp6.exo1

/**
 * Classe représentant une Entreprise
 *
 * @property nom la raison sociale de l'entreprise
 * @property categorie la catégorie d'entreprise
 */

data class Entreprise internal constructor(
    override val nom: String,
    private val categorie: CategorieEntreprise
) : PersonneA(nom) {

    override fun toString(): String {
        return "Entreprise " + categorie.toString() + " " + nom
    }
}