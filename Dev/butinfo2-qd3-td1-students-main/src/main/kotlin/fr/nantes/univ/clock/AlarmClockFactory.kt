package fr.nantes.univ.clock

/**
 * Factory for creating AlarmClock instances.
 */
class AlarmClockFactory {
    /**
     * Instantiates a new AlarmClock and initializes it with the following parameters: ring, hour and min.
     * After the instantiation, the fields ringing and isActive are set to false.
     *
     * @param ring choice of the ringtone (value between 1 and 10)
     * @param hour the alarm hour (value between 0 and 23).
     * @param min the alarm minute (value between 0 and 59).
     *
     * @return an instance of AlarmClock
     * @throws AlarmClockException when arguments are invalid
     */
    @Throws(AlarmClockException::class)
    fun createAlarmClock(
        ring: Int,
        hour: Int,
        min: Int
    ): AlarmClock {
        return BasicAlarmClock(ring, hour, min)
    }
}