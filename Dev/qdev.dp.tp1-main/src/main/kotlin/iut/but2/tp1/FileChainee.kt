package iut.but2.tp1

import javax.lang.model.element.Element

class FileChainee<E> : File<E> {

    private var debut:Cellule<E>?=null
    private var fin:Cellule<E>?=null

    init {
    }

    override fun insererEnQueue(element: E) {
        var elem=Cellule(element)
        if (debut==null){
            debut=elem
            fin=debut
        }
        else {
            fin?.modifieSuivant(elem)
            fin = elem
        }
    }

    override fun supprimerEnTete() {
        if (debut!=null) debut= debut?.suivant()
        else throw NoSuchElementException()
    }

    override fun listerDepuisDebut() : MutableList<E>  {
        var cons=debut
        var list= mutableListOf<E>()
        if (cons?.valeur()!=null)list.add(cons.valeur())
        while (cons?.suivant()!=null){
            cons=cons.suivant()
            list.add(cons!!.valeur())
        }
        return list
    }

    override fun taille(): Int {
        return listerDepuisDebut().size
    }

    override fun consulter(position: Int): E {
        if (position>=taille()) IndexOutOfBoundsException()
        return listerDepuisDebut()[position]
    }

    override fun listerDepuisFin() : MutableList<E> {
        var list=listerDepuisDebut()
        list.reverse()
        return list
    }

}