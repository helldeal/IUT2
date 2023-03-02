package iut.but2.tp1

/** NE PAS MODIFIER **/

interface File<E>  {
    /**
     * insere un élément à la fin de la file
     * @param element : l'élément à insérer
     */
    fun insererEnQueue(element : E)

    /**
     * supprime l'élément en tête de la file
     * @throws NoSuchElementException s'il n'y a pas d'élément à supprimer
     */
    fun supprimerEnTete()

    /**
     * liste les éléments depuis le début dans l'ordre d'insertion
     * @return une liste des éléments
     */
    fun listerDepuisDebut() : MutableList<E>

    /**
     * donne la taille de la file
     */
    fun taille() : Int

    /**
     * renvoie l'élément à la position indiquée dans la file
     * @return un élément de type E
     * @throws IndexOutOfBoundsException si la position ne correspond à aucun élément
     */
    fun consulter(position : Int) : E

    /**
     * liste les éléments depuis la fin de la file dans l'ordre inverse de l'insertion
     * @return une liste des éléments
     */
    fun listerDepuisFin() : MutableList<E>
}