package tp8.exo2

interface IHashtable<E : Hashable?> {

    /**
     * ajout d'un élément dans la table de hachage
     *
     * @param element élément à ajouter
     * @return true si l'élément est ajouté / false si l'élément est déjà présent
     * @throws CollisionException s'il y a une collision non traitée, cad qu'un autre élément est
     * déjà présent dans la table et qu'on ne peut rien faire
     */
    fun add(element: E): Boolean

    /**
     * recherche un élément dans la table de hachage
     *
     * @param element élément à rechercher
     * @return true si l'élément est présent, false sinon
     */
    operator fun contains(element: E): Boolean

    /**
     * remove l'élément dans la table de hachage
     *
     * @param element l'élément à remove
     * @return true si l'élément est supprimé / false si l'élément n'est pas présent
     */
    fun remove(element: E): Boolean

    /**
     * @return le nombre de collisions évitées
     */
    fun avoidedCollisions() : Int {
        return 0
    }
}
