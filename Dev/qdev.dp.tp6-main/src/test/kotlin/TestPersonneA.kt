import iut.info2.tp6.exo1.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class TestPersonneA {

    lateinit var xxx: PersonneA


    @Test
    fun `AXBG Azerty is a person`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("AXBG Azerty") }
        assertEquals(Personne("AXBG", "Azerty"), xxx)
    }

    @Test
    fun `Azerty AXBG is a person`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("Azerty AXBG") }
        assertEquals(Personne("AXBG", "Azerty"), xxx)
    }

    @Test
    fun `A X is a person`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("A X") }
        assertEquals(Personne("A", "X"), xxx)
    }

    @Test
    fun `azerty AXBG is not a person`() {
        assertThrows<PersonneException> { xxx = PersonneA.donnePersonneSaisie("azerty AXBG") }
    }

    @Test
    fun `AXBG azerty is not a person`() {
        assertThrows<PersonneException> { xxx = PersonneA.donnePersonneSaisie("AXBG azerty") }
    }

    @Test
    fun `SA ANONYME is a society`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("SA ANONYME") }
        assertEquals(Entreprise("ANONYME", CategorieEntreprise.SA), xxx)
    }

    @Test
    fun `EURL ANONYME is a society`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("EURL ANONYME") }
        assertEquals(Entreprise("ANONYME", CategorieEntreprise.EURL), xxx)
    }

    @Test
    fun `SARL ANONYME is a society`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("SARL ANONYME") }
        assertEquals(Entreprise("ANONYME", CategorieEntreprise.SARL), xxx)
    }

    @Test
    fun `SCP ANONYME is a society`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("SCP ANONYME") }
        assertEquals(Entreprise("ANONYME", CategorieEntreprise.SCP), xxx)
    }

    @Test
    fun `SCP X is a society`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("SCP X") }
        assertEquals(Entreprise("X", CategorieEntreprise.SCP), xxx)
    }

    @Test
    fun `SA a is not a society`() {
        assertThrows<PersonneException> { xxx = PersonneA.donnePersonneSaisie("SA a") }
    }

    @Test
    fun `Sarl ANONYME is not a society`() {
        assertDoesNotThrow { xxx = PersonneA.donnePersonneSaisie("Sarl ANONYME") }
        assertFalse(xxx is Entreprise)
    }

}