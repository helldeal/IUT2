
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import tp8.exo2.Hashable
import tp8.exo2.HashtableA
import tp8.exo2.IHashtable
import tp8.exo2.SimpleHashtable


class TestSimpleHashtable {

    data class Truc(private val id : Int) : Hashable {
        override fun hash(): Int {
            return id
        }
    }


    private lateinit var table : HashtableA<Truc>

    @BeforeEach
    fun init() {
            table = SimpleHashtable(5)
    }

    @Test
    fun testPlusieursElements() {
        assertAll(
            { assertTrue(table.add(Truc(1))) },
            { assertTrue(table.add(Truc(5))) },
            { assertTrue(table.add(Truc(9))) },
            { assertTrue(table.add(Truc(7))) },
        )

        var ite = table.iterator()
        while (ite.hasNext()) {
            val element : Truc = ite.next()
            println(element)
        }

        for (element in table) {
            println(element)
        }

        val attendu = listOf(Truc(5), Truc(1),Truc(7), Truc(9))
        assertIterableEquals(attendu, table)

        assertAll(
            {  assertTrue(Truc(1) in table) },
            {  assertTrue(Truc(5) in table) },
            {  assertTrue(Truc(7) in table) },
            {  assertTrue(Truc(9) in table) }
        )
    }




}