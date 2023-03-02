package tp8.exo1.templatemethod

class CelibGosse(foyer: Foyer) : CalculImpot(foyer) {
    override fun taux(): Double {
        return 0.88
    }

    override fun divrevenu(): Int {
        return 3
    }

    override fun modificateur(): Int {
        return 1000
    }

    override fun supplémenthab(): Int {
        return -75*foyer.nbEnfants
    }

    override fun tauxtaxehab(): Double {
        return 1.0
    }
}