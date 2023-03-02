package Application


import Application.Bean.employe
import Application.DAOStamement.DAOEmploye
import Application.DAOStamement.DAOEmployeBis
import Application.DAOStamement.DAOEmployeTer
import BD.SessionOracle

fun main(args: Array<String>) {
    var essai= SessionOracle("i2c09a","maylandia44");
    var alex=employe(667,"nefertiti",35,1,0)
    var dd= DAOEmployeBis(essai)
    //dd.read()
    //dd.read()
    //dd.create(alex)
    //dd.read()
    //alex.setHebdo(34)
    //alex.setNomempl("SUNAJ")
    //dd.update(alex)
    //dd.read()
    //dd.delete(alex)

    var dd3= DAOEmployeTer(essai)
    dd.read()
    dd3.create(alex)
    dd.read()
    dd.delete(alex)
}