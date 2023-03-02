package tp8.exo1.templatemethod

class Celib(foyer: Foyer) : CalculImpot(foyer) {
    override fun taux(): Double {
        return 0.9
    }

    override fun divrevenu(): Int {
        return 3
    }

    override fun modificateur(): Int {
        return 0
    }

    override fun supplémenthab(): Int {
        return 1000
    }

    override fun tauxtaxehab(): Double {
        return 2.0
    }
}