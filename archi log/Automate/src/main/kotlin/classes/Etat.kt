package main.classes

class Etat(var nom:String,var transition:HashMap<Char,Etat>) {
    public fun setTransitions(t:HashMap<Char,Etat>) {
        transition=t
    }
}