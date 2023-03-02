package fr.nantes.univ.clock

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFailsWith

internal class BasicAlarmClockTest {

    @Test
    fun construct() {
        val testCases = listOf(
                Triple(-1, 0, 1),
                Triple(24, 0, 1),
                Triple(0, -1, 1),
                Triple(0, 60, 1),
                Triple(0, 0, 0),
                Triple(0, 0, 11)
        )

        testCases.forEach { (hour, min, ring) ->
            assertFailsWith<AlarmClockException> {
                BasicAlarmClock(ring, hour, min)
            }
        }
        assertDoesNotThrow {
            var testbon=BasicAlarmClock(1,2,3)
            assertEquals(3,testbon.min)
            assertEquals(2,testbon.hour)
            assertEquals(1,testbon.ring)
            assertFalse(testbon.isEnabled)
            assertFalse(testbon.isRinging)
        }
    }

    @Test
    fun selectRing() {
        var testbon=BasicAlarmClock(1,2,3)
        assertThrows<AlarmClockException> {
            testbon.selectRing(-10)
            testbon.selectRing(100)
        }
        assertFalse(testbon.selectRing(1))
        assertTrue(testbon.selectRing(5))

        //Manque le test ringing
    }

    @Test
    fun checkTimeAndRing() {
        // create an alarm clock that is set to go off at the current time
        val currentHour = (Calendar.HOUR)
        val currentMinute = (Calendar.MINUTE)
        val alarmClock = BasicAlarmClock(1, currentHour, currentMinute)
        alarmClock.enable()

        // check that the alarm is ringing
        alarmClock.checkTimeAndRing()
        assertTrue(alarmClock.isRinging)
        assertFalse(alarmClock.isEnabled)

        // check that the alarm is not ringing if it's not enabled
        alarmClock.disable()
        alarmClock.checkTimeAndRing()
        assertFalse(alarmClock.isRinging)
        assertFalse(alarmClock.isEnabled)
    }
}