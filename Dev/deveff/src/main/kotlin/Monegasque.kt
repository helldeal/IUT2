class Monegasque(tab:MutableList<Char>) {
    private var tab : MutableList<Char>
    init {
        this.tab=tab
    }
    fun resolustion(): MutableList<Char>{
        assert(tab.toSet().size==2)
        var i=0
        var k = tab.size-1
        while (k!=i){
            if (tab[i]=='B')i++
            else {
                var tmp=tab[i]
                tab[i]=tab[k]
                tab[k]=tmp
                k=k-1
            }
            assert(0<=i && i<=k && k<=tab.size)
            var check=true
            for (c in 0..i-1){
                if (tab[c]!='B')check=false
            }
            for (c in k+1..tab.size-1){
                if (tab[c]!='R')check=false
            }
            assert(check)
        }
        assert(k==i)
        return tab
    }
}