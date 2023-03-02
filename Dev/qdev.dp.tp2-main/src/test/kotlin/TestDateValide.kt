import iut.r304.tp2.Date
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class TestDateValide {

    @Test
    fun test() {
        var a=Date.fabrique("03-02-2003")
        assertEquals(a,Date(3,2,2003))
    }

    @Test
    fun test2() {
        var a =Date(29,2,2003)
        var b =Date(29,2,2003)
        var c =Date(29,2,2003)
        var d =Date(29,2,2003)
    }

}