package iut.info2.tp6.exo3

import java.sql.DriverManager
import java.sql.SQLException

fun main() {

    try {
        // On se connecte à une BDD H2, ie au fichier data/persons.h2.mv.db
        Class.forName("org.h2.Driver")
        val connection = DriverManager.getConnection("jdbc:h2:./data/persons.h2")
        val statement = connection.createStatement()
        lateinit var sql: String

        // On efface la table Person
        sql = "DROP TABLE Person"
        statement.executeUpdate(sql)

        // On recréé une table Person
        sql = "CREATE TABLE Person (" +
                "    Id INT PRIMARY KEY NOT NULL, " +
                "    Name TEXT NOT NULL," +
                "    Salary REAL NOT NULL," +
                "    Age INT NOT NULL" +
                ")"
        statement.executeUpdate(sql)

        // On insère des personnes
        sql = "INSERT INTO Person VALUES " +
                "(31, 'Arnaud', 1045.42, 23), " +
                "(26, 'OB', 2659.6, 20), " +
                "(34, 'JFB', 2456.6, 19), " +
                "(32, 'JFB', 2456.6, 19), " +
                "(30, 'JFR', 2343.00, 22)," +
                "(33, 'JMM', 2342.99, 22) ;"
        statement.executeUpdate(sql)

        // On consulte la table Person
        sql = "SELECT * FROM Person WHERE Salary > 2000.0 ORDER BY Age ;"
        var result = statement.executeQuery(sql)
        while (result.next()) {
            println("${result.getInt("Id")} : ${result.getString("Name")}")
        }

        sql = "SELECT * FROM Person WHERE Name = 'JFB' ; "
        println(sql)
        result = statement.executeQuery(sql)
        while (result.next()) {
            println("${result.getString("Name")} (${result.getInt("Salary")})")
        }

        sql = "SELECT * FROM Person WHERE Name = 'JFR' ; "
        println(sql)
        result = statement.executeQuery(sql)
        while (result.next()) {
            println("${result.getString("Name")} (${result.getInt("Salary")})")
        }

        statement.close()
        connection.close()
    } catch (e: SQLException) {
        println(e.message)
    }


}