package fr.nantes.univ.clock

/**
 * Basic implementation of the [AlarmClock] interface.
 *
 * Initial code from Arnaud Lanoix and Jean-Marie Mottu
 */
class BasicAlarmClock(ring: Int, hour: Int, min: Int) : AlarmClock {
    /**
     * {@inheritDoc}
     */
    /**
     * An [int] value between 1 and 10 representing the ringtone.
     */
    override var ring = 0
        private set
    /**
     * {@inheritDoc}
     */
    /**
     * An [int] value between 0 and 23 representing the alarm hour.
     */
    override var hour = 0
        private set
    /**
     * {@inheritDoc}
     */
    /**
     * An [int] value between 0 and 59 representing the alarm minutes.
     */
    override var min = 0
        private set
    /**
     * {@inheritDoc}
     */
    /**
     * A [boolean] value: true if the alarm is enabled, false otherwise.
     */
    override var isEnabled = false
        private set
    /**
     * {@inheritDoc}
     */
    /**
     * A [boolean] value: true if the alarm is ringing, false otherwise.
     */
    override var isRinging = false
        private set

    /**
     * @see fr.nantes.alarm.AlarmClock
     *
     * @param ring [BasicAlarmClock.ring]
     * @param hour [BasicAlarmClock.hour]
     * @param min [BasicAlarmClock.min]
     * @throws AlarmClockException if one parameter is out of bounds.
     */
    init {
        if (hour < 0) {
            throw AlarmClockException("bad hour: inf value")
        } else if (hour > 23) {
            throw AlarmClockException("bad hour: sup value")
        } else if (min < 0) {
            throw AlarmClockException("bad min: inf value")
        } else if (min > 59) {
            throw AlarmClockException("bad min: sup value")
        } else if (ring < 1 || ring > 10) {
            throw AlarmClockException("bad ringtone: out of limits")
        } else {
            this.ring = ring
            this.hour = hour
            this.min = min
        }
    }

    /**
     * {@inheritDoc}
     */
    @Throws(AlarmClockException::class)
    override fun selectRing(ringtone: Int): Boolean {
         if (!isRinging) {
            if (ringtone < 1 || ringtone > 10) {
                throw AlarmClockException("bad ringtone: out of bounds")
            }
             ring = ringtone
             return ring != ringtone
        } else {
            return false
        }
    }

    /**
     * {@inheritDoc}
     */
    @Throws(AlarmClockException::class)
    override fun addMin(minutes: Int) {
        var minutes = minutes
        var addedHour = 0
        var newMin = 0
        if (minutes < 0) {
            throw AlarmClockException("Cannot add negative minutes")
        }
        while (minutes > 59) {
            addedHour++
            minutes = minutes - 60
        }
        newMin = min + minutes
        if (newMin > 59) {
            addedHour++
            newMin = newMin - 60
        }
        hour = (hour + addedHour) % 24
        min = newMin
    }

    /**
     * {@inheritDoc}
     */
    @Throws(AlarmClockException::class)
    override fun enable() {
        if (isRinging) {
            throw AlarmClockException("Cannot enable the alarm when it is ringing")
        }
        isEnabled = true
    }

    /**
     * {@inheritDoc}
     */
    override fun checkTimeAndRing() {
        if (isEnabled) {
            val cal = Calendar()
            if (hour == cal[Calendar.HOUR] && min == cal[Calendar.MINUTE])
                isRinging = true
                isEnabled = false
        }
    }

    /**
     * {@inheritDoc}
     */
    @Throws(AlarmClockException::class)
    override fun disable() {
        if (isRinging) {
            throw AlarmClockException("Cannot disable the alarm when it is ringing")
        }
        isEnabled = false
    }

    /**
     * {@inheritDoc}
     */
    @Throws(AlarmClockException::class)
    override fun switchOff(snooze: Boolean) {
        isRinging = false
        if (snooze) {
            isEnabled = true
            addMin(5)
        }
    }

    override fun hashCode(): Int {
        return ring + hour + min + isEnabled.hashCode()+ isRinging.hashCode()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as BasicAlarmClock
        return ring == that.ring && hour == that.hour && min == that.min && isEnabled == that.isEnabled && isRinging == that.isRinging
    }

    /**
     * {@inheritDoc}
     */
    override fun setRingingOn() {
        isRinging = true
        isEnabled = false
    }

    override fun toString(): String {
        return "BasicAlarmClock{" +
                "ring=" + ring +
                ", hour=" + hour +
                ", min=" + min +
                ", enabled=" + isEnabled +
                ", ringing=" + isRinging +
                '}'
    }
}