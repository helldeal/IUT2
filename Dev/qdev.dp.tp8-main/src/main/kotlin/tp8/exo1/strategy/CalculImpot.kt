package tp8.exo1.strategy

import tp8.exo1.ImpotSurLeRevenu
import tp8.exo1.TaxeHabitation

abstract class CalculImpot(var foyer: Foyer) : ImpotSurLeRevenu, TaxeHabitation {

    override fun revenusImposables(): Double {
        TODO("Not yet implemented")
    }

    override fun impotsSurLeRevenu(): Int {
        TODO("Not yet implemented")
    }

    override fun taxeHabitation(): Int {
        TODO("Not yet implemented")
    }


}

