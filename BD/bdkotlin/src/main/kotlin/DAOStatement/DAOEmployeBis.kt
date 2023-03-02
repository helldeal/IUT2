package Application.DAOStamement

import Application.Bean.employe
import BD.SessionOracle
import java.sql.Connection
import java.sql.*

class DAOEmployeBis(val ss: SessionOracle) {
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
        val requete="INSERT INTO employe values(?,?,?,?,?)"
        try {
            val stmt: PreparedStatement = conn!!.prepareStatement(requete)
            stmt.setInt(1,e.getNuempl())
            stmt.setString(2,e.getNomempl())
            stmt.setInt(3,e.getHebdo())
            stmt.setInt(4,e.getAffect())
            stmt.setInt(5,e.getSalaire())
            val result = stmt.executeUpdate() //Le contenu du select est dans ResultSet

            println("Tache terminée")
        }

        catch(e: SQLException){
            print("${e.errorCode} : ${e.message}")
        }

    }
    fun delete(e: employe){
        var conn: Connection? = null
        conn= session?.getConnectionOracle()
        val requete="Delete from employe where nuempl =?"
        try {
            val stmt: PreparedStatement = conn!!.prepareStatement(requete)// Création d'une requete de type Statemen
            stmt.setInt(1,e.getNuempl())
            val result = stmt.executeUpdate() //Le contenu du select est dans ResultSet

            println("Tache terminée")
        }
        catch(e: SQLException){
            print("${e.errorCode} : ${e.message}")
        }

    }
    fun update(e: employe){
        var conn: Connection? = null
        conn= session?.getConnectionOracle()
        val requete="Update employe SET nomempl=?," +
                "hebdo=?," +
                "affect=?," +
                "salaire=?" +
                "where nuempl=?"

        try {
            val stmt: PreparedStatement = conn!!.prepareStatement(requete)// Création d'une requete de type Statemen
            stmt.setInt(5,e.getNuempl())
            stmt.setString(1,e.getNomempl())
            stmt.setInt(2,e.getHebdo())
            stmt.setInt(3,e.getAffect())
            stmt.setInt(4,e.getSalaire())
            val result = stmt.executeUpdate() //Le contenu du select est dans ResultSet

            println("Tache terminée")
        }
        catch(e: SQLException){
            print("${e.errorCode} : ${e.message}")
        }

    }
}