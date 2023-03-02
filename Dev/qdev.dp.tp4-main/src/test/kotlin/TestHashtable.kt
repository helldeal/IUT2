import iut.info2.tp4.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll


class TestHashtable {

    data class Truc(private val id : Int) : Hashable {
        override fun hash(): Int {
            return id
        }
    }

    private lateinit var table : IHashtable<Truc>

    @BeforeEach
    fun init() {
            table = Hashtable(5)
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
        // ajout d'élements en collision
        assertTrue(table.add(Truc(10)))
        assertTrue(table.add(Truc(2)))
        assertTrue(table.add(Truc(12)))
        // nombre de collisions évitées
        assertEquals(3,table.avoidedCollisions())
    }

    @Test
    fun testColision_2() {
        table.add(Truc(1))
        table.add(Truc(5))
        table.add(Truc(7))
        table.add(Truc(9))
        // ajout d'élements en collision
        assertDoesNotThrow { table.add(Truc(10))}
        assertDoesNotThrow { table.add(Truc(2))}
        assertDoesNotThrow { table.add(Truc(12))}
        // nombre de collisions évitées
        assertEquals(3,table.avoidedCollisions())
    }

    @Test
    fun testElementDejaPresent() {
        table.add(Truc(1))
        table.add(Truc(5))
        table.add(Truc(7))
        table.add(Truc(9))
        table.add(Truc(10))
        table.add(Truc(10))
        table.add(Truc(2))
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

    @Test
    fun testRedimensionnement_20() {
        val table1 = Hashtable<Truc>(5)
        table1.add(Truc(1))
        table1.add(Truc(5))
        table1.add(Truc(7))
        table1.add(Truc(9))
        table1.add(Truc(10))
        table1.add(Truc(10))
        table1.add(Truc(2))
        table1.add(Truc(12))
        val table2 = Hashtable.resize(table1, 20)
        assertEquals(0,table2.avoidedCollisions())
        //
        assertTrue(table2.contains(Truc(1)))
        assertTrue(table2.contains(Truc(5)))
        assertTrue(table2.contains(Truc(7)))
        assertTrue(table2.contains(Truc(9)))
        assertTrue(table2.contains(Truc(10)))
        assertTrue(table2.contains(Truc(2)))
        assertTrue(table2.contains(Truc(12)))
    }

    @Test
    fun testRedimensionnement_1() {
        val table1 = Hashtable<Truc>(5)
        table1.add(Truc(1))
        table1.add(Truc(5))
        table1.add(Truc(7))
        table1.add(Truc(9))
        table1.add(Truc(10))
        table1.add(Truc(10))
        table1.add(Truc(2))
        table1.add(Truc(12))
        val table2 = Hashtable.resize(table1, 1)
        assertEquals(6,table2.avoidedCollisions())
        //
        assertTrue(table2.contains(Truc(1)))
        assertTrue(table2.contains(Truc(5)))
        assertTrue(table2.contains(Truc(7)))
        assertTrue(table2.contains(Truc(9)))
        assertTrue(table2.contains(Truc(10)))
        assertTrue(table2.contains(Truc(2)))
        assertTrue(table2.contains(Truc(12)))
    }

}