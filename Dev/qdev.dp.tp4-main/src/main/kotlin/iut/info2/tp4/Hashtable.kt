package iut.info2.tp4

/**
 * une seconde implémentation d'une table de hachage,
 * avec gestion des collisions par chaînage externe
 *
 * @param <E> le type des éléments manipulés
 * @constructor construit une table de hachage
 * @param size la taille de la table de hachage
 */

class Hashtable<E : Hashable>(size : Int) : HashtableA<E>(size)  {

    private var table : Array< MutableList<E> >
    private var nbCollisions = 0

    init {
        table = emptyArray()
        for (i in 0 until size) {
            table += arrayOf(mutableListOf())
        }
    }

    override fun add(element: E): Boolean {
        if (contains(element))return false
        if (table[indice(element)].size>0) nbCollisions++
        table[indice(element)].add(element)
        return true




    }

    override fun contains(element: E): Boolean {
        return element in table[indice(element)]
    }

    override fun remove(element: E): Boolean {
        if(contains(element)) {
            table[indice(element)] .remove(element)
            return true
        }
        return false
    }

    override fun avoidedCollisions(): Int {
        return nbCollisions
    }


    companion object {
        /**
         * redimensionne une table de hachage
         * @param hashtable la table à redimensionner
         * @param newsize la nouvelle taille de la table de hash
         * @return une nouvelle table de hachage redimensionnée et
         * contenant tous les éléments contenus dans [hashtable]
         *
         */
        fun <E : Hashable> resize(hashtable : Hashtable<E>, newsize : Int) : IHashtable<E> {
            var ht=Hashtable<E>(newsize)
            for (i in hashtable.table)for (y in i)
                ht.add(y)
            return ht
        }
    }

}