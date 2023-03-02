package iut.but2.tp1

import org.junit.jupiter.api.Test
import kotlin.reflect.KVisibility

class TestUMLfileChainee {
    val uml = UMLChecker(FileChainee::class)

    @Test
    fun extend() {
        uml.extend("File")
    }

    @Test
    fun attribute_debut() {
        uml.attributeCheck(
            "debut",
            "iut.but2.tp1.Cellule<E>?",
            KVisibility.PRIVATE
        )
    }

    @Test
    fun attribute_fin() {
        uml.attributeCheck(
            "fin",
            "iut.but2.tp1.Cellule<E>?",
            KVisibility.PRIVATE
        )
    }
}