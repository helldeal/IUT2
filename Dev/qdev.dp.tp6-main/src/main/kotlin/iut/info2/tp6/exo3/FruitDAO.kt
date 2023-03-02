package iut.info2.tp6.exo3

interface FruitDAO {

    /**
     * Donne tous les fruits stockés, triés par le nom des fruits.
     *
     * @return une liste de fruits.
     */
    fun donneLesFruits(): List<Fruit>


    /**
     * Donne les fruits stockés coutant moins que [prix], triés par le prix des fruits.
     *
     * @param prix le prix plancher.
     * @return une liste de prix.
     */
    fun donneLesFruitsCoutantMoinsQue(prix: Double): List<Fruit>

    /**
     * Donne le fruit nommé [nom] si on le trouve dans le stockage.
     *
     * @param nom le nom du fruit à rechercher.
     * @return le fruit ou [null] si [nom] ne correspond à aucun fruit.
     */
    fun donneLeFruit(nom: String): Fruit?

    /**
     * Enregistre les [fruits] présents dans la liste ; écrase les fruits présent
     * précédemment dans le stockage
     *
     * @param fruits une liste de fruits à enregistrer
     */
    fun enregistreLesFruits(fruits: List<Fruit>)

    /**
     * Ajoute un [fruit] dans le stockage
     *
     * @param fruit le fruit à ajouter
     * @throws FruitDejaPresentException si le fruit est déjà présent dans le stockage
     */
    fun ajouteUnFruit(fruit: Fruit)
}