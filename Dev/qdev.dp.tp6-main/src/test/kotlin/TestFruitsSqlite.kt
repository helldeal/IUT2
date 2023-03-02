import iut.info2.tp6.exo3.Fruit
import iut.info2.tp6.exo3.FruitDAO
import iut.info2.tp6.exo3.FruitDejaPresentException
import iut.info2.tp6.exo3.FruitSqliteDAO
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TestFruitsSqlite {

    val data = "data"
    val ext = ".sqlite"
    val file = "fruits"

    lateinit var mesFruits: List<Fruit>
    lateinit var dao: FruitDAO

    @BeforeEach
    fun init() {
        File(data + "/" + "init/" + file + ext)
            .copyTo(File(data + "/" + file + ext), overwrite = true)
        dao = FruitSqliteDAO(data + "/" + file + ext)
    }

    @Test
    fun testTousLesFruits() {
        mesFruits = dao.donneLesFruits()
        println(mesFruits)
        assertEquals(15, mesFruits.size)
        for (i in 1 until 15) {
            assertTrue(mesFruits[i - 1].nom < (mesFruits[i].nom))
        }
        dao.enregistreLesFruits(mesFruits)
    }

    @Test
    fun testFruitsCoutantMoinsQue10() {
        mesFruits = dao.donneLesFruitsCoutantMoinsQue(10.0)
        assertEquals(4, mesFruits.size)
        for (i in 1 until 4) {
            assertTrue(mesFruits[i - 1].prix < mesFruits[i].prix)
        }
    }

    @Test
    fun testFruitBanane() {
        val banane = dao.donneLeFruit("Banane")
        assertNotNull(banane)
        assertEquals(Fruit("Banane", 8.4, 30), banane)
    }

    @Test
    fun testFruitGoyave() {
        val goyave = dao.donneLeFruit("Goyave")
        assertNotNull(goyave)
        assertEquals(Fruit("Goyave", 24.4, 0), goyave)
    }

    @Test
    fun testEnregistrement() {
        mesFruits = dao.donneLesFruitsCoutantMoinsQue(10.0)
        dao.enregistreLesFruits(emptyList())
        assertEquals(emptyList(), dao.donneLesFruits())
        dao.enregistreLesFruits(mesFruits)
        assertEquals(mesFruits, dao.donneLesFruitsCoutantMoinsQue(10.0))
    }

    @Test
    fun testAjout_exception() {
        val fruitPresent = dao.donneLeFruit("Banane")
        assertTrue(dao.donneLesFruits().contains(fruitPresent))
        assertNotNull(fruitPresent)
        assertThrows<FruitDejaPresentException> { dao.ajouteUnFruit(fruitPresent) }
    }

    @Test
    fun testAjout() {
        assertEquals(15, dao.donneLesFruits().size)
        val fruitXXX = Fruit("XXX", 4.2, 42)
        dao.ajouteUnFruit(fruitXXX)
        assertEquals(16, dao.donneLesFruits().size)
        assertEquals(fruitXXX, dao.donneLeFruit("XXX"))
        assertTrue(dao.donneLesFruitsCoutantMoinsQue(5.0).contains(fruitXXX))
    }
}
