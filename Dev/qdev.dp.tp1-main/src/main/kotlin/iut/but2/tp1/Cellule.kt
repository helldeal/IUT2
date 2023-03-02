package iut.but2.tp1

/** NE PAS MODIFIER **/

class Cellule<E>(valeur : E) {

    private var valeur : E
    private var suivant : Cellule<E>?

    init {
        this.valeur = valeur
        this.suivant = null
    }

    fun valeur() = valeur

    fun suivant() = suivant

    fun modifieSuivant(nouveauSuivant : Cellule<E>?)  {
        suivant = nouveauSuivant
    }
}