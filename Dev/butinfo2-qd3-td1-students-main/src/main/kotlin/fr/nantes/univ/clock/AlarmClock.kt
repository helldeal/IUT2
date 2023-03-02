package fr.nantes.univ.clock

/**
 * Common interface for a simple alarm clock software library.
 *
 * Created on 01/01/2023.
 * Initial code from Arnaud Lanoix and Jean-Marie Mottu
 *
 * Rehaul by Erwan Bousse in 2019-2022
 *
 * Transformed into kotlin and updated in 2023 by Jean-Marie Mottu
 */
interface AlarmClock {
    /**
     * Selects a new ringtone, if and only if this alarm clock is not ringing.
     *
     * @param ringtone the ringtone, between 1 and 10.
     * @return true when the ringtone has changed, false otherwise.
     * @throws AlarmClockException when the ringtone is invalid.
     */
    @Throws(AlarmClockException::class)
    fun selectRing(ringtone: Int): Boolean

    /**
     * Increments the ringing time with the number of minutes passed as parameter.
     * As a result, it changes the Hour and the Minutes previously stored.
     *
     * @param minutes the increment, in minutes.
     * @throws AlarmClockException if the parameter is invalid.
     */
    @Throws(AlarmClockException::class)
    fun addMin(minutes: Int)

    /**
     * Enables (activates) this Alarm Clock. The Alarm Clock only rings when it is enabled.
     * This AlarmClock cannot be enabled when it is ringing.
     *
     * @throws AlarmClockException if the alarm is ringing.
     */
    @Throws(AlarmClockException::class)
    fun enable()

    /**
     * If the system clock time matches the configured alarm time, and if the Alarm Clock is enabled,
     * rings the Alarm Clock and disables the Alarm Clock.
     *
     * This method should be called at least once per minute, in order to not miss
     * the alarm time.
     *
     */
    fun checkTimeAndRing(cal: Calendar)

    /**
     * Disables this Alarm Clock. The Alarm Clock does not ring when it is disabled.
     * This AlarmClock cannot be disabled when it is ringing.
     *
     * @throws AlarmClockException if the alarm is ringing.
     */
    @Throws(AlarmClockException::class)
    fun disable()

    /**
     * Switches off (stops) this alarm clock ring. If the parameter snooze is true, makes it enabled again and rings
     * back after 5 minutes.
     *
     * @param snooze when true, makes the alarm clock ring again, 5 minutes later.
     * @throws AlarmClockException if the alarm cannot be snoozed.
     */
    @Throws(AlarmClockException::class)
    fun switchOff(snooze: Boolean)

    /**
     * Accessor for the current status of this alarm, if it is enabled or not.
     *
     * @return a [boolean] value: true if the alarm is enabled, false otherwise.
     */
    val isEnabled: Boolean

    /**
     * Returns the current state of this alarm.
     *
     * @return a [boolean] value: true if the alarm is ringing, false otherwise.
     */
    val isRinging: Boolean

    /**
     * Accessor to this alarm ringtone.
     *
     * @return an [int] value between 1 and 10.
     */
    val ring: Int

    /**
     * Accessor to this alarm hour.
     *
     * @return an [int] value between 0 and 23.
     */
    val hour: Int

    /**
     * Accessor to this alarm's minutes.
     *
     * @return an [int] value between 0 and 59.
     */
    val min: Int

    /**
     * Setter to switch on this.ringing attribute for testing.
     * Desactivate the alarmClock to prevent inconsistency
     */
    fun setRingingOn()
}