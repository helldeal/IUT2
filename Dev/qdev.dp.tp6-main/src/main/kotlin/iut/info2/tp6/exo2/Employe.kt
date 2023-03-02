package iut.info2.tp6.exo2

class Employe(
    private val nom: String,
    private val salaire: Double = 0.0
):Entite() {
    override fun salaire(): Double {
        return salaire
    }
}