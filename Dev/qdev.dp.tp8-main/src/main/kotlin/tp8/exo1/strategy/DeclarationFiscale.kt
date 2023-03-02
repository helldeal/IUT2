package tp8.exo1.strategy

import tp8.exo1.Situation

fun declarationFiscale(
    revenuAnnuel: Double,
    loyerMensuel: Double,
    situation: Situation,
    nbEnfants: Int
): Triple<Double, Int, Int> {
    val foyer = Foyer(revenuAnnuel, loyerMensuel, situation, nbEnfants)

    lateinit var calcul: CalculImpot
    // TODO : fixer la bonne strategie pour le calcul de l'impot

    return Triple(
        calcul.revenusImposables(),
        calcul.impotsSurLeRevenu(),
        calcul.taxeHabitation())
}



