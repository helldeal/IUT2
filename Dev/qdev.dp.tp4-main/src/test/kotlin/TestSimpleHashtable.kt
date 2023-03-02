import iut.info2.tp4.CollisionException
import iut.info2.tp4.Hashable
import iut.info2.tp4.IHashtable
import iut.info2.tp4.SimpleHashtable
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows


class TestSimpleHashtable {

    data class Truc(private val id : Int) : Hashable {
        override fun hash(): Int {
            return id
        }
    }


    private lateinit var table : IHashtable<Truc>

    @BeforeEach
    fun init() {
            table = SimpleHashtable(5)
    }

    @Test
    fun testVide() {
        assertFalse(Truc(10) in table)
    }

    @Test
    fun testUnElement() {
        assertAll(
            { assertTrue(table.add(Truc(1))) },
            { assertTrue(Truc(1) in table) },
            { assertFalse(Truc(4) in table) })
    }

    @Test
    fun testNoColisions() {
        table.add(Truc(1))
        assertDoesNotThrow { table.add(Truc(5)) }
        assertDoesNotThrow { table.add(Truc(7)) }
    }

    @Test
    fun testPlusieursElements() {
        assertAll(
            { assertTrue(table.add(Truc(1))) },
            { assertTrue(table.add(Truc(5))) },
            { assertTrue(table.add(Truc(7))) },
            { assertTrue(table.add(Truc(9))) }
        )
        assertAll(
            {  assertTrue(Truc(1) in table) },
            {  assertTrue(Truc(5) in table) },
            {  assertTrue(Truc(7) in table) },
            {  assertTrue(Truc(9) in table) }
        )
    }

    @Test
    fun testColision() {
        table.add(Truc(1))
        table.add(Truc(5))
        table.add(Truc(7))
        table.add(Truc(9))
        assertThrows<CollisionException> { table.add(Truc(10)) }
    }

    @Test
    fun testElementDejaPresent() {
        table.add(Truc(1))
        table.add(Truc(5))
        table.add(Truc(7))
        table.add(Truc(9))
        // ajout d'un élément déjà présent
        assertFalse(table.add(Truc(1)))
    }

    @Test
    fun testSupprimer() {
        table.add(Truc(1))
        table.add(Truc(5))
        table.add(Truc(7))
        table.add(Truc(9))
        assertTrue(table.remove(Truc(7)))
        assertFalse(Truc(7) in table)
    }

    @Test
    fun testSupprimer_elementAbsent() {
        table.add(Truc(1))
        table.add(Truc(5))
        table.add(Truc(7))
        table.add(Truc(9))
        assertFalse(table.remove(Truc(2)))
    }

    @Test
    fun testSupprimer_elementCollision() {
        table.add(Truc(1))
        table.add(Truc(5))
        table.add(Truc(7))
        table.add(Truc(9))
        assertFalse(table.remove(Truc(10)))
    }



}