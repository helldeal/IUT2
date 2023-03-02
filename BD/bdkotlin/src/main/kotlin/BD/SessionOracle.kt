package BD

import java.sql.*
import java.util.*


class SessionOracle(username : String,password : String) {

    var conn: Connection? = null
    var username: String    // provide the username
    var password :String // provide the corresponding password

    init {
        this.username = username
        this.password = password

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()
            conn = DriverManager.getConnection("jdbc:oracle:thin:@172.26.82.68:1521:pdb1",username, password);
            println("connexion réussie")
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
    }
    /**
     * This method makes a connection to Oracle  Server
     */


    fun getConnectionOracle(): Connection? = conn


}