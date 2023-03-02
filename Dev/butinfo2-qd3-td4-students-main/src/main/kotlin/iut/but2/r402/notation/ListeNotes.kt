package iut.but2.r402.notation

/**
 * Cette classe stocke des notes et calcule des statistiques
 * @param listesDesNotes : les notes entrées en paramètre du constructeur
 * @property listesDesNotes : attributs de classe stockant les notes de l'instance de classe
 * @author mottu-jm
 */
class ListeNotes(var listeDesNotes: IntArray) {

    /**
     * Calcule la moyenne des notes
     * @return renvoie la moyenne des notes, renvoie 0 s'il n'y a pas de note
     */
    fun moyenne(): Float {
        var somme = 0
        var index = 0
        while (index < listeDesNotes.size) {
            somme += listeDesNotes[index]
            index++
        }
        return if(listeDesNotes.size != 0)
            somme.toFloat()/listeDesNotes.size
        else
            0.0F
    }

    /**
     * Compte les occurrences d'une note dans la liste des notes
     * @param elem
     * @return renvoie le nombre de notes trouvées
     * @throws IllegalArgumentException si la note cherchée est hors limite
     */
    fun nombreOccurrence(elem: Int): Int {
        var compteurOccurrence = 0
        if (elem > 20 || elem < 0) throw IllegalArgumentException()
        var i = 0
        while (i < listeDesNotes.size) {
            if (elem == listeDesNotes[i]) {
                compteurOccurrence++
            }
            i++
        }
        return compteurOccurrence
    }
}