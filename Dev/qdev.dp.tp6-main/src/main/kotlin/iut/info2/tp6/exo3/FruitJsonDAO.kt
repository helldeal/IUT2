package iut.info2.tp6.exo3

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FruitJsonDAO(nomFichier : String) : FruitDAO {
    private val fichier=nomFichier


    override fun donneLesFruits(): List<Fruit> {
        return Json.decodeFromStream<MutableList<Fruit>>(FileInputStream(fichier)).sortedBy { it.nom }
    }

    override fun donneLesFruitsCoutantMoinsQue(prix: Double): List<Fruit> {
        return donneLesFruits().filter { it.prix<prix }.sortedBy { it.prix }
    }

    override fun donneLeFruit(nom: String): Fruit? {
        return donneLesFruits().find { it.nom==nom  }
    }

    override fun enregistreLesFruits(fruits: List<Fruit>) {
        Json.encodeToStream(fruits,FileOutputStream(fichier))
    }

    override fun ajouteUnFruit(fruit: Fruit) {
        if (donneLeFruit(fruit.nom)!=null) throw FruitDejaPresentException()
        val list =donneLesFruits().toMutableList()
        list.add(fruit)
        enregistreLesFruits(list)



    }

}