package tp8.exo1.strategy

import tp8.exo1.Situation

class Foyer(
    var revenuAnnuel: Double,
    var loyerMensuel: Double,
    var situation: Situation = Situation.Celibataire,
    var nbEnfants: Int = 0
)