package Application.DAOStamement

import Application.Bean.employe
import BD.SessionOracle
import java.sql.Connection
import java.sql.*

class DAOEmploye(val ss: SessionOracle) {
    var session: SessionOracle? = null
    init {
        this.session=ss
    }

    fun read(){


        //var essai = SessionOracle();
        var conn: Connection? = null
        conn= session?.getConnectionOracle()
        val requete: String="SELECT * FROM employe"
        try {
            val stmt: Statement = conn!!.createStatement()// Création d'une requete de type Statemen
            val result: ResultSet= stmt.executeQuery(requete) //Le contenu du select est dans ResultSet

            /* Parcourir le résultat du select avec la fonction next();*/
            while (result!!.next()) {

                // getting the value of the id column
                val id = result.getInt("nuempl")
                val nom=result.getString("nomempl")
                val hebdo = result.getInt("hebdo")
                val affect = result.getInt("affect")
                val salaire = result.getInt("salaire")
                println("$id $nom $hebdo $affect $salaire")

            }
            println("Tache terminée")
            result.close()
        }

        catch(e: SQLException){
            e.printStackTrace()
        }
    }
    fun create(e: employe){
        var conn: Connection? = null
        conn= session?.getConnectionOracle()
        val requete="INSERT INTO employe values(${e.getNuempl()},'${e.getNomempl()}',${e.getHebdo()},${e.getAffect()},${e.getSalaire()})"
        try {
            val stmt: Statement = conn!!.createStatement()// Création d'une requete de type Statemen
            val result = stmt.executeUpdate(requete) //Le contenu du select est dans ResultSet

            println("Tache terminée")
        }

        catch(e: SQLException){
            print("${e.errorCode} : ${e.message}")
        }
    }
    fun delete(e: employe){
        var conn: Connection? = null
        conn= session?.getConnectionOracle()
        val requete="Delete from employe where nuempl =${e.getNuempl()}"
        try {
            val stmt: Statement = conn!!.createStatement()// Création d'une requete de type Statemen
            val result = stmt.executeUpdate(requete) //Le contenu du select est dans ResultSet

            println("Tache terminée")
        }
        catch(e: SQLException){
            print("${e.errorCode} : ${e.message}")
        }
    }
    fun update(e: employe){
        var conn: Connection? = null
        conn= session?.getConnectionOracle()
        val requete="Update employe SET nomempl='${e.getNomempl()}'," +
                "hebdo=${e.getHebdo()}," +
                "affect=${e.getAffect()}," +
                "salaire=${e.getSalaire()}" +
                "where nuempl=${e.getNuempl()}"
        try {
            val stmt: Statement = conn!!.createStatement()// Création d'une requete de type Statemen
            val result = stmt.executeUpdate(requete) //Le contenu du select est dans ResultSet

            println("Tache terminée")
        }
        catch(e: SQLException){
            print("${e.errorCode} : ${e.message}")
        }
    }


}