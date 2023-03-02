package tp8.exo2

/**
 * Classe abstraite représentant une table de hachage abstraite
 *
 * @param <E> le type des éléments manipulés par la table de hachage
 * @param size la taille de la table de hachage
 * @constructor construit une table de hachage
 */

abstract class HashtableA<E : Hashable>(private val size: Int) : IHashtable<E>, Iterable<E> {

    protected var nbElements : Int = 0

    /**
     * calcule la clef de hachage en appliquant à la fonction de hachage de
     * l'objet courant un modulo pour que l'indice calculé rentre dans la table
     *
     * @param element
     * @return la valeur de hachage pour l'élément ramené dans les dimensions
     *     de la table
     */
    protected fun indice(element: E): Int {
        return element.hash() % size
    }

}