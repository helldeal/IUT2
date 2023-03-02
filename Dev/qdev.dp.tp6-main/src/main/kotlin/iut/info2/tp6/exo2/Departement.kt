package iut.info2.tp6.exo2

class Departement:Entite(),Ajoutable {
    var list= mutableListOf<Entite>()
    override fun salaire(): Double {
        var somme=0.0
        for (i in list)
            somme+=i.salaire()
        return somme
    }

    override fun ajouter(entite: Entite) {
        list.add(entite)
    }

    override fun supprimer(entite: Entite) {
        list.remove(entite)
    }
}