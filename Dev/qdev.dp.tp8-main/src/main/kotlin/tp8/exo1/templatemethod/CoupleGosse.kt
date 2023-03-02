package tp8.exo1.templatemethod

class CoupleGosse(foyer: Foyer) : CalculImpot(foyer) {
    override fun taux(): Double {
        return 0.8
    }

    override fun divrevenu(): Int {
        return 4
    }

    override fun modificateur(): Int {
        return 750
    }

    override fun supplémenthab(): Int {
        return -50*foyer.nbEnfants
    }

    override fun tauxtaxehab(): Double {
        return 1.5
    }
}