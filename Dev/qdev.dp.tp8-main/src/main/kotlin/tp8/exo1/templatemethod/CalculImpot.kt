package tp8.exo1.templatemethod

import tp8.exo1.ImpotSurLeRevenu
import tp8.exo1.Situation
import tp8.exo1.Taux
import tp8.exo1.TaxeHabitation

abstract class CalculImpot(var foyer: Foyer) : ImpotSurLeRevenu, TaxeHabitation, Taux {

    companion object{
        public fun choose(foyer: Foyer):CalculImpot{
            if (foyer.situation==Situation.Couple){
                if (foyer.nbEnfants<1){
                    return Couple(foyer)
                } else{
                    return CoupleGosse(foyer)
                }
            } else{
                if (foyer.nbEnfants<1){
                    return Celib(foyer)
                } else{
                    return CelibGosse(foyer)
                }
            }
        }
    }

    override fun revenusImposables(): Double {
        return this.taux()*foyer.revenuAnnuel-this.modificateur()*foyer.nbEnfants
    }

    override fun impotsSurLeRevenu(): Int {
        return this.revenusImposables().toInt()*1/(this.divrevenu()+foyer.nbEnfants)
    }

    override fun taxeHabitation(): Int {
        return (foyer.loyerMensuel*this.tauxtaxehab()).toInt()+this.supplémenthab()
    }


}

