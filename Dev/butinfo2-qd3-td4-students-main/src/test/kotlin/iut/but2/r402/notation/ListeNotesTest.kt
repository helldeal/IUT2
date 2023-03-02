package iut.but2.r402.notation

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import kotlin.test.assertFails

internal class ListeNotesTest {

    @Test
    fun testMoyenne00() {
        var lnvide = ListeNotes(intArrayOf())
        assertEquals(0.0F,lnvide.moyenne())
    }
    @Test
    fun testMoyenne11() {
        var ln = ListeNotes(intArrayOf(0,20))
        assertEquals(10.0F,ln.moyenne())
    }
    @Test
    fun testNombreOccurrenceFail() {
        var ln = ListeNotes(intArrayOf(0,5,10,20,20))
        assertThrows<IllegalArgumentException> {
            ln.nombreOccurrence(21)
        }
        assertThrows<IllegalArgumentException> {
            ln.nombreOccurrence(-1)
        }
    }
    @Test
    fun testNombreOccurrence() {
       var ln = ListeNotes(intArrayOf(0,5,10,20,20))
        var lnvide = ListeNotes(intArrayOf())
        assertDoesNotThrow {
            ln.nombreOccurrence(20)
            ln.nombreOccurrence(0)
        }
        assertEquals(0,lnvide.nombreOccurrence(10))
        assertEquals(0,ln.nombreOccurrence(15))
        assertEquals(1,ln.nombreOccurrence(10))
        assertEquals(2,ln.nombreOccurrence(20))
    }
}