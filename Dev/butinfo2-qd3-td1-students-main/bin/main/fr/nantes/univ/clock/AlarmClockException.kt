package fr.nantes.univ.clock

class AlarmClockException(private val msg: String) : Exception() {
    override fun toString(): String {
        return msg + " : " + super.toString()
    }

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 1L
    }
}