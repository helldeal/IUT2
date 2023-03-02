package tp8.exo2

/**
 * Une premiere implémentation d'une table de hachage,
 * sans gestion des collisions
 *
 * @param <E> le type des éléments manipulés
 * @constructor construit une table de hachage
 * @param size la taille de la table de hachage
 */
class SimpleHashtable<E : Hashable>(size : Int) : HashtableA<E>(size) {

    private var table : Array<Any?>

    init {
        table = arrayOfNulls<Any?>(size)
    }

    @Suppress("UNCHECKED_CAST")
    private fun get(index : Int) : E {
        requireNotNull(table[index])
        return (table[index] as E)
    }


    override fun add(element: E): Boolean {
        val index = indice(element)
        if (table[index] == null) {
            table[index] = element
            nbElements++
            return true
        }
        else if (table[index] != element) { // collision avec un autre element au meme index
            throw CollisionException("et de une")
        }
        return false // élément déjà présent
    }

    override fun contains(element: E): Boolean {
        val index = indice(element)
        if (table[index] != null)
            return (get(index) == element)
        return false
    }

    override fun remove(element: E): Boolean {
        val index = indice(element)
        if (table[index] != null) {
            if (get(index) == element) {
                table[index] = null
                nbElements--
                return true
            }
        }
        return false
    }

    override fun iterator(): Iterator<E> {
        return object : Iterator<E> {

            //TODO

            override fun hasNext(): Boolean {
                TODO()
            }

            override fun next(): E {
                TODO()
            }

        }
    }
}