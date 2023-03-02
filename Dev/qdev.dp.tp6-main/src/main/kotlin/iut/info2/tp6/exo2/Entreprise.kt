package iut.info2.tp6.exo2

class Entreprise(
    private val principal: Departement
    = Departement()

):Ajoutable by Departement(),Salariable by Departement(){
    override fun ajouter(entite: Entite) {
        return principal.ajouter(entite)
    }

    override fun supprimer(entite: Entite) {
        return principal.supprimer(entite)
    }

    override fun salaire(): Double {
        return principal.salaire()
    }

}

