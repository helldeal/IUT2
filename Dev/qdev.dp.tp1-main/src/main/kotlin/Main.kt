import iut.but2.tp1.FileChainee

fun main(args: Array<String>) {
    var file= FileChainee<Int>()
    try {
        file.supprimerEnTete()
    }
    catch (e:Exception){
        println(e)
    }
    println(file.listerDepuisDebut())
    file.insererEnQueue(42)
    println(file.listerDepuisDebut())
    file.insererEnQueue(10)
    println(file.listerDepuisDebut())
    file.insererEnQueue(25)
    println(file.listerDepuisDebut())
    println(file.listerDepuisFin())
    println(file.consulter(1))
    println(file.consulter(2))
    println(file.consulter(3))
    try {
        println(file.consulter(4))
    }
    catch (e:Exception){
        println(e)
    }
    println(file.taille())
    file.supprimerEnTete()
    println(file.listerDepuisDebut())
    file.supprimerEnTete()
    println(file.listerDepuisDebut())
    println(file.taille())
}