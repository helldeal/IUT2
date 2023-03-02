import fr.nantes.univ.clock.AlarmClockException
import fr.nantes.univ.clock.BasicAlarmClock
import fr.nantes.univ.clock.Calendar

fun main(args: Array<String>) {
    println("Hello IUT World")

    try {
        val ac = BasicAlarmClock(3, 12, 30)
        ac.enable()
        println(ac)
        ac.addMin(55)
        ac.enable()
        println(ac)
        ac.checkTimeAndRing()
        println(ac)

        val cal= Calendar()
        println(Calendar.HOUR)
        println(cal[Calendar.HOUR])
        println(cal[Calendar.MINUTE])

    } catch (e: AlarmClockException) {
        println(e)
    }
}