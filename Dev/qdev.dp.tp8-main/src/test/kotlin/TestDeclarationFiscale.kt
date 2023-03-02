import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import tp8.exo1.Situation
import kotlin.random.Random

class TestDeclarationFiscale {

    fun situationI(i :Int) : Situation {
        return if(i == 2) Situation.Couple else Situation.Celibataire
    }

    @ParameterizedTest(name = "Impot pour {2} personne(s) : revenu={0}, enfant(s)={3}")
    @CsvSource(
        "80000.0, 1000.0, 2, 2, 62500.0, 10417.0, 1400.0",
        "80000.0, 1000.0, 2, 0, 64000.0, 16000.0, 2000.0",
        "80000.0, 1000.0, 1, 0, 72000.0, 24000.0, 3000.0",
        "80000.0, 1000.0, 1, 3, 67400.0, 11233.0, 775.0",
        "40000.0, 1000.0, 2, 2, 30500.0, 5083.0, 1400.0",
        "40000.0, 1000.0, 2, 0, 32000.0, 8000.0, 2000.0",
        "40000.0, 1000.0, 1, 0, 36000.0, 12000.0, 3000.0",
        "40000.0, 1000.0, 1, 3, 32200.0, 5367.0, 775.0",
    )
    fun testCalcul_strategy(revenu : Double, loyer : Double, situation : Int, nbEnfants : Int,
                            imposable : Double, ir : Double, taxe : Double) {
        val result = tp8.exo1.strategy.
                declarationFiscale(revenu, loyer, situationI(situation), nbEnfants)
        assertAll(
            {assertEquals(imposable, result.first, 1.0)},
            {assertEquals(ir, result.second.toDouble(), 1.0)},
            {assertEquals(taxe, result.third.toDouble(), 1.0)},
        )
    }

    @ParameterizedTest(name = "Impot pour {2} personne(s) : revenu={0}, enfant(s)={3}")
    @CsvSource(
        "80000.0, 1000.0, 2, 2, 62500.0, 10417.0, 1400.0",
        "80000.0, 1000.0, 2, 0, 64000.0, 16000.0, 2000.0",
        "80000.0, 1000.0, 1, 0, 72000.0, 24000.0, 3000.0",
        "80000.0, 1000.0, 1, 3, 67400.0, 11233.0, 775.0",
        "40000.0, 1000.0, 2, 2, 30500.0, 5083.0, 1400.0",
        "40000.0, 1000.0, 2, 0, 32000.0, 8000.0, 2000.0",
        "40000.0, 1000.0, 1, 0, 36000.0, 12000.0, 3000.0",
        "40000.0, 1000.0, 1, 3, 32200.0, 5367.0, 775.0",
    )
    fun testCalcul_templatemethod(revenu : Double, loyer : Double, situation : Int, nbEnfants : Int,
                            imposable : Double, ir : Double, taxe : Double) {
        val result = tp8.exo1.templatemethod.
        declarationFiscale(revenu, loyer, situationI(situation), nbEnfants)
        assertAll(
            {assertEquals(imposable, result.first, 1.0)},
            {assertEquals(ir, result.second.toDouble(), 1.0)},
            {assertEquals(taxe, result.third.toDouble(), 1.0)},
        )
    }

    @Test
    fun testRandom() {
        val nbTests = 100
        var compteur = 0
        while (compteur < nbTests) {
            val revenu = Random.nextDouble(40_000.0, 2_000_000.0)
            val loyer = Random.nextDouble(500.0, 5_000.0)
            val situation = Random.nextInt(1, 3)
            val nbEnfants = Random.nextInt(0, 6)

            println("Impot pour ${situation} personne(s) : revenu=${revenu}, enfant(s)=${nbEnfants}")

            val strategy = tp8.exo1.strategy.
            declarationFiscale(revenu, loyer, situationI(situation), nbEnfants)

            val templatemethod = tp8.exo1.templatemethod.
            declarationFiscale(revenu, loyer, situationI(situation), nbEnfants)

            assertAll(
                {assertEquals(strategy.first, templatemethod.first, 1.0)},
                {assertEquals(strategy.second.toDouble(), templatemethod.second.toDouble(), 1.0)},
                {assertEquals(strategy.third.toDouble(), templatemethod.third.toDouble(), 1.0)},
            )
            compteur ++
        }
    }

}