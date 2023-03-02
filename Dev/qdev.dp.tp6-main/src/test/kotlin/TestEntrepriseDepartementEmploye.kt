import iut.info2.tp6.exo2.Departement
import iut.info2.tp6.exo2.Employe
import iut.info2.tp6.exo2.Entreprise
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestEntrepriseDepartementEmploye {

    @Test
    fun testCompta() {
        val compta = Departement()
        compta.ajouter(Employe("Anurak", 2_348.98 ))
        assertEquals(2_348.98, compta.salaire(), 0.01)
    }

    @Test
    fun testRh() {
        val rh = Departement()
        rh.ajouter(Employe("Fatima", 2_678.12))
        rh.ajouter(Employe("Christophe", 1_345.76))
        assertEquals(2_678.12+1_345.76, rh.salaire(), 0.01)
    }

    @Test
    fun testSI() {
        val SI = Departement()
        val reseau = Departement()
        val developpement = Departement()
        val test = Departement()
        SI.ajouter(reseau)
        SI.ajouter(developpement)
        SI.ajouter(test)
        SI.ajouter(Employe("Jeanne", 12_000.23))
        reseau.ajouter(Employe("Henri", 2_345.0))
        developpement.ajouter(Employe("Paul", 3_456.0))
        developpement.ajouter(Employe("Louise", 4_348.12))
        developpement.ajouter(Employe("Mei", 4_348.12))
        developpement.ajouter(Employe("Abdel", 3_214.12))
        assertEquals(12_000.23 + 2_345.0 + 3_456.0 + 4_348.12 + 4_348.12 + 3_214.12, SI.salaire(), 0.01)
    }

    @Test
    fun testEntreprise() {
        val entreprise = Entreprise()
        val SI = Departement()
        val reseau = Departement()
        val developpement = Departement()
        val test = Departement()
        val compta = Departement()
        val rh = Departement()
        entreprise.ajouter(SI)
        SI.ajouter(reseau)
        SI.ajouter(developpement)
        SI.ajouter(test)
        entreprise.ajouter(compta)
        entreprise.ajouter(rh)
        entreprise.ajouter(Employe("Felicie", 6_786.23))
        SI.ajouter(Employe("Jeanne", 12_000.23))
        reseau.ajouter(Employe("Henri", 2_345.0))
        developpement.ajouter(Employe("Paul", 3_456.0))
        developpement.ajouter(Employe("Louise", 4_348.12))
        developpement.ajouter(Employe("Mei", 4_348.12))
        developpement.ajouter(Employe("Abdel", 3_214.12))
        compta.ajouter(Employe("Anurak", 2_348.98 ))
        rh.ajouter(Employe("Fatima", 2_678.12))
        rh.ajouter(Employe("Christophe", 1_345.76))
        assertEquals(6_786.23
                + 12_000.23 + 2_345.0 + 3_456.0 + 4_348.12 + 4_348.12 + 3_214.12
                + 2_348.98
                + 2_678.12+1_345.76,
            entreprise.salaire(), 0.01)

    }


}