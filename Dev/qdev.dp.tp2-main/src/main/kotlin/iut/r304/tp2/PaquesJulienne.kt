package iut.r304.tp2

class PaquesJulienne : Paques {
    private var hist= mutableListOf<Date>()


    override fun calculeDatePaques(annee: Int): Date {
        if (annee<326)throw PaquesException("année<326 J")
        var A=annee%19
        var B=annee%7
        var C=annee%4
        var D=(19*A+15)%30
        var E=(2*C+4*B-D+34)%7
        var F=(D+E+114)/31
        var G=(D+E+114)%31
        hist.add(Date(G+1,F,annee))
        return Date(G+1,F,annee)
    }

    override fun historiqueResultats(): List<Date> {
        return hist.toList()
    }

    override fun historiqueResultatsTries(): List<Date> {
        var histsort=historiqueResultats().toMutableList()
        histsort.sortBy { it.annee }
        histsort.sortBy { it.jour }
        histsort.sortBy { it.mois }
        println(histsort)
        return histsort.toList()
    }

    override fun historiqueResultatsTriesAutrement(): List<Date> {
        var histsort=historiqueResultats().toMutableList()
        histsort.sortBy { it.jour }
        histsort.sortBy { it.mois }
        histsort.sortBy { it.annee }
        println(histsort)
        return histsort.toList()
    }
}