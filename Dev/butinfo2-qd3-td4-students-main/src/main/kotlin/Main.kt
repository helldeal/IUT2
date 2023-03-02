fun main() {
    println("Hello IUT!")

    var ln = iut.but2.r402.notation.ListeNotes(intArrayOf(1,2,3,4,5))
    afficherListe(ln.listeDesNotes)
    println("Moyenne " + ln.moyenne())
    println("Combien de 20 : " + ln.nombreOccurrence(20))

}

fun afficherListe(liste : IntArray) {
    for (i in liste.indices) {
        print(liste[i])
        print(" ; ")
    }
    println("Il y a " + liste.size + " elements dans la liste.")
}