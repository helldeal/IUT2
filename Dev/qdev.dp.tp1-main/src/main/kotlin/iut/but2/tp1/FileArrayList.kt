package iut.but2.tp1

class FileArrayList<E> : File<E> {

    private var list = ArrayList<E>()

    init {
    }

    override fun insererEnQueue(element: E) {
        list.add(element)
    }

    override fun supprimerEnTete() {
        list.removeFirst()
    }

    override fun listerDepuisDebut(): MutableList<E> {
        return list.toMutableList()
    }

    override fun taille(): Int {
        return list.size
    }

    override fun consulter(position: Int): E {
        return list[position]
    }

    override fun listerDepuisFin(): MutableList<E> {
        var mut=list.toMutableList()
        mut.reverse()
        return mut
    }


}