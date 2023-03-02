package fr.nantes.univ.clock

import java.util.*

class Calendar {
    init {
        println("A Calendar is instantiated")
    }

    operator fun get(field: Int): Int {
        println("Calendar consulted")
        val calendar = GregorianCalendar()
        return if (field == HOUR) calendar[GregorianCalendar.HOUR] else if (field == MINUTE) calendar[GregorianCalendar.MINUTE] else -1
    }

    companion object {
        const val HOUR = 1
        const val MINUTE = 2
    }
}