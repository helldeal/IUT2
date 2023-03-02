package iut.but2.tp1

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class TestUsageFileArrayListVide {

    lateinit var file  : File<Int>
    val listeVide = mutableListOf<Int>()

    @BeforeEach
    fun reinit() {
        file = FileArrayList()
    }

    @Test
    fun taille() {
        assertEquals(0, file.taille())
    }

    @Test
    fun listedebutvide() {
        assertEquals(listeVide, file.listerDepuisDebut())
    }

    @Test
    fun listefinvide() {
        assertEquals(listeVide, file.listerDepuisFin())
    }

    @Test
    fun consult0() {
        assertThrows<IndexOutOfBoundsException> { file.consulter(0) }
    }

    @Test
    fun consult10() {
        assertThrows<IndexOutOfBoundsException> { file.consulter(10) }
    }

    @Test
    fun supp() {
        assertThrows<NoSuchElementException> { file.supprimerEnTete() }
    }


}