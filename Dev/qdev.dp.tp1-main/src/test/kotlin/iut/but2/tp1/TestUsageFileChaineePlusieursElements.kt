package iut.but2.tp1

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class TestUsageFileChaineePlusieursElements {

    lateinit var file  : File<Int>

    val listeVide = mutableListOf<Int>()
    val expectedListDebut = mutableListOf<Int>(100, 0, 42)
    val expectedListFin = mutableListOf<Int>(42, 0, 100)
    val expectedList = mutableListOf<Int>(42)

    @BeforeEach
    fun reinit() {
        file = FileChainee<Int>()
        file.insererEnQueue(100)
        file.insererEnQueue(0)
        file.insererEnQueue(42)
    }

    @Test
    fun taille() {
        assertEquals(3, file.taille())
    }

    @Test
    fun listes() {
        assertAll(
            { assertEquals(expectedListDebut, file.listerDepuisDebut()) },
            { assertEquals(expectedListFin, file.listerDepuisFin()) },
        )
    }

    @Test
    fun consultations() {
        assertAll(
            { assertEquals(100, file.consulter(0)) },
            { assertEquals(0, file.consulter(1)) },
            { assertEquals(42, file.consulter(2)) },
            { assertThrows<IndexOutOfBoundsException> { file.consulter(10) } },
        )
    }

    @Test
    fun deuxSuppr() {
        assertDoesNotThrow { file.supprimerEnTete() }
        assertDoesNotThrow { file.supprimerEnTete() }
    }

    @Test
    fun deuxSuppr_listedebut() {
        file.supprimerEnTete()
        file.supprimerEnTete()
        assertEquals(expectedList, file.listerDepuisDebut())
    }

    @Test
    fun deuxSuppr_listefin() {
        file.supprimerEnTete()
        file.supprimerEnTete()
        assertEquals(expectedList, file.listerDepuisFin())
    }

    @Test
    fun deuxSuppr_consult() {
        file.supprimerEnTete()
        file.supprimerEnTete()
        assertAll(
            { assertEquals(42, file.consulter(0)) },
            { assertThrows<IndexOutOfBoundsException> { file.consulter(10) } },
        )
    }

    @Test
    fun troisSuppr() {
        file.supprimerEnTete()
        file.supprimerEnTete()
        assertDoesNotThrow { file.supprimerEnTete() }
    }

    @Test
    fun troisSuppr_listeDebut() {
        file.supprimerEnTete()
        file.supprimerEnTete()
        file.supprimerEnTete()
        assertEquals(listeVide, file.listerDepuisDebut())
    }

    @Test
    fun troisSuppr_listeFin() {
        file.supprimerEnTete()
        file.supprimerEnTete()
        file.supprimerEnTete()
        assertEquals(listeVide, file.listerDepuisFin())
    }

    @Test
    fun troisSuppr_consult() {
        file.supprimerEnTete()
        file.supprimerEnTete()
        file.supprimerEnTete()
        assertThrows<IndexOutOfBoundsException> { file.consulter(0) }
    }
}