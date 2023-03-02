import iut.r304.tp2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class TestPaquesGregorienne {

    lateinit var p: Paques

    @BeforeEach
    fun init() {
        p = PaquesGregorienne()
    }

    @Test
    fun testPaquesGregorienne125() {
        Assertions.assertThrows(
            PaquesException::class.java
        ) { p.calculeDatePaques(125) }
    }

    @Test
    fun testPaquesJulienne325() {
        Assertions.assertThrows(
            PaquesException::class.java
        ) { p.calculeDatePaques(325) }
    }

    @Test
    fun testPaquesJulienne326() {
        Assertions.assertThrows(
            PaquesException::class.java
        ) { p.calculeDatePaques(326) }
    }

    @Test
    fun testPaquesJulienne327() {
        Assertions.assertThrows(
            PaquesException::class.java
        ) { p.calculeDatePaques(327) }
    }

    @Test
    fun testPaquesGregorienne1492() {
        Assertions.assertThrows(
            PaquesException::class.java
        ) { p.calculeDatePaques(1492) }
    }

    @Test
    fun testPaquesGregorienne1582() {
        Assertions.assertThrows(
            PaquesException::class.java
        ) { p.calculeDatePaques(1582) }
    }

    @Test
    fun testPaquesGregorienne1583_v0() {
        val d = p.calculeDatePaques(1583)
        assertEquals("10-4-1583", d.toString())
    }

    @Test
    fun testPaquesGregorienne1583() {
        val d = p.calculeDatePaques(1583)
        assertEquals(Date(10, 4, 1583), d)
    }

    @Test
    fun testPaquesGregorienne2006() {
        val d = p.calculeDatePaques(2006)
        assertEquals(Date(16, 4, 2006), d)
    }

    @Test
    fun testPaquesGregorienne2015() {
        val d = p.calculeDatePaques(2015)
        assertEquals(Date(5, 4, 2015), d)
    }

    @Test
    fun testPaquesGregorienne2018() {
        val d = p.calculeDatePaques(2018)
        assertEquals(Date(1, 4, 2018), d)
    }

    @Test
    fun testPaquesGregorienne2019() {
        val d = p.calculeDatePaques(2019)
        assertEquals(Date(21, 4, 2019), d)
    }

    @Test
    fun testPaquesGregorienne2022() {
        val d = p.calculeDatePaques(2022)
        assertEquals(Date(17, 4, 2022), d)
    }

    @Test
    fun testPaquesGregorienne2040() {
        val d = p.calculeDatePaques(2040)
        assertEquals(Date(1, 4, 2040), d)
    }

}