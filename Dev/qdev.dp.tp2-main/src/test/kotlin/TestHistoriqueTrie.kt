import iut.r304.tp2.*
import iut.r304.tp2.Date
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class TestHistoriqueTrie {

    @Test
    fun testHisto() {
        val paques: Paques = PaquesGregorienne()

        val annees = listOf(2022, 125, 1492, 2019, 2018, 1583, 2016,
                            1492, 23, 2017, 2002, 1582, 2018)
        for (annee in annees) {
            try { paques.calculeDatePaques(annee)
            } catch (e: Exception) {
                //
            }
        }

        val resultat: List<Date> = listOf(
            Date(27, 3, 2016), Date(31, 3, 2002),
            Date(1, 4, 2018), Date(1, 4, 2018),
            Date(10, 4, 1583), Date(16, 4, 2017),
            Date(17, 4, 2022), Date(21, 4, 2019))


        Assertions.assertIterableEquals(resultat, paques.historiqueResultatsTries())
    }

    @Test
    fun testHisto2() {
        val paques: Paques = PaquesJulienne()

        val annees = listOf(2022, 125, 1492, 2019, 2018, 1583, 2016, 1492, 23,
                            2017, 2002, 1582, 2018)
        for (annee in annees) {
            try { paques.calculeDatePaques(annee)
            } catch (e: Exception) {
                //
            }
        }
        val resultat: List<Date> = listOf(
            Date(26, 3, 2018),Date(26, 3, 2018),
            Date(31, 3, 1583), Date(3, 4, 2017),
            Date(11, 4, 2022), Date(15, 4, 1582),
            Date(15, 4, 2019), Date(18, 4, 2016),
            Date(22, 4, 1492),  Date(22, 4, 1492),
            Date(22, 4, 2002)
        )

        Assertions.assertIterableEquals(resultat, paques.historiqueResultatsTries())
    }

    @Test
    fun testHistoAutrement() {
        val paques: Paques = PaquesJulienne()

        val annees = listOf(2022, 125, 1492, 2019, 2018, 1583, 2016, 1492, 23,
            2017, 2002, 1582, 2018)
        for (annee in annees) {
            try { paques.calculeDatePaques(annee)
            } catch (e: Exception) {
                //
            }
        }
        val resultat: List<Date> = listOf(
            Date(22, 4, 1492),
            Date(22, 4, 1492),
            Date(15, 4, 1582),
            Date(31, 3, 1583),
            Date(22, 4, 2002),
            Date(18, 4, 2016),
            Date(3, 4, 2017),
            Date(26, 3, 2018),
            Date(26, 3, 2018),
            Date(15, 4, 2019),
            Date(11, 4, 2022)
        )

        Assertions.assertIterableEquals(resultat,
            paques.historiqueResultatsTriesAutrement())
    }


    @Test
    fun testHisto_sansEffetDeBord() {
        val paques: Paques = PaquesGregorienne()

        val annees = listOf(2022, 125, 1492, 2019, 2018, 1583, 2016,
            1492, 23, 2017, 2002, 1582, 2018)
        for (annee in annees) {
            try { paques.calculeDatePaques(annee)
            } catch (e: Exception) {
                //
            }
        }
        paques.historiqueResultatsTries()
        // effet de bord possible

        val resultat: List<Date> = listOf(
            Date(17, 4, 2022), Date(21, 4, 2019),
            Date(1, 4, 2018), Date(10, 4, 1583),
            Date(27, 3, 2016), Date(16, 4, 2017),
            Date(31, 3, 2002), Date(1, 4, 2018)
        )
        Assertions.assertIterableEquals(resultat, paques.historiqueResultats())

    }

}