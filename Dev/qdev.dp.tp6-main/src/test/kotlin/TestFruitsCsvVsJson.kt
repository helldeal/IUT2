import iut.info2.tp6.exo3.FruitCsvDAO
import iut.info2.tp6.exo3.FruitDAO
import iut.info2.tp6.exo3.FruitJsonDAO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestFruitsCsvVsJson {

    lateinit var json: FruitDAO
    lateinit var csv: FruitDAO

    @BeforeEach
    fun init() {
        File( "./data/init/fruits.csv")
            .copyTo(File("./data/fruits.csv"), overwrite = true)
        File( "./data/init/fruits.json")
            .copyTo(File("./data/fruits.json"), overwrite = true)
        json = FruitJsonDAO("./data/fruits.json")
        csv = FruitCsvDAO("./data/fruits.csv")
    }

    @Test
    fun testTousLesFruits() {
        val fruitsJ = json.donneLesFruits()
        val fruitsS = csv.donneLesFruits()
        assertEquals(fruitsJ, fruitsS)
    }

    @Test
    fun testFruitsCoutantMoinsQue10() {
        val fruitsJ = json.donneLesFruitsCoutantMoinsQue(10.0)
        val fruitsS = csv.donneLesFruitsCoutantMoinsQue(10.0)
        assertEquals(fruitsJ, fruitsS)
    }

    @Test
    fun testEnregistrement() {
        val mesFruits = json.donneLesFruitsCoutantMoinsQue(10.0)
        csv.enregistreLesFruits(mesFruits)
        assertEquals(mesFruits, csv.donneLesFruitsCoutantMoinsQue(10.0))
    }
}
