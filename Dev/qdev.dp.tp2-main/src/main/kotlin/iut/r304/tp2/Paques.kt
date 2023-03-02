package iut.r304.tp2

/**
 * Cette interface définie plusieurs fonctionnalités autour du calcul de la
 * date de Pâques pour une année donnée
 */
interface Paques {

    /**
     * calcule la date de Pâques en fonction de l'année
     *
     * @param annee année pour laquelle on veut calculer la date de Pâques
     * @return la date de Pâques
     * @throws PaquesException si le calcul ne peut avoir lieu
     */
    fun calculeDatePaques(annee: Int): Date


    /**
     * donne la liste de toutes les dates de Pâques calculées à l'aide de
     * l'objet courant
     *
     * @return la liste de toutes les dates de Pâques, dans l'ordre où elles
     *     ont été calculées
     */
    fun historiqueResultats(): List<Date>


    /**
     * donne la liste de toutes les dates de Pâques calculées à l'aide de
     * l'objet courant ; la liste résultat est triée par mois, puis par jour,
     * puis par année
     *
     * @return la liste de toutes les dates de Pâques, correctement triée
     */
    fun historiqueResultatsTries(): List<Date>

    /**
     * donne la liste des dates de Pâques calculées à l'aide de
     * l'objet courant ; la liste résultat ne contient que les dates
     * du mois d'avril, triées par année
     *
     * @return la liste des dates de Pâques, correctement triée
     */
    fun historiqueResultatsTriesAutrement(): List<Date>

}