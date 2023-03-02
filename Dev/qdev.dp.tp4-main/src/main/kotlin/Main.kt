import iut.info2.tp4.Hashable
import iut.info2.tp4.Hashtable
import iut.info2.tp4.IHashtable
import java.io.File
import kotlin.system.measureNanoTime

data class word(val mot : String) : Hashable {
    override fun hash(): Int {
        return mot.length
    }
}
private lateinit var ht : IHashtable<word>

fun main() {
    var mtl= mutableListOf<String>()
    ht =Hashtable(10)
    File("data/dico-en.txt").forEachLine {
        mtl.add(it)
        ht.add(word(it) )
    }
    var time= measureNanoTime {
        mtl.contains("is")
    }
    println(time)
    time= measureNanoTime {
        ht.contains(word("is"))
    }
    println(time)



}