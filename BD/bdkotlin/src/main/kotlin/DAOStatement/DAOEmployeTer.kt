package Application.DAOStamement

import Application.Bean.employe
import BD.SessionOracle
import oracle.jdbc.OracleTypes
import java.sql.Connection
import java.sql.*

class DAOEmployeTer(val ss: SessionOracle) {
    var session: SessionOracle? = null
    init {
        this.session=ss
    }

    fun read(){


        //var essai = SessionOracle();
        var conn: Connection? = null
        conn= session?.getConnectionOracle()
        val requete: String="call lecture.liste_employes(?)"
        try {
            var stmt : CallableStatement= conn!!.prepareCall(requete);
            stmt.registerOutParameter(1,OracleTypes.CURSOR);
            stmt.execute();
            var result: ResultSet= stmt.getObject(1) as ResultSet;
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
        val requete="call MAJ.CREER_EMPLOYE(?,?,?,?,?)"
        try {
            val stmt: CallableStatement = conn!!.prepareCall(requete)
            stmt.setInt(1,e.getNuempl())
            stmt.setString(2,e.getNomempl())
            stmt.setInt(3,e.getHebdo())
            stmt.setInt(4,e.getAffect())
            stmt.setInt(5,e.getSalaire())
            stmt.execute();
            println("Tache terminée")
        }

        catch(e: SQLException){
            print("${e.errorCode} : ${e.message}")
        }
    }
}