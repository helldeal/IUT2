fun main() {
    var list = mutableListOf<Char>('R','R','R','R','R','R','B','B','B','B','B','B')
    list.shuffle()
    var monaco=Monegasque(list)
    println(monaco.resolustion())
}