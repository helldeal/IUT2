package tp8.exo2

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