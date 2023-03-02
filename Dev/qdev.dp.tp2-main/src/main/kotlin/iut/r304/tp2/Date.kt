package iut.r304.tp2

/**
 * Classe très simple représentant une date
 *
 * @constructor Constructeur définissant une date
 * @property jour entier représentant le jour
 * @property mois entier représentant le mois
 * @property annee entier représentant l'annee
 */
class Date (j : Int, m : Int, a : Int) {
    var jour : Int
        private set
    var mois : Int
        private set
    var annee : Int
        private set

    init {
        jour = j
        mois = m
        annee = a
    }


    /**
     * donne une chaine de caractères correspondant à la date
     *
     * @return une chaine JJ-MM-AAAA
     */
    override fun toString(): String {
        return "$jour-$mois-$annee"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Date) return false

        if (jour != other.jour) return false
        if (mois != other.mois) return false
        if (annee != other.annee) return false

        return true
    }

    override fun hashCode(): Int {
        var result = jour
        result = 31 * result + mois
        result = 31 * result + annee
        return result
    }



    /**
     * methode qui permet de vérifier si une date est valide, cad si le jour,
     * mois et année sont cohérents : uniquement 28 jours en février, sauf les
     * années bissextiles, uniquement 30 jours en juin, etc.
     *
     * @return true si la date est valide
     */
    fun dateValide(): Boolean {
        if (annee%400==0&&annee%5==0&&annee%4==0&&mois==2&&jour>29)throw DateException("")
        if (mois==1&&jour<1&&jour>31)throw DateException("")
        if (mois==2&&jour<1&&jour>28)throw DateException("")
        if (mois==3&&jour<1&&jour>31)throw DateException("")
        if (mois==4&&jour<1&&jour>30)throw DateException("")
        if (mois==5&&jour<1&&jour>31)throw DateException("")
        if (mois==6&&jour<1&&jour>30)throw DateException("")
        if (mois==7&&jour<1&&jour>31)throw DateException("")
        if (mois==8&&jour<1&&jour>31)throw DateException("")
        if (mois==9&&jour<1&&jour>30)throw DateException("")
        if (mois==10&&jour<1&&jour>31)throw DateException("")
        if (mois==11&&jour<1&&jour>30)throw DateException("")
        if (mois==12&&jour<1&&jour>31)throw DateException("")
        return true
    }

    companion object {

        /**
         * fabrique une date valide à partir de la chaine de caractère passé en
         * paramètre
         *
         * @param str chaine representant une date dans le format JJ-MM-AAAA
         * @return la date correspondant à la chaine de caractère
         * @throws DateException si la chaine est malformée
         */
        fun fabrique(str: String): Date {
            var j = str.substringBefore("-").toInt()
            var m =str.substringAfter("-").substringBefore("-").toInt()
            var a =str.substringAfterLast("-").toInt()
            return Date(j,m,a)
        }
    }
}