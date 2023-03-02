package iut.info2.tp6.exo3

import java.sql.DriverManager

class FruitMariaDbDAO(host : String, database : String, user : String, password : String) : FruitDAO {

    val url = "jdbc:mariadb://$host/$database?user=$user&password=$password"

    fun restaurationDatabase() {
        val connection = DriverManager.getConnection(url)
        val statement = connection.createStatement()
        statement.executeUpdate("DROP TABLE DB_Fruits")
        statement.executeUpdate("CREATE TABLE DB_Fruits AS SELECT * FROM DB_Fruits_COPIE; ")
        statement.close()
        connection.close()
    }

    override fun donneLesFruits(): List<Fruit> {
        TODO("Not yet implemented")
    }

    override fun donneLesFruitsCoutantMoinsQue(prix: Double): List<Fruit> {
        TODO("Not yet implemented")
    }

    override fun donneLeFruit(nom: String): Fruit? {
        TODO("Not yet implemented")
    }

    override fun enregistreLesFruits(fruits: List<Fruit>) {
        TODO("Not yet implemented")
    }

    override fun ajouteUnFruit(fruit: Fruit) {
        TODO("Not yet implemented")
    }
}