package main.classes

class MonApplication(var msg:String,var automate: Automate) {
    public fun resolve():String{
        println("\u001B[33m"+"MSG : $msg"+"\u001B[0m")
        var Ec=automate.E0
        for (i in msg){
            if (Ec.transition[i]==null)return "\u001b[31m"+"Char $i non reconnu dans l'état ${Ec.nom}"+"\u001B[0m"
            else{
                println("\u001B[33m"+"${Ec.nom}--$i-->${Ec.transition[i]!!.nom}"+"\u001B[0m")
                Ec=Ec.transition[i]!!
            }
        }
        if (Ec in automate.Ef)return "\u001B[32m"+"Message reconnu"+"\u001B[0m"
        return "\u001b[31m"+"Message non reconnu"+"\u001B[0m"
    }
}