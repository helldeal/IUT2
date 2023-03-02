package iut.r304.tp2

class PaquesGregorienne : Paques {
    private var hist= mutableListOf<Date>()

    override fun calculeDatePaques(annee: Int): Date {
        if (annee<1583)throw PaquesException("année<1583 G")
        var n =annee%19
        var u =annee%100
        var c =annee/100
        var t=c%4
        var s=c/4
        var p =(c+8)/25
        var q=(c-p+1)/3
        var e =(19*n+c-s-q+15)%30
        var b =u/4
        var d =u%4
        var L=(2*t+2*b-e-d+32)%7
        var h=(n+11*e+22*L)/451
        var m=(e+L-7*h+114)/31
        var j=(e+L-7*h+114)%31
        hist.add(Date(j+1,m,annee))
        return Date(j+1,m,annee)
    }

    override fun historiqueResultats(): List<Date> {
        return hist.toList()
    }

    override fun historiqueResultatsTries(): List<Date> {
        var histsort=historiqueResultats().toMutableList()
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