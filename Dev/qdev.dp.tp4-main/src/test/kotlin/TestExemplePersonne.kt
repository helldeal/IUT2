import iut.info2.tp4.CollisionException
import iut.info2.tp4.Hashable
import iut.info2.tp4.Hashtable
import iut.info2.tp4.SimpleHashtable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestExemplePersonne {

    data class Personne1(val nom : String, val age :Int, val ville : String) : Hashable {
        override fun hash(): Int {
            return age
        }
    }

    @Test
    fun exemple1() {
        val table = SimpleHashtable<Personne1>(5)
        assertTrue(table.add(Personne1("Alfred", 42, "Nantes")))
        assertTrue(table.add(Personne1("Justine", 20, "Rennes")))
        assertTrue(table.add(Personne1("Kevin", 9, "Paris")))
    }

    data class Personne2(val nom : String, val age :Int, val ville : String) : Hashable {
        override fun hash(): Int {
            return nom[0].toInt()
        }
    }

    @Test
    fun exemple2() {
        val table = SimpleHashtable<Personne2>(5)
        assertTrue(table.add(Personne2("Alfred", 42, "Nantes")))
        assertTrue(table.add(Personne2("Justine", 20, "Rennes")))
        assertThrows<CollisionException> { table.add(Personne2("Kevin", 9, "Paris")) }
    }

    @Test
    fun exemple3() {
        val table = Hashtable<Personne2>(5)
        assertTrue(table.add(Personne2("Alfred", 42, "Nantes")))
        assertTrue(table.add(Personne2("Justine", 20, "Rennes")))
        assertDoesNotThrow { table.add(Personne2("Kevin", 9, "Paris")) }
        assertEquals(1, table.avoidedCollisions())
    }
}