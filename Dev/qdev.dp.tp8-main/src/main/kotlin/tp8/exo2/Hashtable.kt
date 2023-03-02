package tp8.exo2

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
        val index = indice(element)
        if (element !in table[index]) {
            if (table[index].isNotEmpty())
                nbCollisions++
            table[index].add(element)
            nbElements++
            return true
        }
        return false // element déjà présent
    }

    override fun contains(element: E): Boolean {
        return (element in table[indice(element)])
    }

    override fun remove(element: E): Boolean {
        if (table[indice(element)].remove(element)) {
            nbElements--
            return true
        }
        return false
    }

    /**
     * @return le nombre de collisions évitées grâce au chainage
     */
    override fun avoidedCollisions() : Int {
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
          val nouvelleTable = Hashtable<E>(newsize)
          for (i in 0 until hashtable.table.size) {
              for (element in hashtable.table[i]) {
                  nouvelleTable.add(element)
              }
          }
          return nouvelleTable
        }
    }

    override fun iterator(): Iterator<E> {
        TODO("Not yet implemented")
    }
}