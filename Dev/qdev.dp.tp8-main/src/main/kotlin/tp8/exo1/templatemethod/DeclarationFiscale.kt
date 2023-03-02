package tp8.exo1.templatemethod

import tp8.exo1.Situation

fun declarationFiscale(
    revenuAnnuel: Double,
    loyerMensuel: Double,
    situation: Situation,
    nbEnfants: Int
): Triple<Double, Int, Int> {

    var foyer =Foyer(revenuAnnuel,loyerMensuel,situation,nbEnfants)

    var calcul=CalculImpot.choose(foyer)
    return Triple(
        calcul.revenusImposables(),
        calcul.impotsSurLeRevenu(),
        calcul.taxeHabitation())
}

