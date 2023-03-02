package Application.Bean

import oracle.sql.INTERVALDS
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class employe(nuempl: Int,nomempl: String,hebdo: Int,
              affect: Int,salaire: Int) {
    private var nuempl: Int
    private var nomempl: String
    private var hebdo: Int
    private var affect: Int
    private var salaire: Int

    init {
        this.nuempl=nuempl
        this.nomempl=nomempl
        this.hebdo=hebdo
        this.affect=affect
        this.salaire=salaire
    }
    fun setNuempl(new:Int){
        this.nuempl=new
    }
    fun setNomempl(new:String){
        this.nomempl=new
    }
    fun setHebdo(new:Int){
        this.hebdo=new
    }
    fun setAffect(new:Int){
        this.affect=new
    }
    fun setSalaire(new:Int){
        this.salaire=new
    }
    fun getNuempl(): Int{
        return this.nuempl
    }
    fun getNomempl(): String{
        return this.nomempl
    }
    fun getHebdo(): Int{
        return this.hebdo
    }
    fun getAffect(): Int{
        return  this.affect
    }
    fun getSalaire(): Int {
        return this.salaire
    }
    override fun toString(): String {
        return ("$nuempl,$nomempl,$hebdo,$affect;$salaire")
    }

}
