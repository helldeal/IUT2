package main

import main.classes.Automate
import main.classes.Etat
import main.classes.MonApplication

fun main(){

    var Automates= mutableListOf<Automate>()

    //Fini par 1
    var E0= Etat("E0", hashMapOf())
    var E1= Etat("E1", hashMapOf())
    E0.setTransitions(hashMapOf('1' to E1,'0' to E0))
    E1.setTransitions(hashMapOf('1' to E1,'0' to E0))
    var Auto1 = Automate("0 et 1 fini par un 1",mutableListOf(E0,E1),
        mutableListOf('0','1'),E0,mutableListOf(E1))
    Automates.add(Auto1)

    //Smiley
    E0= Etat("E0", hashMapOf())
    E1= Etat("E1", hashMapOf())
    var E2= Etat("E2", hashMapOf())
    var E3= Etat("E3", hashMapOf())
    E0.setTransitions(hashMapOf(':' to E1,';' to E1,']' to E1))
    E1.setTransitions(hashMapOf('=' to E2,'-' to E2,'(' to E3,')' to E3))
    E2.setTransitions(hashMapOf('(' to E3,')' to E3))
    var Auto2 = Automate("Smiley",mutableListOf(E0,E1,E2,E3),
        mutableListOf(':',';',']','=','-',')','('),E0,mutableListOf(E3))
    Automates.add(Auto2)

    //HH:MM
    E0= Etat("E0", hashMapOf())
    var H1= Etat("H1", hashMapOf())
    var H2= Etat("H2", hashMapOf())
    var H= Etat("H", hashMapOf())
    var M1= Etat("M1", hashMapOf())
    var M2= Etat("M2", hashMapOf())
    var M= Etat("M", hashMapOf())
    E0.setTransitions(hashMapOf('2' to H2,'1' to H1,'0' to H1))
    H1.setTransitions(hashMapOf('0' to H,'1' to H,'2' to H,'3' to H,'4' to H,'5' to H,'6' to H,'7' to H,'8' to H,'9' to H))
    H2.setTransitions(hashMapOf('0' to H,'1' to H,'2' to H,'3' to H))
    H.setTransitions(hashMapOf(':' to M1))
    M1.setTransitions(hashMapOf('0' to M2,'1' to M2,'2' to M2,'3' to M2,'4' to M2,'5' to M2))
    M2.setTransitions(hashMapOf('0' to M,'1' to M,'2' to M,'3' to M,'4' to M,'5' to M,'6' to M,'7' to M,'8' to M,'9' to M))
    var Auto3 = Automate("HH:MM (format heure)",mutableListOf(E0,H1,H2,H,M1,M2,M),
        mutableListOf(':','1','2','3','4','5','6','7','8','9'),E0,mutableListOf(M))
    Automates.add(Auto3)

    //Test Heure
//    println(MonApplication("18:55",Auto3).resolve()).toString()
//    println(MonApplication("",Auto3).resolve()).toString()
//    println(MonApplication("18:89",Auto3).resolve()).toString()
//    println(MonApplication("xdvfdsfg",Auto3).resolve()).toString()
//    println(MonApplication("18:",Auto3).resolve()).toString()

    //Interface
    while (true) {
        println("--------------- Menu de mon TP -------------------------")
        for (i in 0 until Automates.size){
            println("${i+1} : ${Automates[i].nom}")
        }
        var stringInput ="99999999"
        while (stringInput.toInt()>Automates.size || stringInput.toInt()<=0){
            print("Votre choix (1-${Automates.size}) ? ")
            stringInput=readLine()!!
            if(stringInput.toIntOrNull()==null)stringInput ="99999999"
        }
        println("Votre choix est l'automate : ${Automates[stringInput.toInt()-1].nom}")
        var autoselec=Automates[stringInput.toInt()-1]
        println("Char possible : ${autoselec.A.joinToString()}")
        print("Entrez une chaine de caractère : ")
        stringInput = readLine()!!


        var app = MonApplication(stringInput,autoselec).resolve()
        println(app)
    }


}