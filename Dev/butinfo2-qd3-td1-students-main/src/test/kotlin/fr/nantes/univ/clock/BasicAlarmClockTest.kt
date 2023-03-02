package fr.nantes.univ.clock

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.test.assertFailsWith

internal class BasicAlarmClockTest {

    @Test
    fun construct() {
        val testCases = listOf(
                Triple(-1, 0, 1), //chemin 1
                Triple(24, 0, 1), //chemin 2
                Triple(0, -1, 1), //chemin 3
                Triple(0, 60, 1), //chemin 4
                Triple(0, 0, 0), //chemin 5
                Triple(0, 0, 11)  //chemin 5
        )

        testCases.forEach { (hour, min, ring) ->
            assertThrows<AlarmClockException> {
                BasicAlarmClock(ring, hour, min)
            }
        }
        assertDoesNotThrow {
            //chemin 6
            var testbon=BasicAlarmClock(10,0,59)
            assertEquals(10,testbon.ring)
            testbon=BasicAlarmClock(1,23,0)
            assertEquals(1,testbon.ring)
            testbon=BasicAlarmClock(1,2,3)
            assertEquals(3,testbon.min)
            assertEquals(2,testbon.hour)
            assertEquals(1,testbon.ring)
            assertFalse(testbon.isEnabled)
            assertFalse(testbon.isRinging)
        }
    }

    @Test
    fun selectRing() {
        var testbon=BasicAlarmClock(2,2,3)
        assertThrows<AlarmClockException> {
            testbon.selectRing(-10)
            testbon.selectRing(100)
        }
        assertFalse(testbon.isRinging)
        assertFalse(testbon.selectRing(2))
        assertTrue(testbon.selectRing(1))
        assertTrue(testbon.selectRing(10))

        //test ringing
        val cal = Calendar()
        val alarmClock = BasicAlarmClock(1, cal[Calendar.HOUR], cal[Calendar.MINUTE])

        alarmClock.enable()
        alarmClock.checkTimeAndRing(cal)
        assertFalse(alarmClock.selectRing(8))
    }

    @Test
    fun checkTimeAndRing() {
        val cal = Calendar()
        val alarmClock = BasicAlarmClock(1, cal[Calendar.HOUR], cal[Calendar.MINUTE])

        //disable
        alarmClock.checkTimeAndRing(cal)
        assertFalse(alarmClock.isRinging)
        assertFalse(alarmClock.isEnabled)

        //enable
        alarmClock.disable()
        alarmClock.enable()
        alarmClock.checkTimeAndRing(cal)
        assertTrue(alarmClock.isRinging)
        assertFalse(alarmClock.isEnabled)
        assertThrows<AlarmClockException> { alarmClock.enable() }
        assertThrows<AlarmClockException> { alarmClock.disable() }

    }

    @Test
    fun addMin(){
        var testbon=BasicAlarmClock(1,22,30)
        //Chemin 1
        assertThrows<AlarmClockException> {
            testbon.addMin(-1)
        }
        //chemin 1 1 1
        testbon=BasicAlarmClock(1,22,30)
        testbon.addMin(0)
        testbon.addMin(90)
        //chemin 1 1 0
        testbon=BasicAlarmClock(1,10,30)
        testbon.addMin(90)
        //chemin 1 0 0
        testbon=BasicAlarmClock(1,10,30)
        testbon.addMin(80)
        //chemin 0 1 0
        testbon=BasicAlarmClock(1,10,30)
        testbon.addMin(30)
        //chemin 0 1 1
        testbon=BasicAlarmClock(1,23,30)
        testbon.addMin(30)
        //chemin 1 0 1
        testbon=BasicAlarmClock(1,23,30)
        testbon.addMin(70)
        //chemin 0 0 0
        testbon=BasicAlarmClock(1,23,30)
        testbon.addMin(10)
        testbon.addMin(60)
        testbon=BasicAlarmClock(1,0,0)
        testbon.addMin(1380)
        assertEquals(testbon.hour,23)
        assertEquals(testbon.min,0)
        testbon=BasicAlarmClock(1,0,0)
        testbon.addMin(1440)
        assertEquals(testbon.hour,0)
        assertEquals(testbon.min,0)
        testbon=BasicAlarmClock(1,0,0)
        testbon.addMin(70)
        assertEquals(testbon.hour,1)
        assertEquals(testbon.min,10)
        testbon=BasicAlarmClock(1,0,0)
        testbon.addMin(59)
        assertEquals(testbon.hour,0)
        assertEquals(testbon.min,59)
        testbon=BasicAlarmClock(1,0,10)
        testbon.addMin(59)
        assertEquals(testbon.hour,1)
        assertEquals(testbon.min,9)

    }

    @Test
    fun switchOff(){
        // TODO: 25/01/2023
    }

    @Test
    fun outilsup(){
        var alarmClock=BasicAlarmClock(1,0,0)
        var alarmClocktrue=BasicAlarmClock(1,0,0)
        var alarmClockfalse=BasicAlarmClock(1,2,0)
        assertEquals(alarmClocktrue==alarmClock,true)
        assertEquals(alarmClockfalse==alarmClock,false)

        println("$alarmClock ${alarmClock.hashCode()}")

    }


}