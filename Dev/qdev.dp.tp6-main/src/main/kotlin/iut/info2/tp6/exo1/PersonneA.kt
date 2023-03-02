package iut.info2.tp6.exo1

/**
 * Classe englobant des Personnes ou des Entreprises
 *
 * @property nom le nom de la personne ou de l'entreprise
 */
open class PersonneA
protected constructor(protected open val nom: String) {

    companion object {

        /**
         * Méthode permettant de retourner une Personne ou une Entreprise à partir
         * de l'analyse d'une seule chaine de caractères qui doit respecter une
         * format précis
         *
         * @param texteSaisi le texte a analyser
         * @return une Personne ou une Entreprise
         * @throws PersonneException si le chaine [texteSaisi] le respecte pas l'un
         *     des formats attendus et donc, ne permet pas de créer une Personne ou
         *     une Entreprise
         */
        fun donnePersonneSaisie(texteSaisi: String): PersonneA {
            if (texteSaisi.substringBefore(" ")==CategorieEntreprise.EURL.toString()||
                texteSaisi.substringBefore(" ")==CategorieEntreprise.SARL.toString()||
                texteSaisi.substringBefore(" ")==CategorieEntreprise.SA.toString()||
                texteSaisi.substringBefore(" ")==CategorieEntreprise.SCP.toString()){
                if (texteSaisi.substringAfter(" ").isFullUpperCase())
                    return Entreprise(texteSaisi.substringAfter(" "), CategorieEntreprise.valueOf(texteSaisi.substringBefore(" ")))

            }
            if (texteSaisi.substringBefore(" ").isFullUpperCase()&&
                texteSaisi.substringAfter(" ").hasAnUppercaseLetterFirst()){
                return Personne(texteSaisi.substringBefore(" "),texteSaisi.substringAfter(" "))
            }
            if (texteSaisi.substringBefore(" ").hasAnUppercaseLetterFirst()&&
                texteSaisi.substringAfter(" ").isFullUpperCase()){
                return Personne(texteSaisi.substringAfter(" "),texteSaisi.substringBefore(" "))
            }
            throw PersonneException("")
        }
    }
}