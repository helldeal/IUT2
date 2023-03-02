package tp8.exo1.templatemethod

import tp8.exo1.Situation

open class Foyer(
    var revenuAnnuel: Double,
    var loyerMensuel: Double,
    var situation: Situation = Situation.Celibataire,
    var nbEnfants: Int = 0
) {

}