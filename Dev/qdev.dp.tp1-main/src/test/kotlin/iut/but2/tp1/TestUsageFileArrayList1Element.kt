package iut.but2.tp1

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class TestUsageFileArrayList1Element {

    lateinit var file : File<Int>
    val listeVide = mutableListOf<Int>()
    val expectedList = mutableListOf(42)

    @BeforeEach
    fun reinit() {
        file = FileArrayList()
        file.insererEnQueue(42)
    }


    @Test
    fun taille() {
        assertEquals(1, file.taille())
    }

    @Test
    fun listedebut() {
        assertEquals(expectedList, file.listerDepuisDebut())
    }

    @Test
    fun listeFin() {
        assertEquals(expectedList, file.listerDepuisFin())
    }

    @Test
    fun consult0() {
        assertEquals(42, file.consulter(0))
    }

    @Test
    fun consult10() {
        assertThrows<IndexOutOfBoundsException> { file.consulter(10) }
    }


    @Test
    fun suppr() {
        assertDoesNotThrow { file.supprimerEnTete() }
    }

    @Test
    fun suppr_taille() {
        file.supprimerEnTete()
        assertEquals(0, file.taille())
    }

    @Test
    fun suppr_listedebut() {
        file.supprimerEnTete()
        assertEquals(listeVide, file.listerDepuisDebut())
    }

    @Test
    fun suppr_listefin() {
        file.supprimerEnTete()
        assertEquals(listeVide, file.listerDepuisFin())
    }

    @Test
    fun suppr_consult() {
        file.supprimerEnTete()
        assertThrows<IndexOutOfBoundsException> { file.consulter(0) }
    }
}