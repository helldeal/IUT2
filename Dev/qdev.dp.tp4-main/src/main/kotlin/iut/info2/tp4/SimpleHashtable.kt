package iut.info2.tp4

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
        if (table[indice(element)]!=element){
            if (table[indice(element)]!=null) throw CollisionException("")
            table[indice(element)]=element
            return true


        }
        return false
    }

    override fun contains(element: E): Boolean {
        return element in table
    }

    override fun remove(element: E): Boolean {
        if (table.indexOf(element)!=-1) {
            table[table.indexOf(element)] = null
            return true
        }
        return false
    }


}