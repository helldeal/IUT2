package iut.info2.tp4

/**
 * Interface imposant une fonction de hachage
 */
interface Hashable {

    /**
     * donne une valeur de hachage
     *
     * @return la valeur de hachage de l'objet appelant
     */
    fun hash(): Int
}