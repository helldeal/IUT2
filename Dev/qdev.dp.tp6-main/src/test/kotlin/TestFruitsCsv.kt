import iut.info2.tp6.exo3.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TestFruitsCsv {

    lateinit var mesFruits: List<Fruit>
    lateinit var dao: FruitDAO

    @BeforeEach
    fun init() {
        File( "./data/init/fruits.csv")
            .copyTo(File("./data/fruits.csv"), overwrite = true)
        dao = FruitCsvDAO("./data/fruits.csv")
    }

    @Test
    fun testTousLesFruits() {
        mesFruits = dao.donneLesFruits()
        println(mesFruits)
        assertEquals(15, mesFruits.size)
        for (i in 1 until 15) {
            Assertions.assertTrue(mesFruits[i - 1].nom < (mesFruits[i].nom))
        }
    }

    @Test
    fun testFruitsCoutantMoinsQue10() {
        mesFruits = dao.donneLesFruitsCoutantMoinsQue(10.0)
        assertEquals(4, mesFruits.size)
        for (i in 1 until 4) {
            Assertions.assertTrue(mesFruits[i - 1].prix < mesFruits[i].prix)
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
        Assertions.assertTrue(dao.donneLesFruits().contains(fruitPresent))
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
        Assertions.assertTrue(dao.donneLesFruitsCoutantMoinsQue(5.0).contains(fruitXXX))
    }

}